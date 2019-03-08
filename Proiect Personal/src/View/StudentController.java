package View;

import Domain.Student;
import Domain.User;
import Domain.UserType;
import Domain.Validare.ValidationException;
import Service.CatalogService;
import Service.StudentService;
import Service.TemeService;
import Service.UserService;
import Utils.Observer;
import Utils.PDFCreator;
import Utils.ShowError;
import Utils.StudentChangeEvent;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.apache.derby.client.am.SqlException;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentController implements Observer<StudentChangeEvent>, ShowError, PDFCreator {
    public AnchorPane PrimaryStudentAnchorPane;
    public TableView<Student> TabelCuStudenti = new TableView<>();
    public TableColumn idStudent;
    public TableColumn NumeStudent;
    public TableColumn  GrupaStudent;
    public TableColumn ProfesorStudent;
    public TableColumn emailStudent;
    public ChoiceBox GrupaChoiceBox;
    public TextField NumeStudentTextField;
    public TextField ProfesorTextField;
    public TextField EmailTextField;
    public Button saveStudentButton;
    public Button DeleteStudentButton;
    public Button UpdateStudentButton;
    public Button FindStudentButton;
    public Button ExportaPDFStudent;
    public TableView<User> TabelUser = new TableView<>();
    public TableColumn <User, String>UsernameColumn;
    public TableColumn <User, String>PasswordColumn;
    public TableColumn<User, String> UserTypeColumn;
    public TextField UserNameTextField;
    public TextField PasswordTextField;
    public ChoiceBox userTypeTextField;
    public Button ExportPDFTableUser;
    public Button addUserButton;
    public Button UpdateUserButton;
    public Button deleteUserButton;
    public TextField idStudentTextField;
    public ComboBox FilterStudentComboBox;
    public ComboBox FiltrareUserComboBOx;
    public TextField NumeFisierUserPDF;
    public TextField NumeFisierStudentPDF;
    public Button AfisareRapoarteButton;
    public PieChart studentiInGrupePieChart;
    public BarChart eleviPerProfesorBarChart;
    public CategoryAxis ProfesorBaraAxa;
    public NumberAxis Studentiaxa;
    public Button ExportRapoarte;

    StudentService studentService;
    UserService userService;
    TemeService temeService;
    CatalogService catalogService;
    private ObservableList<Student> model;
    private ObservableList<User> modelUser;

    private Stage stage;
    private Scene primaryScene;

    public void setPrimaryScene(Scene mainScene) {
        this.primaryScene = mainScene;
    }

    public void setStage(Stage primaryStage) {
        this.stage=primaryStage;
    }

    public void setService(StudentService service, UserService userService, TemeService t,CatalogService c){
        this.temeService = t;
        this.catalogService=c;
        this.studentService = service;
        studentService.addObserver(this);
        this.userService=userService;
        List<Student> list= StreamSupport.stream(studentService.getAll().spliterator(), false)
                .collect(Collectors.toList());
        model= FXCollections.observableArrayList(list);
        List<User> listt= StreamSupport.stream(userService.getAll().spliterator(), false)
                .collect(Collectors.toList());
        modelUser= FXCollections.observableArrayList(listt);
        initData();

    }
@FXML
    private void initialize(){

//        idStudent.setCellValueFactory(new PropertyValueFactory<>("idStudent"));
//        NumeStudent.setCellValueFactory(new PropertyValueFactory<>("nume"));
//        GrupaStudent.setCellValueFactory(new PropertyValueFactory<>("grupa"));
//        ProfesorStudent.setCellValueFactory(new PropertyValueFactory<>("profesor"));
//        emailStudent.setCellValueFactory(new PropertyValueFactory<>("email"));
        TabelCuStudenti.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldvalue,newValue)->showStudentDetails(newValue) );

