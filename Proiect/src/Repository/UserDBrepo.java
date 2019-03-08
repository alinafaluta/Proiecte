package Repository;

import Domain.User;
import Domain.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBrepo extends  DataBaseRepository<String, User>{


    public UserDBrepo(DataBaseHandler databaseHandler) {
        super(databaseHandler, "Users");
        this.getAllQuery=this.getAllQuery + " ORDER BY userType";
        this.idColumnName="username";
    }

    @Override
    protected User createEntityFromFile(String[] var1) {
        String username= var1[0];
        String password = var1[1];
        String userType = var1[2];
        return new User(username,password,getType(userType));
    }

    protected UserType getType(String s)
    {
        if(s.equals("secretar"))
            return UserType.secretar;
        if(s.equals("profesor"))
            return UserType.profesor;
        if(s.equals("student"))
            return UserType.student;
        return null;
    }

    protected String getTypeString(UserType u)
    {
     if(u==UserType.student)
         return "student";
     if(u==UserType.profesor)
         return "profesor";
     if(u==UserType.secretar)
         return "secretar";
     return null;
    }
    @Override
    protected User createEntity(ResultSet resultSet) {
        User u = null;
        try {
            String user = resultSet.getString("username");
            String pass = resultSet.getString("password");
            String type = resultSet.getString("userType");
            u = new User(user,pass,getType(type));
            return u;
        } catch (SQLException var6) {
            System.out.println("Eroare la create entity din result set : " + var6.getMessage());
            return u;
        }
    }

    @Override
    protected void createAddStatement(User user) throws SQLException {
        String query = "INSERT INTO " + this.tableName + " (username,password,userType) values ('" +user.getUsername()+"','"+user.getPassword()+"','"+getTypeString(user.getUserType())+"')";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
    }

    @Override
    protected void createEditStatement(String username) throws SQLException {
        String query = "UPDATE " + this.tableName + " SET username = ?, password = ?, type=?,WHERE username = ?";
        preparedStatement = DataBaseHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(7, username);
    }

    @Override
    protected void populateStatementValues(User user) throws SQLException {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getUserType().name());
    }
}
