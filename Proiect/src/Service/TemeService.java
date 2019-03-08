package Service;

import Domain.Student;
import Domain.Tema;
import Domain.Validare.ValidationException;
import Repository.DataBaseRepository;
import Utils.*;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TemeService implements Observable<Event> {
    DataBaseRepository<Integer, Tema> repoTeme;
    private List<Observer<Event>> observers=new ArrayList<>();

    public TemeService(DataBaseRepository<Integer, Tema> repoTeme) {
        this.repoTeme = repoTeme;
    }

    /**
     * functie ce adauga tema in repository
     * @param t - tema pe care dorim sa o adaugam
     * @throws ValidationException cand tema exista deja
     */
    public void addTema(Tema t) throws SQLException {
        if(repoTeme.findOne(t.getID())!=null)
            throw new ValidationException("Tema exista deja");

        int max=0;
        Iterable<Tema> it = repoTeme.findAll();
        for(Tema s : it)
        {
            if(s.getID()>max)
                max=s.getID();
        }
        t.setID(max+1);
        repoTeme.save(t);
        notifyObservers(new TemaChangeEvent(ChangeEventType.ADD,t));
    }
    /**
     * Sterge o tema din fisier si memorie
     * @param id -id-ul temei pe care dorim sa o stergem
     * @throws ValidationException daca nu exista nici o tema cu id-ul dat
     */
    public void deleteTema(Integer id){

        if(repoTeme.findOne(id)==null)
            throw new ValidationException("Nu exista nici o tema cu acest id\n");
        Tema t =repoTeme.delete(id);
        notifyObservers(new TemaChangeEvent(ChangeEventType.DELETE,t));
    }

    /**
     * Modifica toate datele unei teme
     * @param t -  pe care dorim sa o stergem
     * @throws ValidationException daca nu exista nici o tema cu id-ul dat
     */
    public void updateTema(Tema t){
        Tema old = findTema(t.getID());
        if(repoTeme.update(t)==null){
            throw  new ValidationException("Tema pe care doriti sa o actualizati nu exista! \n");
        }
        notifyObservers(new TemaChangeEvent(ChangeEventType.UPDATE,t,old));
    }

    /**
     * Functie ce extrage toate temele din memorie
     * @return un interabil cu toate temele
     */
    public Iterable<Tema> allTeme()
    {
        return repoTeme.findAll();
    }

    /**
     * returneaza o Tema cautata dupa id
     * @param id - id-ul temei pe care o cautam
     * @return Tema cautata
     */
    public Tema findTema(Integer id)
    {
        return repoTeme.findOne(id);
    }

    int nr;
    void initNr(){nr=0;};
    void incrementNr(){nr++;};
    int  retNr(){return nr;};
    public Iterable<Tema> getAll(){return repoTeme.findAll();}
    /**
     *Determina numarul de teme
     * @return un int cu nr de teme
     */
    public int nrTeme()
    {
        Iterable<Tema> it = repoTeme.findAll();
        initNr();
        it.forEach(a->incrementNr());
        return retNr();
    }
public Iterable<String> toateTemele()
{
    List<String> teme = new ArrayList<>();
    for(Tema t  : this.getAll())
    {
        teme.add(t.getDescriere());
    }
    return teme;
}
    private <T> Iterable <T> filter(Iterable <T> list, Predicate<T> cond)
    {
        List<T> rez=new ArrayList<>();
        list.forEach((T x)->{if (cond.test(x)) rez.add(x);});
        return rez;
    }

    public Iterable<Tema> byDeadline(String name) {
        return filter(repoTeme.findAll(), student -> Integer.toString(student.getDeadline()).contains(name));
    }


    public Iterable<Tema> byPredare(String name) {
        return filter(repoTeme.findAll(), student -> Integer.toString(student.getPredare()).contains(name));
    }


    public Iterable<Tema> byDescriere(String name) {
        return filter(repoTeme.findAll(), student -> student.getDescriere().contains(name));
    }

    public Iterable<Integer> toateDeadline()
    {
        Iterable<Tema> teme = repoTeme.findAll();
        List<Integer> dead = new ArrayList<>();
        for(Tema t :teme )
        {
            if(dead.indexOf(t.getDeadline())==-1)
                dead.add(t.getDeadline());
        }
        return dead;
    }

    public Iterable<Pair<Integer,Integer>> raportTemeDeadline()
    {
        List<Pair<Integer,Integer>> raport = new ArrayList<>();
        Iterable<Tema> teme = repoTeme.findAll();
        Iterable<Integer> dead = toateDeadline();
        for(Integer it: dead)
        {
            int nr=0;
            for(Tema t : teme)
            {
                if(t.getDeadline()==it)
                    nr++;
            }
            raport.add(new Pair(it,nr));
        }
        return raport;
    }

    @Override
    public void addObserver(Observer<Event> e) {
            observers.add(e);
    }

    @Override
    public void removeObserver(Observer<Event> e) {
            observers.remove(e);
    }

    @Override
    public void notifyObservers(Event t) {
            observers.stream().forEach(x->x.update(t));
    }
}
