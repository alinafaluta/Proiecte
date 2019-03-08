package Domain.Validare;

import Domain.Tema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidareTema implements Validator<Tema> {
    private static Pattern idPtrn = Pattern.compile("^[1-9][0-9]*$");
    private static Pattern datePtrn = Pattern.compile("^[1-9]|1[0-4]$");
    /**
     * Validarea id-ului temei
     * @param id - numar intreg >0
     * @throws ValidationException daca id-ul nu e valid
     */
    private void validateId(String id){
        Matcher mtch = idPtrn.matcher(id);
        if(!mtch.matches()){
            throw new ValidationException("Id incorect!");
        }
    }
    /**
     * Validarea data
     * @param data - numar intreg >0
     * @throws ValidationException daca data nu e valida
     */
    private void validateDate(Integer data){
        String dataS=data.toString();
        Matcher mtch = datePtrn.matcher(dataS);
        if(!mtch.matches()){
            throw new ValidationException("Data incorecta!");
        }
    }

    /**
     * Se verifica daca data de predare < deadline
     * @param predare (data de primire a temei)
     * @param deadline (data limita de predare a temei)
     */
    private void validateInterval(Integer predare,Integer deadline){
        if(predare>deadline)
            throw  new ValidationException("Date incorecte!");
    }

    /**
     * Validarea entitatii student
     * @param entity - entitatea de validat
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
        validateId(Integer.toString(entity.getID()));
        validateDate(entity.getDeadline());
        validateDate(entity.getPredare());
        validateInterval(entity.getPredare(),entity.getDeadline());
    }
}
