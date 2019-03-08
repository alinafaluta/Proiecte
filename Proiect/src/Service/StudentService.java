package Service;

import Domain.Student;
import Domain.Validare.ValidationException;
import Repository.DataBaseRepository;
import Utils.*;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StudentService implements Observable<StudentChangeEvent> {
    DataBaseRepository<Integer, Student> repoStud;
    private List<Observer<StudentChangeEvent>> observers=new ArrayList<>();

    public StudentService(DataBaseRepository<Integer, Student> repo) {
        this.repoStud = repo;
       // this.repoStud.importDataFromFile("C:/Users/alina/Desktop/ProiectGrupa221Import.txt",",");
    }
    /**
     * Adauga un student in repo
     * @param entity - entitatea de tip student pe care vreau sa o adaug in repo
     * @throws ValidationException atunci cand id-ul studentului citit de la tastatura exista deja in repository
     */
    public void addStudent(Student entity) throws SQLException {
        if(repoStud.findOne(entity.getIdStudent())!=null) {
            throw new ValidationException("Studentul cu acest numar matricol exista deja");
        }
        int max=0;
        Iterable<Student> it = repoStud.findAll();
        for(Student s : it)
        {
            if(s.getIdStudent()>max)
                max=s.getIdStudent();
        }
        entity.setIdStudent(max+1);
        repoStud.save(entity);

        notifyObservers(new StudentChangeEvent(ChangeEventType.ADD,entity));
    }
    /**
     *Sterge un student din fisier si memorie
     * @param id - numarul matricol al studentului pe care dorim sa il stergem
     * @throws ValidationException daca nu exista nici un student cu numarul matricol dat
     */
    public void deleteStudent(Integer id)
    {
        if(repoStud.findOne(id)==null)
            throw new ValidationException("Nu exista nici un student cu acest numar matricol \n");
        Student entity = repoStud.delete(id);
        notifyObservers(new StudentChangeEvent(ChangeEventType.DELETE,entity));
    }
    /**
     * Modifica datele unui student
     * @param entity - studentul cu acelasi id dar date noi
     * @throws ValidationException daca nu exista nici un student cu id-ul dat
     */
    public void updateStudent(Student entity)
    {   Student old = findStudent(entity.getIdStudent());
        if(repoStud.update(entity)==null){
            throw  new ValidationException("Studentul pe care doriti sa il modificati nu exista! \n");
        }
        notifyObservers(new StudentChangeEvent(ChangeEventType.UPDATE,old,entity));
       // notifyObservers(new StudentChangeEvent(ChangeEventType.DELETE,old));

    }

    /**
     * Functie ce extrage toti studentii din memorie
     * @return un iterabil cu toti studentii
     */
    public Iterable<Student> allStudents()
    {
        return repoStud.findAll();
    }


    /**
     * returneaza un Student cautat dupa id
     * @param id - id-ul studentului pe care il cautam
     * @return Studentul cautat
     */
    public Student findStudent(Integer id)
    {
        return repoStud.findOne(id);
    }
    public Iterable<Student> getAll(){return repoStud.findAll();}
    int nr;
    void initNr(){nr=0;};
    void incrementNr(){nr++;};
    int  retNr(){return nr;};
    /**
     * Determina numarul de studenti
     * @return un int cu nr de stud
     */
    public int nrStudents()
    {
        Iterable<Student> it = repoStud.findAll();
        initNr();
        it.forEach(a->incrementNr());
        return retNr();
    }

    private <T> Iterable <T> filter(Iterable <T> list, Predicate<T> cond)
    {
        List<T> rez=new ArrayList<>();
        list.forEach((T x)->{if (cond.test(x)) rez.add(x);});
        return rez;
    }

    public Iterable<Student> byTeacher(String name) {
        return filter(repoStud.findAll(), student -> student.getProfesor().contains(name));
    }

    public Iterable<Student> byGroupe(String name) {
        return filter(repoStud.findAll(), student -> Integer.toString(student.getGrupa()).contains(name));
    }

    public Iterable<Student> byName(String name) {
        return filter(repoStud.findAll(), student -> student.getNume().contains(name));
    }

    public Iterable<Student> byEmail(String name) {
        return filter(repoStud.findAll(), student -> student.getEmail().contains(name));
    }

    public Iterable<Integer> grupeleCuCeiMaiMultiStudenti(){
        int max=0;
        int grupa=0;
        int grupaMax=0;
        Iterable<Student> studenti = repoStud.findAll();
        for(Student s : studenti)
        {
            grupa=s.getGrupa();
            int nr =0;
            for(Student ss:studenti)
            {
                if(s.getGrupa()==grupa)
                    nr++;
            }
            if(nr>max)
            {
                max=nr;
                grupaMax=grupa;
            }
        }
        List<Integer> grupe=new ArrayList<>();
        for(Student s : studenti)
        {
           grupa=s.getGrupa();
            int nr =0;
            for(Student ss:studenti)
            {
                if(s.getGrupa()==grupa)
                    nr++;
            }
            if(nr==max)
            {
               grupe.add(grupa);
            }
        }
        return grupe;
    }


    public Iterable<Integer> toateGrupele()
    {
        Iterable<Student> studenti = repoStud.findAll();
        List<Integer> grupe = new ArrayList<>();
        for(Student s:studenti)
        {
            if(grupe.indexOf(s.getGrupa())==-1)
                grupe.add(s.getGrupa());
        }
        return grupe;
    }

    public Iterable<String> totiProfesorii()
    {
        Iterable<Student> studenti = repoStud.findAll();
        List<String> profi = new ArrayList<>();
        for(Student s:studenti)
        {
            if(profi.indexOf(s.getProfesor())==-1)
                profi.add(s.getProfesor());
        }
        return profi;
    }

    public Iterable<Pair<String,Integer>> raportStudetiProfi()
    {
        List<Pair<String,Integer>> raport = new ArrayList<>();
        Iterable<String> profesori = totiProfesorii();
        Iterable<Student> studenti = repoStud.findAll();
        for(String prof: profesori)
        {
            int nr=0;
            for(Student s:studenti)
            {
                if(s.getProfesor().equals(prof))
                    nr++;
            }
            raport.add(new Pair(prof,nr));
        }
        return raport;
    }

    public Iterable<Pair<Integer,Integer>> raportStudentiGrupe()
    {
        List<Pair<Integer,Integer>> raport = new ArrayList<>();
        Iterable<Integer> grupe = toateGrupele();
        Iterable<Student> studenti = repoStud.findAll();
        for(Integer gr:grupe)
        {
            int nr=0;
            for(Student s:studenti)
            {
                if(s.getGrupa()==gr)
                    nr++;
            }
            raport.add(new Pair(gr,nr));
        }
        return raport;
    }
    @Override
    public void addObserver(Observer<StudentChangeEvent> e) {
            observers.add(e);
    }

    @Override
    public void removeObserver(Observer<StudentChangeEvent> e) {
            observers.remove(e);
    }

    @Override
    public void notifyObservers(StudentChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }
}
