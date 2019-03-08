package Repository;

import Domain.Tema;
import Domain.Validare.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLTemeRepo extends AbstractXmlRepository<Integer, Tema> {

    public XMLTemeRepo(String fileName, Validator<Tema> validator) {
        super(fileName, validator);
    }

    @Override
    public Tema createEntityFromElement(Element e) {
        String nrTema = e.getElementsByTagName("idTema")
                .item(0).getTextContent();
        //NodeList nods = e.getChildNodes();
        String descriere =e.getElementsByTagName("descriere")
                .item(0)
                .getTextContent();

        String deadline =e.getElementsByTagName("deadline")
                .item(0)
                .getTextContent();

        String predare =e.getElementsByTagName("predare")
                .item(0)
                .getTextContent();

        return new Tema(Integer.parseInt(nrTema),descriere,Integer.parseInt(deadline),Integer.parseInt(predare));
    }

    @Override
    public Element createElementfromEntity(Document document, Tema entity) {
        Element e = document.createElement("tema");
        Element id = document.createElement("idTema");
        id.setTextContent(Integer.toString(entity.getID()));
        e.appendChild(id);

        Element nume = document.createElement("descriere");
        nume.setTextContent(entity.getDescriere());
        e.appendChild(nume);

        Element grupa = document.createElement("deadline");
        grupa.setTextContent(Integer.toString(entity.getDeadline()));
        e.appendChild(grupa);

        Element email = document.createElement("predare");
        email.setTextContent(Integer.toString(entity.getPredare()));
        e.appendChild(email);
        return e;
    }
}