//        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
//        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
//        UserTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        TabelUser.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldvalue,newValue)->showUserDetails(newValue) );;
    }
    private void initData() {
        TabelCuStudenti.getItems().clear();
        for (Student task:studentService.getAll()) {
            TabelCuStudenti.getItems().add(task);
        }

        TabelUser.getItems().clear();
        for (User task:userService.getAll()) {
            TabelUser.getItems().add(task);
        }
    }
    @Override
    public void update(StudentChangeEvent studentChangeEvent) {
        switch (studentChangeEvent.getType()){
            case ADD:{ TabelCuStudenti.getItems().add(studentChangeEvent.getData());}
                //FiltrariTeme.getItems().add(studentChangeEvent.getData());break;}
            case DELETE:{TabelCuStudenti.getItems().remove(studentChangeEvent.getData());}
                //FiltrariTeme.getItems().remove(studentChangeEvent.getData());break;}
            case UPDATE: {// TabelCuStudenti.getItems().remove(studentChangeEvent.getOldData());
                //TabelCuStudenti.getItems().add(studentChangeEvent.getData());}
                //FiltrariTeme.getItems().remove(studentChangeEvent.getOldData());
                //FiltrariTeme.getItems().add(studentChangeEvent.getData());break;
            }
        }
    }

    public void handleAdaugaStudent(ActionEvent actionEvent) {
        Student s = extractStudentforADD();
        if (s.getProfesor()==null||s.getNume()==null||s.getEmail()==null)
            ShowError.showErrorMessage("Datele nu pot fi nule!");
        try {
            studentService.addStudent(s);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Salvare efectuata cu succes!","Studentul a fost adaugat.");
            userService.addUser(s.getNume()+s.getIdStudent(),"stud"+s.getIdStudent()+"ir"+s.getGrupa(),UserType.student);
            TabelUser.getItems().clear();
            for (User task:userService.getAll()) {
                TabelUser.getItems().add(task);
            }
            TabelCuStudenti.getItems().clear();
            for (Student task:studentService.getAll()) {
                TabelCuStudenti.getItems().add(task);
            }

        } catch (ValidationException e) {
           ShowError.showErrorMessage(e.getMessage());
        } catch (SQLException var3) {
        if (!var3.getMessage().contains("duplicate key value")) {
            ShowError.showErrorMessage("Unable to execute add query.\n" + var3.getMessage());
        }
    }
    }

    public void handleStergeStudent(ActionEvent actionEvent) {
        Student s = extractStudentforDelete();
        if (s.getProfesor()==null||s.getNume()==null||s.getEmail()==null)
            ShowError.showErrorMessage("Datele nu pot fi nule!");
        try {
            studentService.deleteStudent(s.getIdStudent());
            userService.deleteUser(s.getNume()+s.getIdStudent());
            TabelCuStudenti.getItems().clear();
            for (Student task:studentService.getAll()) {
                TabelCuStudenti.getItems().add(task);
            }
            TabelUser.getItems().clear();
            for (User task:userService.getAll()) {
                TabelUser.getItems().add(task);
            }
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Stergere efectuata cu succes!","Studentul a fost Sters.");

        } catch (ValidationException e) {
          ShowError.showErrorMessage(e.getMessage());
        }
    }

    public void handleModificaStudent(ActionEvent actionEvent) {
        Student s = extractStudentforDelete();
        if (s.getProfesor()==null||s.getNume()==null||s.getEmail()==null)
            ShowError.showErrorMessage("Datele nu pot fi nule!");
        try {
            studentService.updateStudent(s);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Modificare efectuata cu succes!","Studentul a fost modificat cu succes.");
            TabelCuStudenti.getItems().clear();
            for (Student task:studentService.getAll()) {
                TabelCuStudenti.getItems().add(task);
            }
        } catch (ValidationException e) {
            ShowError.showErrorMessage(e.getMessage());
        }
    }

    public void handleCautaStudent(ActionEvent actionEvent) {
        try {
            String id = idStudentTextField.toString();
            Student s = studentService.findStudent(Integer.parseInt(id));
            showStudentDetails(s);
        }
        catch (ValidationException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }catch(NumberFormatException e)
        {
            ShowError.showErrorMessage("Id invalid");
        }
    }

