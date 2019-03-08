package Domain.Validare;

import Domain.Student;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidareStudent implements Validator<Student> {
    private static Pattern numePattern = Pattern.compile("^[A-Za-z ,.'-]+$");
    private static Pattern idPattern = Pattern.compile("[0-9]+");
    private static Pattern grupaPattern = Pattern.compile("^[1-9]{3}$");
    private static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    /**
     * Validarea numelui studentului
     * @param name - String (contine litere mari/mici si caracterele speciale: ,.'-)
     * @throws ValidationException daca numele nu e valid
     */
    private static void validateName(String name) {
        Matcher mtch = numePattern.matcher(name);
        if(!mtch.matches()){
            throw new ValidationException("Nume incorect!");
        }
    }

    /**
     * Validarea id-ului studentului
     * @param id - numar intreg >0
     * @throws ValidationException daca id-ul nu e valid
     */
    private static void validateID(String id){
        Matcher mtch = idPattern.matcher(id);
        if(!mtch.matches()){
            throw new ValidationException("Id incorect!");
        }
    }

    /**
     * Validarea id-ului studentului
     * @param grupa - numar intreg de 3 cifre
     * @throws ValidationException daca id-ul nu e valid
     */
    private static void validateGrupa(String grupa) {
        Matcher mtch = grupaPattern.matcher(grupa);
        if(!mtch.matches()){
            throw new ValidationException("Grupa incorecta!");
        }
    }

    /**
     * Validarea email-ului studentului
     * @param email - String
     * @throws ValidationException daca email-ul nu e valid
     */
    private static void validateEmail(String email)  {
        Matcher mtch = emailPattern.matcher(email);
        if(!mtch.matches()){
            throw new ValidationException("Email incorect!");
        }
    }

    /**
     * Validarea entitatii Student
     * @param entity - entitatea de validat
     */
    @Override
    public void validate(Student entity)  {
        validateID(Integer.toString(entity.getID()));
        validateName(entity.getNume());
        validateGrupa(Integer.toString(entity.getGrupa()));
        validateEmail(entity.getEmail());
        validateName(entity.getProfesor());
    }
}
