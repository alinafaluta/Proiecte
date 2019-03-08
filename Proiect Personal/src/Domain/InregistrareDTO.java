package Domain;

public class InregistrareDTO {
    Integer idStudent;
    String nume;
    Integer grupa;
    Integer tema;
    Float nota;
    String feedback;

    public InregistrareDTO(Integer idStudent, String nume, Integer grupa, Integer tema, Float nota, String feedback) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.tema = tema;
        this.nota = nota;
        this.feedback = feedback;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getGrupa() {
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }

    public Integer getTema() {
        return tema;
    }

    public void setTema(Integer tema) {
        this.tema = tema;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
