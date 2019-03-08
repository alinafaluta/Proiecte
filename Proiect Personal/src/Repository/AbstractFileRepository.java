package Repository;

import Domain.HasID;
import Domain.Validare.Validator;

import java.io.*;

public abstract class AbstractFileRepository<ID, E extends HasID<ID>> extends InMemoryRepository<ID,E> {
    String fileName;

    public AbstractFileRepository(String fileName, Validator<E> validator) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Citeste toate datele dintr-un fisier
     */
    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                E temp = extractEntity(line);
                super.save(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construieste dintr-o linie citita din fisier o entitate selectand datele prin intermediul unui separator
     * @param line - linia citita din fisier
     * @return o entitate construita pe baza datelor date
     */
    public abstract E extractEntity(String line);

    /**
     * pe langa salvarea din InMemoryRepository se mai adauga scrierea in fisier a noii entitati
     */
    @Override
    public E save(E entity) {
        E returnedEntity = super.save(entity);
        if (returnedEntity == null) {
            writeToFile(entity);
        }
        return returnedEntity;
    }
    /**
     * pe langa stergerea din InMemoryRepository se mai adauga rescrierea fisierului cu etitatile ramase
     */
    @Override
    public  E delete (ID id){
        E returnedEntity = super.delete(id);
        if (returnedEntity != null) {
            writeToFileALL();
        }
        return returnedEntity;
    }
    /**
     * pe langa update-ul din InMemoryRepository se mai adauga rescrierea fisierului cu etitatile modificate
     */
    @Override
    public  E update (E entity){
        E returnedEntity = super.update(entity);
        if (returnedEntity == null) {
            writeToFileALL();
        }
        return returnedEntity;
    }

    /**
     * Adauga in fisier o noua entitate
     * @param entity - etitatea ce va fi adaugata
     */
    protected void writeToFile(E entity) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(entity.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * rescrie intreg fisierul cu elementele din InMemoryRepository
     */
    protected void writeToFileALL() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Iterable it = super.findAll();
            it.forEach(a->{
                try {
                    bw.write(a.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
