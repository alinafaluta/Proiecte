package Repository;

import Domain.HasID;
import Domain.Validare.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public abstract class AbstractXmlRepository<ID, E extends HasID<ID>> extends InMemoryRepository<ID,E>{
    String fileName;

    public AbstractXmlRepository(String fileName, Validator<E> validator) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Citeste toate datele dintr-un fisier
     */
    private void loadData() {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(this.fileName);

            Element root = document.getDocumentElement();
            NodeList children = root.getChildNodes();
            for(int i=0; i < children.getLength(); i++) {
                Node el = children.item(i);
                if(el instanceof Element) {
                    E entity = createEntityFromElement((Element) el);
                    super.save(entity);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Construieste dintr-o linie citita din fisier o entitate selectand datele prin intermediul unui separator

     * @return o entitate construita pe baza datelor date
     */
    public abstract E createEntityFromElement(Element e);
    public abstract Element createElementfromEntity(Document document,E entity);

    /**
     * pe langa salvarea din InMemoryRepository se mai adauga scrierea in fisier a noii entitati
     */
    @Override
    public E save(E entity) {
        var stuff = super.save(entity);
        if (stuff == null){
            writeToFile();
        }

        return stuff;
    }
    /**
     * pe langa stergerea din InMemoryRepository se mai adauga rescrierea fisierului cu etitatile ramase
     */
    @Override
    public  E delete (ID id){
        E returnedEntity = super.delete(id);
        if (returnedEntity != null) {
            writeToFile();
        }
        return returnedEntity;
    }
    /**
     * pe langa update-ul din InMemoryRepository se mai adauga rescrierea fisierului cu etitatile modificate
     */
    @Override
    public  E update (E entity){
        E returnedEntity = super.update(entity);
        if (returnedEntity == null) {
            writeToFile();
        }
        return returnedEntity;
    }

    protected void writeToFile() {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);
            super.findAll().forEach(m->{
                Element e = createElementfromEntity(document,m);
                root.appendChild(e);
            });

            //write Document to file
            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult(fileName));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