//    public static void createPdf(String dest, String text)
//            throws DocumentException, IOException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(dest));
//        document.open();
//        BufferedReader br = new BufferedReader(new StringReader(text));
//        String line;
//        Paragraph p;
//        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
//        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//        boolean title = true;
//        while ((line = br.readLine()) != null) {
//            p = new Paragraph(line, title ? bold : normal);
//            p.setAlignment(Element.ALIGN_JUSTIFIED);
//            title = line.isEmpty();
//            document.add(p);
//        }
//        document.close();
//    }

    public void handleExportPDFstudent(ActionEvent actionEvent) throws IOException, DocumentException {
        String text = "                                                Export PDF Tabel Studenti                        \n";
        Iterator<Student> studs =model.iterator();
        while (studs.hasNext())
        {
            text=text +studs.next().toString()+"\n";
        }
        try {
            if (NumeFisierStudentPDF.getText() != null) {
                PDFCreator.createPdf(NumeFisierStudentPDF.getText() + ".pdf", text);
                ShowError.showMessage(Alert.AlertType.INFORMATION, "Salvare Date", "Datele au fost salvate in " + NumeFisierStudentPDF.getText());
            } else {
                PDFCreator.createPdf("Studenti.pdf", text);
                ShowError.showMessage(Alert.AlertType.INFORMATION, "Salvare Date", "Datele au fost salvate automat in Studenti.pdf");

            }
        }catch(IOException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }catch (DocumentException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
            catch (ValidationException e)
        {
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Informatie",e.getMessage());
        }
    }

    public void handleExportPDFuser(ActionEvent actionEvent) throws IOException, DocumentException {
        String text = "          Export PDF Tabel Users            \n";
        Iterator<User> studs =modelUser.iterator();
        while (studs.hasNext())
        {
            text=text +studs.next().toString()+"\n";
        }
        if(NumeFisierUserPDF.getText()!=null) {
            PDFCreator.createPdf(NumeFisierUserPDF.getText()+".pdf", text);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Salvare Date","Datele au fost salvate  in "+NumeFisierUserPDF.getText());

        }
        else
        {
            PDFCreator.createPdf("Useri.pdf", text);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Salvare Date","Datele au fost salvate automat in Useri.pdf");

        }

    }

    public void handleAdaugaUser(ActionEvent actionEvent) {
        User u = extractUser();
        if (u.getPassword().equals(null)||u.getUsername().equals(null)||u.getUserType().equals(null))
            ShowError.showErrorMessage("Datele nu pot fi nule!");
        try {
            userService.addUser(u.getUsername(),u.getPassword(),u.getUserType());
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Salvare efectuata cu succes!","Userul a fost adaugat.");
            TabelUser.getItems().clear();
            for (User task:userService.getAll()) {
                TabelUser.getItems().add(task);
            }
        } catch (ValidationException e) {
            ShowError.showErrorMessage(e.getMessage());
        } catch (SQLException e) {
            ShowError.showErrorMessage(e.getMessage());
        }
    }

    public void handleModificaUser(ActionEvent actionEvent) {
    }

    public void handleStergeUser(ActionEvent actionEvent) {
        try{
            userService.deleteUser(UserNameTextField.getText());
            TabelUser.getItems().clear();
            for (User task:userService.getAll()) {
                TabelUser.getItems().add(task);
            }
        }
        catch (ValidationException e){
            ShowError.showErrorMessage(e.getMessage());
        }
    }

    public void showStudentDetails(Student value) {
        if (value==null)
        {
            idStudentTextField.setText("");
           NumeStudentTextField.setText("");
           GrupaChoiceBox.setValue("");
           ProfesorTextField.setText("");
           EmailTextField.setText("");

        }
        else
        {
            idStudentTextField.setText(Integer.toString(value.getID()));
            NumeStudentTextField.setText(value.getNume());
            GrupaChoiceBox.setValue(Integer.toString(value.getGrupa()));
            ProfesorTextField.setText(value.getProfesor());
            EmailTextField.setText(value.getEmail());
        }
    }

    public void showUserDetails(User s)
    {
        if(s==null)
        {
            UserNameTextField.setText("");
            PasswordTextField.setText("");
            userTypeTextField.setValue("");
        }
        else {
            UserNameTextField.setText(s.getUsername());
            PasswordTextField.setText("");
            userTypeTextField.setValue(s.getUserType());
        }
    }
    protected UserType getType(String s)
    {
        if(s.equals("secretar"))
            return UserType.secretar;
        if(s.equals("profesor"))
            return UserType.profesor;
        if(s.equals("student"))
            return UserType.student;
        return null;
    }
    private Student extractStudentforADD(){
        try {
            String id = "99999";
            String nume = NumeStudentTextField.getText();
            String grupa = GrupaChoiceBox.getValue().toString();
            String email = EmailTextField.getText();
            String prof = ProfesorTextField.getText();
            return new Student(Integer.parseInt(id),nume,Integer.parseInt(grupa),email,prof);
        }
        catch (NumberFormatException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
            return null;
    }
    private Student extractStudentforDelete(){
        try {
            String id = idStudentTextField.getText();
            String nume = NumeStudentTextField.getText();
            String grupa = GrupaChoiceBox.getValue().toString();
            String email = EmailTextField.getText();
            String prof = ProfesorTextField.getText();
            return new Student(Integer.parseInt(id),nume,Integer.parseInt(grupa),email,prof);
        }
        catch (NumberFormatException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
        return null;
    }

    private User extractUser(){
        String username = UserNameTextField.getText();
        String pas = PasswordTextField.getText();
        String userType = userTypeTextField.getValue().toString();
        return new User(username,pas,getType(userType));
    }
    public void handleFilterStudent(ActionEvent actionEvent) {
        try {
            if (FilterStudentComboBox.getValue().equals("Nimic")) {
                model.setAll(StreamSupport.stream(studentService.allStudents().spliterator(), false)
                        .collect(Collectors.toList()));
                TabelCuStudenti.setItems(model);
            }
            if (FilterStudentComboBox.getValue().equals("") || NumeStudentTextField.getText().equals("") || GrupaChoiceBox.getValue().equals(null) || ProfesorTextField.getText().equals(null) || EmailTextField.getText().equals(null)) {
                model.setAll(StreamSupport.stream(studentService.allStudents().spliterator(), false)
                        .collect(Collectors.toList()));
                TabelCuStudenti.setItems(model);
            }
            if (FilterStudentComboBox.getValue().equals("Nume")) {
                Iterable<Student> students = studentService.byName(NumeStudentTextField.getText());
                List<Student> list = StreamSupport.stream(students.spliterator(), false)
                        .collect(Collectors.toList());
                model = FXCollections.observableArrayList(list);
                TabelCuStudenti.setItems(model);
            }
            if (FilterStudentComboBox.getValue().equals("Grupa")) {
                Iterable<Student> students = studentService.byGroupe(GrupaChoiceBox.getValue().toString());
                List<Student> list = StreamSupport.stream(students.spliterator(), false)
                        .collect(Collectors.toList());
                model = FXCollections.observableArrayList(list);
                TabelCuStudenti.setItems(model);
            }
            if (FilterStudentComboBox.getValue().equals("Profesor")) {
                Iterable<Student> students = studentService.byTeacher(ProfesorTextField.getText());
                List<Student> list = StreamSupport.stream(students.spliterator(), false)
                        .collect(Collectors.toList());
                model = FXCollections.observableArrayList(list);
                TabelCuStudenti.setItems(model);
            }
            if (FilterStudentComboBox.getValue().equals("Email")) {
                Iterable<Student> students = studentService.byEmail(EmailTextField.getText());
                List<Student> list = StreamSupport.stream(students.spliterator(), false)
                        .collect(Collectors.toList());
                model = FXCollections.observableArrayList(list);
                TabelCuStudenti.setItems(model);
            }
        }catch (NullPointerException e)
        {
            ShowError.showErrorMessage("Nu se poate realiza filtrarea!");
        }

    }

    public void handleFiltrareUser(ActionEvent actionEvent) {
        try {
            if (FiltrareUserComboBOx.getValue().equals("")) {
                modelUser.setAll(StreamSupport.stream(userService.allUsers().spliterator(), false)
                        .collect(Collectors.toList()));
                TabelUser.setItems(modelUser);
            }
            if (FiltrareUserComboBOx.getValue().equals("nimic")) {
                modelUser.setAll(StreamSupport.stream(userService.allUsers().spliterator(), false)
                        .collect(Collectors.toList()));
                TabelUser.setItems(modelUser);
            }
            if (FiltrareUserComboBOx.getValue().equals("student")) {
                Iterable<User> users = userService.Student();
                List<User> list = StreamSupport.stream(users.spliterator(), false)
                        .collect(Collectors.toList());
                modelUser = FXCollections.observableArrayList(list);
                TabelUser.setItems(modelUser);
            }
            if (FiltrareUserComboBOx.getValue().equals("profesor")) {
                Iterable<User> users = userService.Profesor();
                List<User> list = StreamSupport.stream(users.spliterator(), false)
                        .collect(Collectors.toList());
                modelUser = FXCollections.observableArrayList(list);
                TabelUser.setItems(modelUser);
            }
            if (FiltrareUserComboBOx.getValue().equals("secretar")) {
                Iterable<User> users = userService.Secretar();
                List<User> list = StreamSupport.stream(users.spliterator(), false)
                        .collect(Collectors.toList());
                modelUser = FXCollections.observableArrayList(list);
                TabelUser.setItems(modelUser);
            }
        }catch (NullPointerException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
    }

    public void handleRapoarteAfisare(ActionEvent actionEvent) throws IOException {
        StudentService serviceDeSt = studentService;
        UserService userService1 = userService;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/RaportStudentiView.fxml"));
        AnchorPane root = loader.load();
        StudentController editMessageViewController = loader.getController();
        editMessageViewController.setService(serviceDeSt,userService1,temeService,catalogService);
        editMessageViewController.setBarChar();
        editMessageViewController.setPieChart();
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Rapoarte");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        //dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        dialogStage.show();

    }
    public void setBarChar(){
       // eleviPerProfesorBarChart = new BarChart<Number, String>(Studentiaxa,ProfesorBaraAxa);
        Iterable<Pair<String,Integer>> profesorii = studentService.raportStudetiProfi();
        ProfesorBaraAxa.setLabel("Profesori");
        Studentiaxa.setLabel("Studenti");
        Studentiaxa.setTickLabelRotation(90);

        for(Pair<String,Integer> pereche : profesorii)
        {
            XYChart.Series series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(pereche.getKey(),pereche.getValue()));
            eleviPerProfesorBarChart.getData().add(series1);
        }
       // series1.getData().add(new XYChart.Data("performance",80));

    }
    public void setPieChart(){
        Iterable<Pair<Integer,Integer>> grupe = studentService.raportStudentiGrupe();
        for(Pair<Integer,Integer> pereche : grupe)
        {
            studentiInGrupePieChart.getData().add(new PieChart.Data(Integer.toString(pereche.getKey()),pereche.getValue()));
        }
    }

    public void handleExportRapoarte(ActionEvent actionEvent)  {
        String text = "                                                    Rapoarte Studenti                 \n" +"\n**Raport Studenti per Profesor\n";
        Iterable<Pair<String,Integer>> profesorii = studentService.raportStudetiProfi();
        for(Pair<String,Integer> pereche : profesorii)
        {
            text=text + pereche.getKey()+":"+pereche.getValue()+"\n";
        }
        text = text+"\n**Raport cu grupele si numarul de studenti din fiecare grupa\n";
        Iterable<Pair<Integer,Integer>> grupe = studentService.raportStudentiGrupe();
        for(Pair<Integer,Integer> pereche : grupe)
        {
            text=text + pereche.getKey()+":"+pereche.getValue()+"\n";
        }
        try {
            PDFCreator.createPdf("Raport.pdf", text);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Export","Exportul a fost realizat cu succes in fisierul Raport.pdf");
        }catch(IOException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }catch (DocumentException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
        catch (ValidationException e)
        {
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Informatie",e.getMessage());
        }
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
