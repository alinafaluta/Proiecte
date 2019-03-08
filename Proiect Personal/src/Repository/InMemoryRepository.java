package Repository;

import Domain.HasID;
import Domain.Validare.Validator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends HasID<ID>> implements CrudRepository<ID,E> {

    private Validator<E> validator;
    Map<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    /**
     * Returneaza un obiect de tip E ce are ID-ul dat
     * @param id - id-ul obiectului cautat
     * @return obiectul cautat daca acesta exista
     * @throws IllegalArgumentException daca id-ul dat este null
     */
    @Override
    public E findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    /**
     * Returneaza un iterabil cu toate valorile salvate
     * @return interabil cu entiteati de tip E
     */
    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if (entity == null) throw new IllegalArgumentException();
        validator.validate(entity);
        if (entities.get(entity.getID()) == null) {
            entities.put(entity.getID(), entity);
            return null;
        }
        return entity;
    }

    @Override
    public E delete(ID id) {
        if(id==null) {
            throw new IllegalArgumentException("Parametrul dat este null");
        }
        else{
            E s=findOne(id);
            if(s==null) return null;
            else{
                entities.remove(id);
                return s;
            }
        }
    }

    @Override
    public E update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        validator.validate(entity);
        if(entities.get(entity.getID()) != null) {
            entities.put(entity.getID(),entity);
            return null;
        }
        return entity;

    }

}
