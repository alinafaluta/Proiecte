package Repository;

import Domain.HasID;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class DataBaseRepository<Id, E extends HasID<Id>> {
    protected String idColumnName;
    private DataBaseHandler dataBaseHandler;
    protected String tableName;
    protected static PreparedStatement preparedStatement = null;
    protected String getAllQuery;

    public DataBaseRepository(DataBaseHandler databaseHandler, String tableName) {
        this.dataBaseHandler = databaseHandler;
        this.tableName = tableName;
        this.getAllQuery = "SELECT * FROM " + tableName;
    }

    public void save(E entity) throws SQLException {
            this.createAddStatement(entity);
           // this.populateStatementValues(entity);
            preparedStatement.execute();

    }

    public E findOne(Id id) {
        try {
            String query = "SELECT * FROM " + this.tableName + " WHERE " +this.idColumnName+" = ?  FETCH FIRST ROW ONLY";
            preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
            if (id instanceof Integer) {
                preparedStatement.setInt(1, (Integer)id);
            } else if (id instanceof String) {
                preparedStatement.setString(1, (String)id);
            }

            ResultSet result = preparedStatement.executeQuery();
            E returned = null;
            if (result.next()) {
                returned = this.createEntity(result);
            }

            return returned;
        } catch (SQLException var5) {
            System.err.println("Unable to execute query fin.\n" + var5.getMessage());
            return null;
        }
    }

    public long size() {
        try {
            ResultSet resultSet = this.dataBaseHandler.execQuery("SELECT COUNT(*) FROM " + this.tableName);
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException var2) {
            System.out.println("Eroare la lungimea tabelului: " + var2.getMessage());
            return 0L;
        }
    }

    public E delete(Id id) {
        try {
            String queryGet = "SELECT * FROM " + this.tableName + " WHERE " + this.idColumnName + " = ?";
            String query = "DELETE FROM " + this.tableName + " WHERE " + this.idColumnName + " = ?";
            preparedStatement = DataBaseHandler.getConnection().prepareStatement(queryGet);
            if (id instanceof Integer) {
                preparedStatement.setInt(1, (Integer)id);
            } else if (id instanceof String) {
                preparedStatement.setString(1, (String)id);
            }

            ResultSet result = preparedStatement.executeQuery();
            E returned = null;
            if (result.next()) {
                E oneReturned = this.createEntity(result);
                if (oneReturned!=null) {
                    returned = oneReturned;
                }
            }

            preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
            if (id instanceof Integer) {
                preparedStatement.setInt(1, (Integer)id);
            } else if (id instanceof String) {
                preparedStatement.setString(1, (String)id);
            }

            preparedStatement.execute();
            return returned;
        } catch (SQLException var7) {
            System.err.println("Unable to execute query delete.\n" + var7.getMessage());
            return null;
        }
    }

    public void removeAll() {
        this.dataBaseHandler.execAction("DELETE FROM " + this.tableName);
    }

    public ArrayList<E> findAll() {
        ResultSet resultSet = this.dataBaseHandler.execQuery(this.getAllQuery);
        ArrayList<E> elements = new ArrayList();
        if (resultSet == null) {
            return null;
        } else {
            try {
                while(resultSet.next()) {
                    E entity = this.createEntity(resultSet);
                    if(entity!=null)
                        elements.add(entity);
                }
            } catch (SQLException var4) {
                System.out.println("Eroare la getAll: " + var4.getMessage());
                return new ArrayList();
            }

            return elements;
        }
    }

    public E update(E entity) {
        try {
            String queryGet = "SELECT * FROM " + this.tableName + " WHERE " + this.idColumnName + " = ?";
            preparedStatement = DataBaseHandler.getConnection().prepareStatement(queryGet);
            if (entity.getID() instanceof Integer) {
                preparedStatement.setInt(1, (Integer)entity.getID());
            } else if (entity.getID() instanceof String) {
                preparedStatement.setString(1, (String)entity.getID());
            }

            ResultSet result = preparedStatement.executeQuery();
            E returned = null;
            if (result.next()) {
                E optionalReturned = this.createEntity(result);
                if (optionalReturned!=null) {
                    returned = optionalReturned;
                }
            }

            this.createEditStatement(entity.getID());
            this.populateStatementValues(entity);
            preparedStatement.execute();
            return returned;
        } catch (SQLException var6) {
            System.err.println("Unable to execute query update.\n" + var6.getMessage());
            return null;
        }
    }

    public void importDataFromFile(String filename, String separator) {
        Path path = Paths.get(filename);

        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach((line) -> {
                String[] fields = line.split(separator);
                E entity = this.createEntityFromFile(fields);
                try {
                    this.save(entity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException var6) {
            System.out.println("Nu exista fisierul " + filename + ".");
        } catch (NumberFormatException | IOException var7) {
            System.out.println("Input-Output error: " + var7.getMessage());
        }

    }

    protected abstract E createEntityFromFile(String[] var1);

    protected abstract E createEntity(ResultSet var1);

    protected abstract void createAddStatement(E var1) throws SQLException;

    protected abstract void createEditStatement(Id var1) throws SQLException;

    protected abstract void populateStatementValues(E var1) throws SQLException;
}