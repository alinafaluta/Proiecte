package Domain;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Tema implements HasID<Integer>{
    int nrTema;
    String descriere;
    int deadline;
    int predare;
    /**
     * constructor
     */
    public Tema(int nrTema, String descriere, int deadline, int predare) {
        this.nrTema = nrTema;
        this.descriere = descriere;
        this.deadline = deadline;
        this.predare = predare;
    }

    /**
     * Se determina saptamana curenta
     * @return saptamna curenta
     */
    private Integer SaptamanaCurenta() {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    /**
     * Se determina numarul laboratorului (tinand cont de vacanta de iarna)
     * @return dif (nurmarul laboratorului)
     */
    private Integer NumarulLaboratorului(){
        Integer saptCurenta=SaptamanaCurenta();
        Integer dif=saptCurenta-39;
        if(dif<1||dif>16) return null;
        if(saptCurenta.equals(13) || saptCurenta.equals(14))
            return 12;
        if(saptCurenta.equals(15)) return dif-1;
        if(saptCurenta.equals(16)) return dif-2;
        return dif;
    }
    /**
     * Returneaza un intreg ce reprezinta numarul temei curente
     */
    public int getNrTema() {
        return nrTema;
    }
    /**
     Setter pentru numarul temei
     */
    public void setNrTema(int nrTema) {
        this.nrTema = nrTema;
    }
    /**
     *Returneaza un string ce reprezinta descrierea temei curente
     */
    public String getDescriere() {
        return descriere;
    }
    /**
     * Setter pentru descriere
     */
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    /**
     * Returneaza un intreg ce reprezinta deadline-ul temei curente
     */
    public int getDeadline() {
        return deadline;
    }
    /**
     * Setter pentru deadline
     */
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    /**
     *Returneaza un intreg ce reprezinta termenul de predare al temei curente
     */
    public int getPredare() {
        return predare;
    }
    /**
     * Setter pentru termenul de predare
     */
    public void setPredare(int predare) {
        this.predare = predare;
    }
    /**
     * returneaza un intreg ce reprezinta id-ul entitatii curente
     */
    @Override
    public Integer getID() {
        return nrTema;
    }
    /**
     * Setter pentru id-ul entitatii curente
     */
    @Override
    public void setID(Integer integer) {
        this.nrTema = integer;
    }

    @Override
    public String toString() {
        return nrTema +","+ descriere + "," +deadline + "," + predare;
    }


}

