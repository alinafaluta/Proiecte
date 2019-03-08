package Domain.Validare;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}