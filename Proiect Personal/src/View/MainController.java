package View;

import Domain.User;
import Domain.UserType;
import Service.CatalogService;
import Service.StudentService;
import Service.TemeService;
import Service.UserService;
import Utils.ShowError;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.catalog.Catalog;
import java.io.IOException;

public class MainController implements ShowError {
    public Button ButonCONECTARE;
    public TextField UsernameTextField;
    public PasswordField PasswordTextField;

    StudentService studentService;
    UserService userService;
    TemeService temeService;
    CatalogService catalogService;

    private Stage stage;
    private Scene primaryScene;

    public void setPrimaryScene(Scene mainScene) {
        this.primaryScene = mainScene;
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }

    public void setService(StudentService s, UserService u , TemeService t, CatalogService c)
    {
        this.studentService=s;
        this.userService = u;
        this.temeService = t;
        this.catalogService = c;
    }
    public void handleConnect(ActionEvent actionEvent) {
        if(UsernameTextField.getText().equals(""))
            ShowError.showErrorMessage("Username-ul nu poate fi null");
        else
            if(PasswordTextField.getText().equals(""))
            {
                ShowError.showErrorMessage("Parola nu poate fi nula!");
            }
            else
                if(userService.UserExistent(UsernameTextField.getText())==false)
                {
                    ShowError.showErrorMessage("User-ul acesta nu exista!");
                }
                else
                    if(userService.ParolaValida(UsernameTextField.getText(),PasswordTextField.getText())==false)
                        ShowError.showErrorMessage("Parola incorecta");
                    else
                    {
                        User u =userService.findOne(UsernameTextField.getText());
                        if(u.getUserType()== UserType.profesor) {
                            try {
                                showProfesor();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(u.getUserType()== UserType.student) {
                            try {
                                showStudent();
                            } catch (IOException e) {
                                ShowError.showErrorMessage(e.getMessage());
                            }
                        }
                        if(u.getUserType()== UserType.secretar) {
                            try {
                                showSecretar();
                            } catch (IOException e) {
                            ShowError.showErrorMessage(e.getMessage());                            }
                        }
                    }

    }

    public void showSecretar() throws IOException {
        stage.getIcons().add(new Image("http://iconbug.com/data/f9/512/20fd7eb17ba1809f800849ef51b7e7ea.png"));
        stage.setTitle("Secretar access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/StudentView.fxml"));
        AnchorPane rootLayout = loader.load();
        StudentController studentController = loader.getController();
        studentController.setService(studentService,userService,temeService,catalogService);
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }
    public void showProfesor() throws IOException {
        stage.setTitle("Profesor access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/ChooseTemeSauNoteView.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        //studentController.setBarChar();
        //studentController.setPonderi();
        //studentController.setMedii();
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }
    public void showStudent() throws IOException {
        stage.getIcons().add(new Image("http://iconbug.com/data/f9/512/20fd7eb17ba1809f800849ef51b7e7ea.png"));
        stage.setTitle("Student access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/ShowNoteView.fxml"));
        AnchorPane rootLayout = loader.load();
        ShowNote studentController = loader.getController();
        studentController.setUsername(UsernameTextField.getText());
        studentController.setService(studentService,temeService,userService,catalogService);
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();

    }
}
