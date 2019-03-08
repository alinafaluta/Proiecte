package Repository;

import Domain.User;
import Domain.UserType;
import Domain.Validare.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLUserRepo extends AbstractXmlRepository<String, User> {

    public XMLUserRepo(String fileName, Validator<User> validator) {
        super(fileName, validator);
    }

    @Override
    public User createEntityFromElement(Element e) {
        String username = e.getElementsByTagName("username").item(0).getTextContent();
        String password = e.getElementsByTagName("password").item(0).getTextContent();
        String userType = e.getElementsByTagName("userType").item(0).getTextContent();
        if(userType.equals("secretar"))
            return new User(username,password,UserType.secretar);
        if(userType.equals("profesor"))
            return new User(username,password,UserType.profesor);
        if(userType.equals("student"))
            return new User(username,password,UserType.student);
        return new User(username,password,UserType.student);
    }

    @Override
    public Element createElementfromEntity(Document document, User entity) {
        Element e = document.createElement("user");
        Element username = document.createElement("username");
        username.setTextContent(entity.getUsername());
        e.appendChild(username);
        Element pass = document.createElement("password");
        username.setTextContent(entity.getPassword());
        e.appendChild(pass);
        Element type = document.createElement("userType");
        username.setTextContent(entity.getUserType().toString());
        e.appendChild(type);
        return e;
    }
}
