package Repository;

import java.sql.*;

public final class DataBaseHandler {
    private static final String DB_URL = "jdbc:derby:data/DATABASE;create=true";
    private static Connection connection = null;
    private static Statement statement = null;

    public DataBaseHandler(){
        this.createConnection();
        this.createStudentTable();
        this.createTemeTable();
        this.createUserTable();
        this.createInregistrareTable();
    }

    public static Connection getConnection() {
        return connection;
    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getConstructor().newInstance();
            connection = DriverManager.getConnection("jdbc:derby:data/DATABASE;create=true");
        } catch (Exception var2) {
            System.out.println("Nu mergee!!!");
            System.exit(0);
        }

    }

    private void createStudentTable() {
        String tableName = "Students";

        try {
            statement = connection.createStatement();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet tables = metadata.getTables((String)null, (String)null, tableName.toUpperCase(), (String[])null);
            if (!tables.next()) {
                statement.execute("CREATE TABLE " + tableName + "( idStudent INT PRIMARY KEY, nume VARCHAR(100), grupa INT, " +
                        "email VARCHAR(50), profesor VARCHAR(50) )");
            }
        } catch (SQLException var4) {
            System.err.println(">>>>> " + var4.getMessage() + " --- setupDatabase1");
        }

    }

    private void createTemeTable() {
        String tableName = "Teme";

        try {
            statement = connection.createStatement();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet tables = metadata.getTables((String)null, (String)null, tableName.toUpperCase(), (String[])null);
            if (!tables.next()) {
                statement.execute("CREATE TABLE " + tableName + "( idTema INT PRIMARY KEY , descriere VARCHAR(100), deadline INT, " +
                        "CHECK (deadline > 0 and deadline <15),predare INT, CHECK (predare > 0 and predare <15))");
            }
        } catch (SQLException var4) {
            System.err.println(">>>>> " + var4.getMessage() + " --- setupDatabase2");
        }

    }
    private void createUserTable() {
        String tableName = "Users";

        try {
            statement = connection.createStatement();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet tables = metadata.getTables((String)null, (String)null, tableName.toUpperCase(), (String[])null);
            if (!tables.next()) {
                statement.execute("CREATE TABLE " + tableName + "( username VARCHAR(200) PRIMARY KEY, password VARCHAR(9999), userType VARCHAR(20), " +
                        "CONSTRAINT chk_userType CHECK (userType IN ('secretar', 'profesor', 'student')))");
            }
        } catch (SQLException var4) {
            System.err.println(">>>>> " + var4.getMessage() + " --- setupDatabase3");
        }

    }

    private void createInregistrareTable(){
        String tableName = "Inregistrari";

        try {
            statement = connection.createStatement();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet tables = metadata.getTables((String)null, (String)null, tableName.toUpperCase(), (String[])null);
            if (!tables.next()) {
                statement.execute("CREATE TABLE " + tableName + "( id INT, idStudent INT,idTema INT, nota FLOAT, feedback VARCHAR(1000),CONSTRAINT chk_inreg PRIMARY KEY (idStudent, idTema)," +
                        "CONSTRAINT tema_fk FOREIGN KEY (idTema) REFERENCES Teme(idTema), CONSTRAINT std_fk FOREIGN KEY (idStudent) REFERENCES Students(idStudent))");
            }
        } catch (SQLException var4) {
            System.err.println(">>>>> " + var4.getMessage() + " --- setupDatabase4");
        }

    }

    public ResultSet execQuery(String query) {
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch (SQLException var4) {
            var4.printStackTrace();
            System.out.println(">>>>> Exception at execQuery:dataHandler1 : " + var4.getMessage());
            return null;
        }
    }

    public boolean execAction(String query) {
        try {
            statement = connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException var3) {
            System.out.println(">>>>> Exception at execQuery:dataHandler2 : " + var3.getMessage());
            return false;
        }
    }
}
