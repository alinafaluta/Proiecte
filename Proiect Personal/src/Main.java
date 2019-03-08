
import Domain.Inregistrare;
import Domain.Student;
import Domain.Tema;
import Domain.User;
import Repository.*;
import Service.CatalogService;
import Service.StudentService;
import Service.TemeService;
import Service.UserService;
import View.MainController;
import View.StudentController;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        DataBaseHandler handler = new DataBaseHandler();
        DataBaseRepository<Integer, Student> repo = new StudentDBrepo(handler);
        DataBaseRepository<String, User> repoU = new UserDBrepo(handler);
        DataBaseRepository<Integer, Tema> repoT = new TemaDBrepo(handler);
        DataBaseRepository<Integer, Inregistrare> repoI = new InregistrareDBrepo(handler);
        StudentService studentService = new StudentService(repo);
        UserService userService = new UserService(repoU);
        TemeService temeService = new TemeService(repoT);
        CatalogService catalogService = new CatalogService(repoI,repo,repoT);
        //primaryStage.setIconified(true);
        primaryStage.getIcons().add(new Image("http://www.minifigure.org/wp-content/uploads/2012/09/minifigure-catalog-icon-512.png"));
        primaryStage.setTitle("Aplicatie");
        FXMLLoader loader = new FXMLLoader();
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/LoginView.fxml"));
        AnchorPane rootLayout = loader.load();
        MainController studentController = loader.getController();
        studentController.setService(studentService,userService,temeService,catalogService);
        //studentController.setService(studentService,userService);
        studentController.setStage(primaryStage);
        Scene myScene = new Scene(rootLayout);
        primaryStage.setScene(myScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
