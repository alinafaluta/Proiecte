package Repository;

import Domain.Tema;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemaDBrepo extends DataBaseRepository<Integer, Tema> {

    public TemaDBrepo(DataBaseHandler databaseHandler) {
        super(databaseHandler, "Teme");
        this.getAllQuery=this.getAllQuery + " ORDER BY idTema";
        this.idColumnName = "idTema";
    }

    @Override
    protected Tema createEntityFromFile(String[] var1) {
        int idTema = Integer.parseInt(var1[0]);
        String descriere = var1[1];
        int deadline = Integer.parseInt(var1[2]);
        int predare= Integer.parseInt(var1[3]);
        return new Tema(idTema,descriere,deadline,predare);
    }

    @Override
    protected Tema createEntity(ResultSet resultSet) {
       Tema t = null;
        try {
        int id = resultSet.getInt("idTema");
        String descriere = resultSet.getString("descriere");
        int deadline = resultSet.getInt("deadline");
        int predare = resultSet.getInt("predare");
        t = new Tema(id,descriere,deadline,predare);
        return t;
        } catch (SQLException var6) {
            System.out.println("Eroare la create entity din result set : " + var6.getMessage());
            return t;
        }
    }

    @Override
    protected void createAddStatement(Tema t) throws SQLException {
        String query = "INSERT INTO " + this.tableName + " (idTema,descriere,deadline,predare) values ("+t.getID()+",'"+t.getDescriere()+"',"+t.getDeadline()+","+t.getPredare()+")";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);


    }

    @Override
    protected void createEditStatement(Integer id) throws SQLException {
        String query = "UPDATE " + this.tableName + " SET descriere = ?, deadline=?, predare=? WHERE idTema =?";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(4, id);


    }

    @Override
    protected void populateStatementValues(Tema tema) throws SQLException {
        preparedStatement.setString(1, tema.getDescriere());
        preparedStatement.setInt(2, tema.getDeadline());
        preparedStatement.setInt(3, tema.getPredare());
        preparedStatement.setInt(4, tema.getID());
    }
}
