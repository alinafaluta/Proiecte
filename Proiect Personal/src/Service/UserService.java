package Service;

import Domain.Student;
import Domain.User;
import Domain.UserType;
import Domain.Validare.ValidationException;
import Repository.DataBaseRepository;
import Repository.DataBaseRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserService {
    DataBaseRepository<String, User> repoUser;

    public UserService(DataBaseRepository<String, User> repoUser) {
        this.repoUser = repoUser;
    }


    public static String criptareParola(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(String username, String pass, UserType type) throws SQLException {
        if(repoUser.findOne(username)!=null)
            throw new ValidationException("Un User cu acest nume exista deja!");
        repoUser.save(new User(username,criptareParola(pass),type));
    }
    public void deleteUser(String username)
    {
        repoUser.delete(username);
    }

    public void updateParolaUser(String username, String pass, UserType type,String newPassword)
    {
        repoUser.update(new User(username,criptareParola(newPassword),type));
    }

    public boolean ParolaValida(String username, String pass)
    {
        return repoUser.findOne(username).getPassword().equals(criptareParola(pass));
    }

    public boolean UserExistent(String username)
    {
        if(repoUser.findOne(username)!=null)
            return true;
        return false;
    }
    public User findOne(String use){return repoUser.findOne(use);}
    public Iterable<User> getAll()
    {
        return repoUser.findAll();
    }
    public Iterable<User> allUsers()
    {
        return repoUser.findAll();
    }

    private <T> Iterable <T> filter(Iterable <T> list, Predicate<T> cond)
    {
        List<T> rez=new ArrayList<>();
        list.forEach((T x)->{if (cond.test(x)) rez.add(x);});
        return rez;
    }

    public Iterable<User> Profesor() {
        return filter(repoUser.findAll(), student -> student.getUserType().equals(UserType.profesor));
    }
    public Iterable<User> Secretar() {
        return filter(repoUser.findAll(), student -> student.getUserType().equals(UserType.secretar));
    }
    public Iterable<User> Student() {
        return filter(repoUser.findAll(), student -> student.getUserType().equals(UserType.student));
    }
}
