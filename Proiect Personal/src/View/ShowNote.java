package View;

import Domain.Inregistrare;
import Domain.StudentDTO;
import Service.CatalogService;
import Service.StudentService;
import Service.TemeService;
import Service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

public class ShowNote {
    public TableView<StudentDTO> TabelCuNoteStudenti;
    public TableColumn NrTemaTabelNoteColumn;
    public TableColumn DescriereNoteColumn;
    public TableColumn ProfesorNotaColumn;
    public TableColumn NotaColumn;
    public TableColumn FeedbackNotaColumn;
    public TextField ShowMediaStud;
    public TextField temePredate;
    public TextField temeNePredate;
    String username;
    StudentService studentService;
    TemeService temeService;
    CatalogService catalogService;
    UserService userService;
    private Stage stage;
    private Scene primaryScene;
    public void setPrimaryScene(Scene mainScene) {
        this.primaryScene = mainScene;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStage(Stage primaryStage) {
        this.stage=primaryStage;
    }
    public void setService(StudentService s, TemeService t, UserService u, CatalogService c)
    {
        this.studentService =s;
        this.temeService=t;
        this.userService=u;
        this.catalogService=c;
        TabelCuNoteStudenti.getItems().clear();
        String user =this.username.replaceAll("[0-9]","");
        float media;
        float suma=0;
        int nrTeme=0;
        int predate=0;
        Iterable<Integer> gur = catalogService.toateTemele();
        for(Integer g :gur)
        {
            nrTeme++;
        }
        for(Inregistrare i : catalogService.getAll())
        {
            if(studentService.findStudent(i.getStudent()).getNume().equals(user))
            {
                Integer nrTema = i.getTema();
                String descriere = temeService.findTema(nrTema).getDescriere();
                Float nota = i.getNota();
                String profesor = studentService.findStudent(i.getStudent()).getProfesor();
                String feed = i.getFeedback();
                StudentDTO dto = new StudentDTO(nrTema,descriere,nota,profesor,feed);
                TabelCuNoteStudenti.getItems().add(dto);
                suma=suma+nota;
                predate++;
            }
        }
        media = suma/nrTeme;
        ShowMediaStud.setText(Float.toString(media));
        temePredate.setText(Integer.toString(predate));
        temeNePredate.setText(Integer.toString(nrTeme-predate));
    }
    public void backToLogin(ActionEvent actionEvent) throws IOException {
        stage.setTitle("Aplicatie");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/LoginView.fxml"));
        AnchorPane rootLayout = loader.load();
        MainController studentController = loader.getController();
        studentController.setService(studentService,userService,temeService,catalogService);
        //studentController.setService(studentService,userService);
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }

}
