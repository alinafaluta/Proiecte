package Service;

import Domain.Inregistrare;
import Domain.Student;
import Domain.Tema;
import Domain.Validare.ValidationException;
import Repository.DataBaseRepository;
import Utils.*;
import Utils.Observable;
import Utils.Observer;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.util.Pair;

import java.io.IOException;


public class CatalogService implements Observable<Event> {
    DataBaseRepository<Integer, Inregistrare> repoInreg;
    DataBaseRepository<Integer, Student> repoStud;
    DataBaseRepository<Integer, Tema> repoTema;
    private List<Observer<Event>> observers=new ArrayList<>();

    public CatalogService(DataBaseRepository<Integer, Inregistrare> repoInreg) {
        this.repoInreg = repoInreg;
    }

    public CatalogService(DataBaseRepository<Integer, Inregistrare> repoInreg, DataBaseRepository<Integer, Student> repoStud, DataBaseRepository<Integer, Tema> repoTema) {
        this.repoInreg = repoInreg;
        this.repoStud = repoStud;
        this.repoTema = repoTema;
    }
    private String scrieFisier(Inregistrare nota){
        String msg;
        msg="Tema: "+nota.getTema()+"\n";
        msg=msg+"Nota: "+nota.getNota()+"\n";
        Tema tema=repoTema.findOne(nota.getTema());
        msg=msg+"Predata in saptamana: "+tema.getPredare()+"\n";
        msg=msg+"Deadline: "+tema.getDeadline()+"\n";
        msg=msg+"Feedback: "+nota.getFeedback()+"\n";
        return msg;
    }

    private void adaugaInFile(Inregistrare nota){
        Student student=repoStud.findOne(nota.getStudent());
        String fileName="./src/Date/"+student.getNume()+".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true))) {
            bw.write(scrieFisier(nota));
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Inregistrare adaugaNota(Integer idS, Integer idT, String data, Float notaProf,String feedback, boolean motivat) throws SQLException {
        Inregistrare entity=new Inregistrare(1,idS,idT,notaProf,feedback);
        if(repoStud.findOne(entity.getStudent())!=null) {
            if (repoTema.findOne(entity.getTema()) != null) {
                if(!motivat) {
                    Float nota=calculeazaNota(data,notaProf,repoTema.findOne(idT));
                    entity.setNota(nota);
                }
                adaugaInFile(entity);
                repoInreg.save(entity);
                notifyObservers(new InregistrareChangeEvent(ChangeEventType.ADD,entity));
                return entity;
            } else {
                throw new ValidationException("Id-ul dat pentru tema este inexistent \n");
            }
        }
        else {
            throw new ValidationException("Id-ul dat pentru student este inexistent \n");
        }
    }


    public Float calculeazaNota(String data,Float notaProf, Tema tema) {
        Float nota=notaProf;
        Integer dif = Integer.parseInt(data) - tema.getDeadline();
        if (dif > 0 && dif <= 2) {
            return  nota - dif * 2.5f;
        } else if (dif<=0){
            return (float) nota;
        }
        else{
            return 1f;
        }
    }

    public Iterable<Inregistrare> getAll(){return repoInreg.findAll();}
    public String getIdStudent(String string){
        String[] parts=string.split("[:)]");
        return parts[1];
    }

    public Integer getCurrentWeek() {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    /**
     * Se determina numarul laboratorului (tinand cont de vacanta de iarna)
     * @return dif (nurmarul laboratorului)
     */
    public Integer getLabNumber(){
        Integer dif=getCurrentWeek()-39;
        if(dif<1||dif>16) return null;
        if(getCurrentWeek().equals(13) || getCurrentWeek().equals(14))
            return 12;
        if(getCurrentWeek().equals(15)) return dif-1;
        if(getCurrentWeek().equals(16)) return dif-2;
        return dif;
    }

    public Integer getCurrentAssignment(){
        int currentLab=getLabNumber();
        List<Tema> lista= StreamSupport.stream(repoTema.findAll().spliterator(),false).
                filter(t->t.getPredare()<=currentLab&&currentLab<=t.getDeadline()).collect(Collectors.toList());
        return lista.get(0).getID();
    }

    public Inregistrare cautaNota(String idStudent, String idTema){
        List<Inregistrare> lista=StreamSupport.stream(repoInreg.findAll().spliterator(),false).
                filter(n->Integer.toString(n.getStudent()).equals(idStudent)&&Integer.toString(n.getTema()).equals(idTema)).collect(Collectors.toList());
        return lista.get(0);
    }

    public Integer getWeek(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    public Integer getWeekUni(Integer saptCurenta){
        Integer dif=saptCurenta-39;
        if(dif<1||dif>16) return null;
        if(saptCurenta.equals(13) || saptCurenta.equals(14))
            return 12;
        if(saptCurenta.equals(15)) return dif-1;
        if(saptCurenta.equals(16)) return dif-2;
        return dif;
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
        observers.forEach(x->x.update(t));
    }

    private <T> Iterable <T> filter(Iterable <T> list, Predicate<T> cond)
    {
        List<T> rez=new ArrayList<>();
        list.forEach((T x)->{if (cond.test(x)) rez.add(x);});
        return rez;
    }

    public Iterable<Inregistrare> byTema(String name) {
        return filter(repoInreg.findAll(), student -> Integer.toString(student.getTema()).contains(name));
    }


    public Iterable<Inregistrare> byStudent(String name) {
        return filter(repoInreg.findAll(), student -> Integer.toString(student.getStudent()).contains(name));
    }


    public Iterable<Inregistrare> byDescriere(String name) {
        return filter(repoInreg.findAll(), student -> Float.toString(student.getNota()).contains(name));
    }

    public void createPdf( String dest, String text)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BufferedReader br = new BufferedReader(new FileReader(text));
        String line;
        Paragraph p;
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        boolean title = true;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line, title ? bold : normal);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            title = line.isEmpty();
            document.add(p);
        }
        document.close();
    }

