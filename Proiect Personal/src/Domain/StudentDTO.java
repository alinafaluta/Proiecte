package Domain;

public class StudentDTO {
    Integer nrTema;
    String descriere;
    Float nota;
    String profesor;
    String  feedback;

    public StudentDTO(Integer nrTema, String descriere, Float nota, String profesor, String feedback) {
        this.nrTema = nrTema;
        this.descriere = descriere;
        this.nota = nota;
        this.profesor = profesor;
        this.feedback = feedback;
    }

    public Integer getNrTema() {
        return nrTema;
    }

    public void setNrTema(Integer nrTema) {
        this.nrTema = nrTema;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
