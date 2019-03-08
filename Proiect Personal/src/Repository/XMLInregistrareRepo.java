package Repository;

import Domain.Inregistrare;
import Domain.Validare.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLInregistrareRepo extends AbstractXmlRepository<Integer, Inregistrare> {

    public XMLInregistrareRepo(String fileName, Validator<Inregistrare> validator) {
        super(fileName, validator);
    }

    @Override
    public Inregistrare createEntityFromElement(Element e) {
        String id = e.getElementsByTagName("idInregistrare").item(0).getTextContent();
        //NodeList nods = e.getChildNodes();
        String idTema =e.getElementsByTagName("tema")
                .item(0)
                .getTextContent();
        String idStudent =e.getElementsByTagName("sudent")
                .item(0)
                .getTextContent();
        String nota =e.getElementsByTagName("nota")
                .item(0)
                .getTextContent();
        String feedback =e.getElementsByTagName("feedback")
                .item(0)
                .getTextContent();
        return new Inregistrare(Integer.parseInt(id),Integer.parseInt(idTema),Integer.parseInt(idStudent),Float.parseFloat(nota),feedback);
    }

    @Override
    public Element createElementfromEntity(Document document, Inregistrare entity) {
        Element e = document.createElement("inregistrare");
        Element id = document.createElement("idInregistrare");
        id.setTextContent(Integer.toString(entity.getID()));
        e.appendChild(id);

        Element idStudent = document.createElement("tema");
        idStudent.setTextContent(Integer.toString(entity.getTema()));
        e.appendChild(idStudent);

        Element idTema = document.createElement("student");
        idTema.setTextContent(Integer.toString(entity.getStudent()));
        e.appendChild(idTema);

        Element nota = document.createElement("nota");
        nota.setTextContent(Double.toString(entity.getNota()));
        e.appendChild(nota);

        Element feedback = document.createElement("feedback");
        nota.setTextContent(entity.getFeedback());
        e.appendChild(feedback);
        return e;
    }
}
