package Domain;

public class Student implements HasID<Integer> {
    private Integer idStudent;
    private String nume;
    private int grupa;
    private String email;
    private String profesor;

    public Student(Integer idStudent, String nume, int grupa, String email, String profesor) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email = email;
        this.profesor = profesor;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getNume() {
        return nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public String getEmail() {
        return email;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return idStudent +
                "," + nume  +
                "," + grupa +
                "," + email  +
                "," + profesor;
    }


    @Override
    public Integer getID() {
        return this.idStudent;
    }

    @Override
    public void setID(Integer integer) {
        this.idStudent = integer;
    }
}
