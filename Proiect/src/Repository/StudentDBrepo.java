package Repository;

import Domain.Student;
import Domain.Validare.ValidareStudent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDBrepo extends DataBaseRepository<Integer, Student> {
    ValidareStudent vali = new ValidareStudent();
    public StudentDBrepo(DataBaseHandler databaseHandler) {
        super(databaseHandler, "Students");
        this.getAllQuery=this.getAllQuery + " ORDER BY idStudent";
        this.idColumnName = "idStudent";
    }

    @Override
    protected Student createEntityFromFile(String[] var1) {
        int idStudent = Integer.parseInt(var1[0]);
        String nume = var1[1];
        int grupa = Integer.parseInt(var1[2]);
        String email = var1[3];
        String prof = var1[4];
        return new Student(idStudent,nume,grupa,email,prof);
    }

    @Override
    protected Student createEntity(ResultSet resultSet) {
        Student studi = null;
        try {
            int id = resultSet.getInt("idStudent");
            String nume = resultSet.getString("nume");
            int grupa = resultSet.getInt("grupa");
            String email = resultSet.getString("email");
            String prof = resultSet.getString("profesor");
            studi = new Student(id,nume,grupa,email,prof);
            return studi;
        } catch (SQLException var6) {
            System.out.println("Eroare la create entity din result set : " + var6.getMessage());
            return studi;
        }
    }

    @Override
    protected void createAddStatement(Student student) throws SQLException {
        vali.validate(student);
        String query = "INSERT INTO " + this.tableName + " (idStudent, nume,grupa,email,profesor) VALUES ("+student.getID()+
                ",'"+student.getNume()+"',"+student.getGrupa()+",'"+student.getEmail()+"','"+student.getProfesor()+"')";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
    }

    @Override
    protected void createEditStatement(Integer idStudent) throws SQLException {
        String query = "UPDATE " + this.tableName + " SET  nume = ?, grupa=?, email=?, profesor=? WHERE idStudent = ?";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(5, idStudent);
    }

    @Override
    protected void populateStatementValues(Student student) throws SQLException {
        //preparedStatement.setInt(5, student.getIdStudent());
        preparedStatement.setString(1, student.getNume());
        preparedStatement.setInt(2, student.getGrupa());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setString(4, student.getProfesor());
    }

}
