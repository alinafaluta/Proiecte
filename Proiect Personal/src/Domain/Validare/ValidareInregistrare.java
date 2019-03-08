package Domain.Validare;

import Domain.Inregistrare;

public class ValidareInregistrare implements Validator<Inregistrare> {
    @Override
    public void validate(Inregistrare entity) throws ValidationException {
        if(entity.getID().equals(null))
            throw new ValidationException("ID NULL");
        if(entity.getID()<0)
            throw new ValidationException("ID Negativ");
        if(entity.getStudent()<0)
            throw new ValidationException("ID Student Negativ");
        if(entity.getTema()<0)
            throw new ValidationException("ID Tema Negativ");
        if(entity.getNota()<0 || entity.getNota()>10 )
            throw new ValidationException("Nota incorecta");
    }
}
