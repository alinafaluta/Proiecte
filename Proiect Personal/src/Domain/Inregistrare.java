package Domain;

public class Inregistrare implements HasID<Integer> {

    Integer idInregistrare;
    Integer tema;
    Integer student;
    Float nota;
    String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Inregistrare(Integer idInregistrare, Integer student,Integer tema, Float nota, String feedback) {
        this.idInregistrare = idInregistrare;
        this.tema = tema;
        this.student = student;
        this.nota = nota;
        this.feedback = feedback;
    }


    @Override
    public Integer getID() {
        return getIdInregistrare();
    }

    @Override
    public void setID(Integer integer) {
            setIdInregistrare(integer);
    }

    public Integer getIdInregistrare() {
        return idInregistrare;
    }

    public void setIdInregistrare(Integer idInregistrare) {
        this.idInregistrare = idInregistrare;
    }

    public Integer getTema() {
        return tema;
    }

    public void setTema(Integer tema) {
        this.tema = tema;
    }

    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }



    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return
                idInregistrare +
                "," +tema+
                ", " + student +
                "," + nota +","+feedback;
    }
}
