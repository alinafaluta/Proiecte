package Repository;

import Domain.Student;
import Domain.Validare.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLStudentRepo extends AbstractXmlRepository<Integer,Student>{


    public XMLStudentRepo(String fileName, Validator<Student> validator) {
        super(fileName, validator);
    }

    @Override
    public Student createEntityFromElement(Element e) {
        String studentId = e.getElementsByTagName("idStudent")
                .item(0).getTextContent();
            //NodeList nods = e.getChildNodes();
            String nume =e.getElementsByTagName("nume")
                    .item(0)
                    .getTextContent();

            String grupa =e.getElementsByTagName("grupa")
                    .item(0)
                    .getTextContent();

            String email =e.getElementsByTagName("email")
                    .item(0)
                    .getTextContent();

            String prof =e.getElementsByTagName("profesor")
                    .item(0)
                    .getTextContent();
            return new Student(Integer.parseInt(studentId),nume,Integer.parseInt(grupa),email,prof);
    }

    @Override
    public Element createElementfromEntity(Document document, Student entity) {
        Element e = document.createElement("student");
        Element id = document.createElement("idStudent");
        id.setTextContent(Integer.toString(entity.getID()));
        e.appendChild(id);

        Element nume = document.createElement("nume");
        nume.setTextContent(entity.getNume());
        e.appendChild(nume);

        Element grupa = document.createElement("grupa");
        grupa.setTextContent(Integer.toString(entity.getGrupa()));
        e.appendChild(grupa);

        Element email = document.createElement("email");
        email.setTextContent(entity.getEmail());
        e.appendChild(email);

        Element prof = document.createElement("profesor");
        prof.setTextContent(entity.getProfesor());
        e.appendChild(prof);
        return e;
    }
}