    public Iterable<Integer> toateTemele()
    {
        List<Integer> teme = new ArrayList<>();
        for(Tema t  : repoTema.findAll())
        {
            teme.add(t.getNrTema());
        }
        return teme;
    }
    public Iterable<Pair<Integer,Integer>> raportTemeNote()
    {
        List<Pair<Integer,Integer>> raport = new ArrayList<>();
        Iterable<Integer> teme = toateTemele();
        for(Integer tema:teme)
        {
            int nr=0;
            for(Inregistrare i : this.getAll())
            {
                if(i.getTema()==tema)
                    nr++;
            }
            raport.add(new Pair(tema,nr));
        }
        return raport;
    }

    public Iterable<Integer> toateGrupele()
    {
        Iterable<Inregistrare> note = repoInreg.findAll();
        List<Integer> grupe = new ArrayList<>();
        for(Inregistrare i : note)
        {
            if(grupe.indexOf(repoStud.findOne(i.getStudent()).getGrupa())==-1)
                grupe.add(repoStud.findOne(i.getStudent()).getGrupa());
        }
        return grupe;
    }
    public Iterable<Pair<Integer,Float>> raportMediaGrupe()
    {
        List<Pair<Integer,Float>> raport = new ArrayList<>();
        Iterable<Integer> grupe = toateGrupele();
        Iterable<Integer> tm = this.toateTemele();
        int nr=0;
        for (Integer i : tm)
        {
            nr++;
        }
        for(Integer grupa:grupe)
        {
            //int nr=0;
            float suma=0;
            for(Inregistrare i : this.getAll())
            {
                if(repoStud.findOne(i.getStudent()).getGrupa()==grupa) {
                    //nr++;
                    suma = suma+i.getNota();
                }
            }
            float media = suma/nr;
            raport.add(new Pair(grupa,media));
        }
        return raport;
    }
    public Iterable<Pair<Integer,Integer>> raportMediiStudenti()
    {
        List<Pair<Integer,Float>> raport = new ArrayList<>();
        List<Pair<Integer,Integer>> raport3 = new ArrayList<>();
        Iterable<Student> student = repoStud.findAll();
        Iterable<Integer> tm = this.toateTemele();
        int nr=0;
        for (Integer i : tm)
        {
            nr++;
        }
        for(Student st:student)
        {
            //int nr=0;
            float suma=0;
            for(Inregistrare i : this.getAll())
            {
                if(i.getStudent()==st.getIdStudent()) {
                    //nr++;
                    suma = suma+i.getNota();
                }
            }
            float media = suma/nr;
            raport.add(new Pair(st.getIdStudent(),media));
        }
        int nr1=0,nr2=0,nr3=0,nr4=0,nr5=0,nr6=0,nr7=0,nr8=0,nr9=0,nr10=0;
            for(Pair<Integer,Float> pereche:raport){
                if(pereche.getValue()>=0.5 && pereche.getValue()<1.5)
                    nr1++;
                if(pereche.getValue()>=1.5 && pereche.getValue()<2.5)
                    nr2++;
                if(pereche.getValue()>=2.5 && pereche.getValue()<3.5)
                    nr3++;
                if(pereche.getValue()>=3.5 && pereche.getValue()<4.5)
                    nr4++;
                if(pereche.getValue()>=4.5 && pereche.getValue()<5.5)
                    nr5++;
                if(pereche.getValue()>=5.5 && pereche.getValue()<6.5)
                    nr6++;
                if(pereche.getValue()>=6.5 && pereche.getValue()<7.5)
                    nr7++;
                if(pereche.getValue()>=7.5 && pereche.getValue()<8.5)
                    nr8++;
                if(pereche.getValue()>=8.5 && pereche.getValue()<9.5)
                    nr9++;
                if(pereche.getValue()>=9.5 && pereche.getValue()<=10)
                    nr10++;
            }
            raport3.add(new Pair(1,nr1));
            raport3.add(new Pair(2,nr2));
            raport3.add(new Pair(3,nr3));
            raport3.add(new Pair(4,nr4));
            raport3.add(new Pair(5,nr5));
            raport3.add(new Pair(6,nr6));
            raport3.add(new Pair(7,nr7));
            raport3.add(new Pair(8,nr8));
            raport3.add(new Pair(9,nr9));
            raport3.add(new Pair(10,nr10));
        return raport3;
    }
}
