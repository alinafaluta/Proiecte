package Repository;

import Domain.Inregistrare;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InregistrareDBrepo extends DataBaseRepository<Integer, Inregistrare> {
    public InregistrareDBrepo(DataBaseHandler databaseHandler) {
        super(databaseHandler, "Inregistrari");
        this.getAllQuery=this.getAllQuery + " ORDER BY idStudent";
        //this.idColumnName="id"
    }


    @Override
    protected Inregistrare createEntityFromFile(String[] var1) {
        int id = Integer.parseInt(var1[0]);
        int idStudent = Integer.parseInt(var1[1]);
        int idTema = Integer.parseInt(var1[2]);
       Float nota = Float.parseFloat(var1[3]);
        String feedback = var1[4];
        return new Inregistrare(id,idStudent,idTema,nota,feedback);
    }

    @Override
    protected Inregistrare createEntity(ResultSet resultSet) {
        Inregistrare i = null;
        try {
            int idStudent = resultSet.getInt("idStudent");
            int idTema= resultSet.getInt("idTema");
            int id= resultSet.getInt("id");
            float nota = resultSet.getFloat("nota");
            String feed = resultSet.getString("feedback");
            i=new Inregistrare(id,idStudent,idTema,nota,feed);
            return i;
        } catch (SQLException var6) {
            System.out.println("Eroare la create entity din result set : " + var6.getMessage());
            return i;
        }
    }

    @Override
    protected void createAddStatement(Inregistrare inreg) throws SQLException {
        String query = "INSERT INTO " + this.tableName + " (idStudent,idTema,nota,feedback) values ("+inreg.getStudent()+","+inreg.getTema()+","+inreg.getNota()+",'"+inreg.getFeedback()+"')";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);

    }

    @Override
    protected void createEditStatement(Integer var1) throws SQLException {

    }

    @Override
    protected void populateStatementValues(Inregistrare entity) throws SQLException {
        preparedStatement.setInt(1, entity.getID());
        preparedStatement.setInt(2, entity.getStudent());
        preparedStatement.setInt(3, entity.getTema());
        preparedStatement.setFloat(4, entity.getNota());
        preparedStatement.setString(5, entity.getFeedback());

    }
}
