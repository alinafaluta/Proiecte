package View;

import Domain.*;
import Domain.Validare.ValidationException;
import Service.CatalogService;
import Service.StudentService;
import Service.TemeService;
import Service.UserService;
import Utils.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class ProfesorController implements Observer<Event>, ShowError, PDFCreator {
    public TableView<Tema> TableViewTeme = new TableView<>();
    public TableColumn LabelNrTema;
    public TableColumn DescriereC;
    public TableColumn Predarec;
    public TableColumn Deadline;
    public TextField DeadlineTextField;
    public Button AdaugaTemaButton;
    public Button UpdateTemaButton;
    public Button DeleteTemaButton;
    public Button BackButton2;
    public TextField PredareTextField;
    public TextField DescriereTextField;
    public TextField NrTemaTextField;
    public TableView<Tema> FiltrariTeme = new TableView<>();
    public TableColumn nrTemaFiltrari;
    public TableColumn descriereFiltrari;
    public TableColumn PredareFiltrari;
    public TableColumn DeadlineFiltrare;
    public TextField PredareinSapt;
    public TextField DeadlineinSapt;
    public Button FiltrareTemeButton;
    public Button BackButton;
    public CategoryAxis TemaBARA=new CategoryAxis();
    public NumberAxis StudentiBara=new NumberAxis();
    public BarChart BarChartCuStudentiTeme =new BarChart<>(TemaBARA,StudentiBara);
    public Button TemeButton;
    public Button CatalogButton;
    public TableView<InregistrareDTO> TabelCuNote=new TableView<>();
    public Button ButonShowStudenti;
    public ComboBox idStudentCOMBObox=new ComboBox();
    public Button ButonShowTEME;
    public ComboBox idTemaCombobox=new ComboBox();
    public TextField NotaTextField;
    public Button AdaugaNotaBUtton;
    public Button filtreazaNoteButon;
    public ComboBox FiltrareStudentiSUSComboBox = new ComboBox();
    public Button Primii10Button;
    public Button MediaSub5Button;
    public TextArea FeedbackTextArea;
    public Button backButonDinNote;
    public PieChart TemePredatePieChart = new PieChart();
    public CategoryAxis grupaBara = new CategoryAxis();
    public NumberAxis mediaBara= new NumberAxis();
    public BarChart MediaGrupelorBarChart=new BarChart<>(grupaBara,mediaBara);
    public Button exportRapoarteNotePDF;
    public Button backDinRapoarteNoteButton;
    public CheckBox motivatchehc;
    public TableView<Student> TabelCuStudenti=new TableView<>();
    public TableView<Tema> TabelCuTeme=new TableView<>();
    public AnchorPane ConnectBoard;
    public ComboBox SaptamanaCOmbo;
    public Button backTOLogin;
    public Button ArataToateNoteleButon;
    public ComboBox FiltrezStudentiJMK = new ComboBox();
    public ComboBox AutoooooStudent = new ComboBox();


    StudentService studentService;
    TemeService temeService;
    CatalogService catalogService;
    UserService userService;
    private ObservableList<Student> modelStudent;
    private ObservableList<Tema> modelTema;
    private ObservableList<Inregistrare> modelInreg;
    private ObservableList<InregistrareDTO> modelInregDTO;

    private Stage stage;
    private Scene primaryScene;
    public void setStudentModel(ObservableList<Student> m)
    {
        this.modelStudent = m;
        TabelCuStudenti.setItems(m);
    }
    public void setTemeModel(ObservableList<Tema> t)
    {
        this.modelTema=t;
        TabelCuTeme.setItems(t);
    }
    public void setPrimaryScene(Scene mainScene) {
        this.primaryScene = mainScene;
    }

    public void setStage(Stage primaryStage) {
        this.stage=primaryStage;
    }

    public void setService(StudentService s, TemeService t, CatalogService c,UserService u)
    {
        this.userService =u;
        this.studentService=s;
       // studentService.addObserver(this);
        this.temeService=t;
        temeService.addObserver(this);
        this.catalogService=c;
        catalogService.addObserver(this);
        List<Student> list= StreamSupport.stream(studentService.getAll().spliterator(), false)
                .collect(Collectors.toList());
        modelStudent= FXCollections.observableArrayList(list);
        List<Tema> listt= StreamSupport.stream(temeService.getAll().spliterator(), false)
                .collect(Collectors.toList());
        modelTema= FXCollections.observableArrayList(listt);
        List<Inregistrare> listi= StreamSupport.stream(catalogService.getAll().spliterator(), false)
                .collect(Collectors.toList());
        modelInreg= FXCollections.observableArrayList(listi);
        List<InregistrareDTO> dto =new ArrayList<>();
        for (Inregistrare task:catalogService.getAll()) {
            Integer idSt = studentService.findStudent(task.getStudent()).getIdStudent();
            String nume = studentService.findStudent(task.getStudent()).getNume();
            Integer grupa = studentService.findStudent(task.getStudent()).getGrupa();
            Integer nrTema = temeService.findTema(task.getTema()).getNrTema();
            Float nota = task.getNota();
            String feed =task.getFeedback();
            InregistrareDTO i =new InregistrareDTO(idSt,nume,grupa,nrTema,nota,feed);
            dto.add(i);
        }
        modelInregDTO=FXCollections.observableArrayList(dto);
        initData();
        //initComboBoxSubject();
        List<Integer> list1=StreamSupport.stream(studentService.getAll().spliterator(), false)
                .map(m->m.getIdStudent())
                .collect(Collectors.toList());
        idStudentCOMBObox.getItems().addAll(list1);
        new AutoCompleteComboBoxListener<String>(idStudentCOMBObox);
        List<Integer> listGR=StreamSupport.stream(studentService.toateGrupele().spliterator(), false)
                .collect(Collectors.toList());
        FiltrareStudentiSUSComboBox.getItems().addAll(listGR);
       // FiltrareStudentiSUSComboBob

        List<String> listStudi=StreamSupport.stream(studentService.getAll().spliterator(), false)
                .map(m->m.getNume())
                .collect(Collectors.toList());
        AutoooooStudent.getItems().addAll(listStudi);
        new AutoCompleteComboBoxListener<String>(AutoooooStudent);

        List<Integer> listt1=StreamSupport.stream(temeService.getAll().spliterator(), false)
                .map(m->m.getNrTema())
                .collect(Collectors.toList());
        idTemaCombobox.getItems().addAll(listt1);
        new AutoCompleteComboBoxListener<String>(idTemaCombobox);
        setBarChar();
        setMedii();
        setPonderi();
    }

    @FXML
    private void initialize(){
        //cu inregistrareDTO
        try {
            TabelCuNote.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> showINREGdtoDetails(newValue));
            //cu Student
            TabelCuStudenti.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> showStudentDetails(newValue));
            //cu Tema
            TabelCuTeme.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> showTemaTABELASDetails(newValue));
            //cu Tema
            TableViewTeme.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> showTemaDetails(newValue));
            //cu Tema
            FiltrariTeme.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> showTemaDetails(newValue));
        }catch(NullPointerException e){}
    }


    private void initData() {
        TabelCuStudenti.getItems().clear();
        for(Student s :studentService.getAll())
        {
            TabelCuStudenti.getItems().add(s);
        }
        TableViewTeme.getItems().clear();
        for(Tema s :temeService.getAll())
        {
            TableViewTeme.getItems().add(s);
        }
        TabelCuTeme.getItems().clear();
        for(Tema s :temeService.getAll())
        {
            TabelCuTeme.getItems().add(s);
        }
        FiltrariTeme.getItems().clear();
        for(Tema s :temeService.getAll())
        {
            FiltrariTeme.getItems().add(s);
        }
        TabelCuNote.getItems().clear();
        for (Inregistrare task:catalogService.getAll()) {
            Integer idSt = studentService.findStudent(task.getStudent()).getIdStudent();
            String nume = studentService.findStudent(task.getStudent()).getNume();
            Integer grupa = studentService.findStudent(task.getStudent()).getGrupa();
            Integer nrTema = temeService.findTema(task.getTema()).getNrTema();
            Float nota = task.getNota();
            String feed =task.getFeedback();
            InregistrareDTO i =new InregistrareDTO(idSt,nume,grupa,nrTema,nota,feed);
            TabelCuNote.getItems().add(i);
        }
    }

    @Override
    public void update(Event event) {

        modelStudent.setAll(StreamSupport.stream(studentService.getAll().spliterator(),false)
                .collect(Collectors.toList()));
        TabelCuStudenti.setItems(modelStudent);
        modelTema.setAll(StreamSupport.stream(temeService.getAll().spliterator(),false)
                .collect(Collectors.toList()));
        TabelCuTeme.setItems(modelTema);
        TableViewTeme.setItems(modelTema);
        modelInreg.setAll(StreamSupport.stream(catalogService.getAll().spliterator(),false)
                .collect(Collectors.toList()));
        List<InregistrareDTO> dto =new ArrayList<>();
        for (Inregistrare task:catalogService.getAll()) {
            Integer idSt = studentService.findStudent(task.getStudent()).getIdStudent();
            String nume = studentService.findStudent(task.getStudent()).getNume();
            Integer grupa = studentService.findStudent(task.getStudent()).getGrupa();
            Integer nrTema = temeService.findTema(task.getTema()).getNrTema();
            Float nota = task.getNota();
            String feed =task.getFeedback();
            InregistrareDTO i =new InregistrareDTO(idSt,nume,grupa,nrTema,nota,feed);
            dto.add(i);
        }
        modelInregDTO.setAll(StreamSupport.stream(dto.spliterator(),false).collect(Collectors.toList()));
        TabelCuNote.setItems(modelInregDTO);
        setMedii();
        setBarChar();

    }
    private void showTemaTABELASDetails(Tema newValue) {
        if(newValue==null)
            idTemaCombobox.setValue("");
        else
            idTemaCombobox.setValue(newValue.getNrTema());
    }

    private void showTemaDetails(Tema newValue) {
        if(newValue==null)
        {
            NrTemaTextField.setText("");
            DescriereTextField.setText("");
            PredareTextField.setText("");
            DeadlineTextField.setText("");
        }
        else
        {
            NrTemaTextField.setText(Integer.toString(newValue.getNrTema()));
            DescriereTextField.setText(newValue.getDescriere());
            PredareTextField.setText(Integer.toString(newValue.getPredare()));
            DeadlineTextField.setText(Integer.toString(newValue.getDeadline()));
        }
    }

    private void showStudentDetails(Student newValue) {
        if(newValue==null)
            idStudentCOMBObox.setValue("");
        else
            idStudentCOMBObox.setValue(newValue.getID());
    }

    private void showINREGdtoDetails(InregistrareDTO newValue) {
        if(newValue == null)
        {
            idStudentCOMBObox.setValue("");
            idTemaCombobox.setValue("");
            NotaTextField.setText("");
            FeedbackTextArea.setText("");
        }else
        {
            idStudentCOMBObox.setValue(newValue.getIdStudent());
            idTemaCombobox.setValue(newValue.getTema());
            NotaTextField.setText(Float.toString(newValue.getNota()));
            FeedbackTextArea.setText(newValue.getFeedback());
        }
    }

    public void handleFiltrare(ActionEvent actionEvent) {
        try {
            if (PredareinSapt.getText().toString().equals("")) {
                if (DeadlineinSapt.getText().toString().equals("")) {
                    //showErrorMessage("Filtrarea nu se poate face daca campurile sunt goale!");
                    FiltrariTeme.getItems().clear();
                    for (Tema task : temeService.getAll()) {
                        FiltrariTeme.getItems().add(task);
                    }
                } else {
                    Iterable<Tema> lista = temeService.getAll();
                    FiltrariTeme.getItems().clear();
                    int nr = 0;
                    for (Tema task : temeService.getAll()) {
                        if (task.getDeadline() == Integer.parseInt(DeadlineinSapt.getText().toString())) {
                            FiltrariTeme.getItems().add(task);
                            nr++;
                        }
                    }
                    if (nr == 0) {
                        FiltrariTeme.getItems().clear();
                        for (Tema task : temeService.getAll()) {
                            FiltrariTeme.getItems().add(task);
                        }
                    }
                }
            } else {
                if (DeadlineinSapt.getText().toString().equals("")) {
                    Iterable<Tema> lista = temeService.getAll();
                    FiltrariTeme.getItems().clear();
                    int nr = 0;
                    for (Tema task : temeService.getAll()) {
                        if (task.getPredare() == Integer.parseInt(PredareinSapt.getText().toString())) {
                            FiltrariTeme.getItems().add(task);
                            nr++;
                        }
                    }
                    if (nr == 0) {
                        FiltrariTeme.getItems().clear();
                        for (Tema task : temeService.getAll()) {
                            FiltrariTeme.getItems().add(task);
                        }
                    }
                } else {
                    Iterable<Tema> lista = temeService.getAll();
                    FiltrariTeme.getItems().clear();
                    int nr = 0;
                    for (Tema task : temeService.getAll()) {
                        if (task.getDeadline() == Integer.parseInt(DeadlineinSapt.getText().toString()) && task.getPredare() == Integer.parseInt(PredareinSapt.getText().toString())) {
                            FiltrariTeme.getItems().add(task);
                            nr++;
                        }
                    }
                    if (nr == 0) {
                        FiltrariTeme.getItems().clear();
                        for (Tema task : temeService.getAll()) {
                            FiltrariTeme.getItems().add(task);
                        }
                    }
                }
            }
        }
        catch (NumberFormatException e)
        {
            FiltrariTeme.getItems().clear();
            for (Tema task : temeService.getAll()) {
                FiltrariTeme.getItems().add(task);
            }
        }
    }

    public void BackToMain(ActionEvent actionEvent) throws IOException {
        //stage.getIcons().add(new Image("http://iconbug.com/data/f9/512/20fd7eb17ba1809f800849ef51b7e7ea.png"));
        stage.setTitle("Profesor access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/ChooseTemeSauNoteView.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }

    public void handleAddTema(ActionEvent actionEvent) {
        try{
            Tema t =extractTema();
            temeService.addTema(t);
            setBarChar();
            setMedii();
        } catch (SQLException e) {
            ShowError.showErrorMessage(e.getMessage());
        }catch (ValidationException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
        catch (NumberFormatException e)
        {
            ShowError.showErrorMessage("Date introduse gresit. Mentineti mouse-ul pe campuri pt a vedea tipul de date corect");
        }
    }

    public void handleUpdateTema(ActionEvent actionEvent) {
        try {
            Tema t = extractTema();
            temeService.updateTema(t);

        }catch (ValidationException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
        catch (NumberFormatException e)
        {
            ShowError.showErrorMessage("Date introduse gresit. Mentineti mouse-ul pe campuri pt a vedea tipul de date corect");
        }
    }

    public void handleDeleteTema(ActionEvent actionEvent) {
        try {
            Tema t = extractTema();
            temeService.deleteTema(t.getID());
            setBarChar();
            setMedii();
        }catch (ValidationException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }
    }

    public void handleAdaugaTemeDinConnect(ActionEvent actionEvent) throws IOException {
        stage.setTitle("Profesor access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/TemeView.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        studentController.setBarChar();
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }

    public void handleAdaugaNoteDinConnect(ActionEvent actionEvent) throws IOException {
        stage.setTitle("Profesor access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/NoteView.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        //studentController.setMedii();
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }

    public void handleButonShowStudenti(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/ShowStudenti.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        studentController.setStudentModel(modelStudent);
       // studentController.setStage(stage);
        Stage newStage = new Stage();
        Scene myScene = new Scene(rootLayout);
        newStage.setTitle("Profesor access");
        newStage.getIcons().add(new Image("http://www.minifigure.org/wp-content/uploads/2012/09/minifigure-catalog-icon-512.png"));
        newStage.setScene(myScene);
        newStage.setResizable(false);
        newStage.show();
    }

    public void handleButonShowTeme(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/ShowTeme.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        studentController.setTemeModel(modelTema);
        // studentController.setStage(stage);
        Stage newStage = new Stage();
        Scene myScene = new Scene(rootLayout);
        newStage.setTitle("Profesor access");
        newStage.getIcons().add(new Image("http://www.minifigure.org/wp-content/uploads/2012/09/minifigure-catalog-icon-512.png"));
        newStage.setScene(myScene);
        newStage.setResizable(false);
        newStage.show();
    }

    public void handleAddNota(ActionEvent actionEvent) {
        try {
            if (NotaTextField.getText().contains(",")) {
                ShowError.showErrorMessage("Nota se scrie in formatul 10.0, cu -punct- nu cu virgula-");
            } else{
                if(SaptamanaCOmbo.getValue()==null) {
                    ShowError.showErrorMessage("Selectati Saptamana");
                }
                else{
                if(motivatchehc.isSelected()) {
                    catalogService.adaugaNota(Integer.parseInt(idStudentCOMBObox.getValue().toString()), Integer.parseInt(idTemaCombobox.getValue().toString()), LocalDateTime.now().toString(), Float.parseFloat(NotaTextField.getText()), FeedbackTextArea.getText(), true);
                    ShowError.showMessage(Alert.AlertType.INFORMATION,"Succes!","Nota a fost adaugata cu succes.");
                    setBarChar();
                    setMedii();
                    setPonderi();
                }
                else {
                    catalogService.adaugaNota(Integer.parseInt(idStudentCOMBObox.getValue().toString()), Integer.parseInt(idTemaCombobox.getValue().toString()), SaptamanaCOmbo.getValue().toString(), Float.parseFloat(NotaTextField.getText()), FeedbackTextArea.getText(), false);
                    ShowError.showMessage(Alert.AlertType.INFORMATION,"Succes!","Nota a fost adaugata cu succes !");
                    setBarChar();
                    setMedii();
                    setPonderi();
                }}}
        } catch (ValidationException e) {
            ShowError.showErrorMessage(e.getMessage());
        }catch (NullPointerException e)
        {
            ShowError.showErrorMessage(e.getMessage());
        }catch (SQLException e) {
            ShowError.showErrorMessage("Date gresite");
        }
    }

    public void handleFiltrareNote(ActionEvent actionEvent) {
        String numeStudent;
        if(AutoooooStudent.getSelectionModel().getSelectedItem()==null)
            numeStudent = "";
        else
            numeStudent=AutoooooStudent.getSelectionModel().getSelectedItem().toString();

        Predicate<InregistrareDTO> studentPredicate = m -> m.getNume().contains(numeStudent);

        List<InregistrareDTO> dto =new ArrayList<>();
        for (Inregistrare task:catalogService.getAll()) {
            Integer idSt = studentService.findStudent(task.getStudent()).getIdStudent();
            String nume = studentService.findStudent(task.getStudent()).getNume();
            Integer grupa = studentService.findStudent(task.getStudent()).getGrupa();
            Integer nrTema = temeService.findTema(task.getTema()).getNrTema();
            Float nota1 = task.getNota();
            String feed1 =task.getFeedback();
            InregistrareDTO i =new InregistrareDTO(idSt,nume,grupa,nrTema,nota1,feed1);
            dto.add(i);
        }

        modelInregDTO.setAll(StreamSupport.stream(dto.spliterator(),false)
                .filter(studentPredicate)
                .collect(Collectors.toList()));
        TabelCuNote.setItems(modelInregDTO);
    }

    public void handleFiltrareStudenti(ActionEvent actionEvent) {
        try {
            if (FiltrareStudentiSUSComboBox.getValue().equals("Toate grupele")||FiltrareStudentiSUSComboBox.getValue().equals("")) {
                modelStudent.setAll(StreamSupport.stream(studentService.allStudents().spliterator(), false)
                        .collect(Collectors.toList()));
                this.setStudentModel(modelStudent);
                TabelCuStudenti.setItems(modelStudent);
            }
          else{
                Iterable<Student> students = studentService.byGroupe(FiltrareStudentiSUSComboBox.getValue().toString());
                List<Student> list = StreamSupport.stream(students.spliterator(), false)
                        .collect(Collectors.toList());
                modelStudent = FXCollections.observableArrayList(list);
                TabelCuStudenti.setItems(modelStudent);
            }


        }catch (NullPointerException e)
        {
            ShowError.showErrorMessage("Nu se poate realiza filtrarea!");
        }
    }


    public void handlePrimii10(ActionEvent actionEvent) {
        List<Integer> studenti = new ArrayList<>();
        List<Pair<Integer,Float>> raport = new ArrayList<>();
        List<Pair<Integer,Float>> raportSub5 = new ArrayList<>();
        for(Student s : studentService.getAll())
        {
            studenti.add(s.getIdStudent());
        }
        Iterable<Integer> teme = catalogService.toateTemele();
        int nr =0;
        for(Integer t : teme)
        {
            nr++;
        }
        for(Integer i :studenti)
        {
            float suma =0;
            //int nr= 0;
            for(Inregistrare in : catalogService.getAll())
            {
                if(in.getStudent()==i)
                {
                    suma=suma+in.getNota();
                    //nr++;
                }
            }
            float media = suma/nr;
            raport.add(new Pair(i,media));
        }
        raport.sort(new Comparator<Pair<Integer,Float>>() {
            @Override
            public int compare(Pair<Integer,Float> m1, Pair<Integer,Float> m2) {
                if (m1.getValue() == m2.getValue()) {
                    return 0;
                } else if (m1.getValue() > m2.getValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        String text = "                    Studentii cu Media 10\n";
        for(Pair<Integer,Float> pereche :raport)
        {
            if(pereche.getValue()==10)
                text=text+pereche.getKey()+"."+studentService.findStudent(pereche.getKey()).getNume()+"  "+studentService.findStudent(pereche.getKey()).getGrupa()+"("+pereche.getValue()+")\n";
        }
        try {
            PDFCreator.createPdf("Primii10.pdf", text);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Export","Exportul a fost realizat cu succes in fisierul Primii10.pdf");
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

    public void handleMediaSub5(ActionEvent actionEvent) {
        List<Integer> studenti = new ArrayList<>();
        List<Pair<Integer,Float>> raport = new ArrayList<>();
        List<Pair<Integer,Float>> raportSub5 = new ArrayList<>();
        for(Student s : studentService.getAll())
        {
            studenti.add(s.getIdStudent());
        }
        Iterable<Integer> teme = catalogService.toateTemele();
        int nr =0;
        for(Integer t : teme)
        {
            nr++;
        }
        for(Integer i :studenti)
        {
            float suma =0;
            //int nr= 0;
            for(Inregistrare in : catalogService.getAll())
            {
                if(in.getStudent()==i)
                {
                    suma=suma+in.getNota();
                   // nr++;
                }
            }
            float media = suma/nr;
            raport.add(new Pair(i,media));
        }
        for(Pair<Integer,Float> pereche :raport)
        {
            if(pereche.getValue()<5)
            {
                raportSub5.add(pereche);
            }
        }
        raportSub5.sort(new Comparator<Pair<Integer,Float>>() {
            @Override
            public int compare(Pair<Integer,Float> m1, Pair<Integer,Float> m2) {
                if (m1.getValue() == m2.getValue()) {
                    return 0;
                } else if (m1.getValue() < m2.getValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        String text = "                    Studentii cu media sub nota 5\n";
        for(Pair<Integer,Float> pereche :raportSub5)
        {
            text=text+pereche.getKey()+"."+studentService.findStudent(pereche.getKey()).getNume()+"  "+studentService.findStudent(pereche.getKey()).getGrupa()+"("+pereche.getValue()+")\n";
        }
        try {
            PDFCreator.createPdf("StudentiSub5.pdf", text);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Export","Exportul a fost realizat cu succes in fisierul StudentiSub5.pdf");
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


    public void handleBackDInNote(ActionEvent actionEvent) throws IOException {
        //stage.getIcons().add(new Image("http://iconbug.com/data/f9/512/20fd7eb17ba1809f800849ef51b7e7ea.png"));
        stage.setTitle("Profesor access");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/ChooseTemeSauNoteView.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        studentController.setStage(stage);
        Scene myScene = new Scene(rootLayout);
        stage.setScene(myScene);
        stage.setResizable(false);
        stage.show();
    }
//    private static void ShowError.showMessage(Alert.AlertType type, String header, String text){
//        Alert message=new Alert(type);
//        message.setHeaderText(header);
//        message.setContentText(text);
//        message.showAndWait();
//    }
//
//    private static void ShowError.showErrorMessage(String text){
//        Alert message=new Alert(Alert.AlertType.ERROR);
//        message.setTitle("Eroare!!!!");
//        message.setContentText(text);
//
//        message.getDialogPane().setStyle("-fx-background-color:#e0e0e0");
//        message.showAndWait();
//    }

    public void handleExportRapoarte(ActionEvent actionEvent) {
        Iterable<Pair<Integer,Float>> grupele = catalogService.raportMediaGrupe();
        String text ="                                 Media grupelor           \n\n";
        for(Pair<Integer,Float> pereche : grupele)
        {
            text=text+"        Grupa "+pereche.getKey()+"  are media  "+pereche.getValue()+"\n";
        }
        try {
            PDFCreator.createPdf("MediaGrupelor.pdf",text);
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Succes","Raportul a fost exportat in fisierul MediaGrupelor.pdf");
        } catch (DocumentException e) {
            ShowError.showErrorMessage(e.getMessage());
        } catch (IOException e) {
           ShowError.showErrorMessage(e.getMessage());
        }catch (ValidationException e) {
            ShowError.showMessage(Alert.AlertType.INFORMATION,"Informatie",e.getMessage());
        }

    }

    public void handleBackDinRapoarteNote(ActionEvent actionEvent) throws IOException {
        stage.setTitle("Aplicatie");
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        //loader.setLocation(getClass().getResource("/View/LoginView.fxml"));
        //AnchorPane rootLayout = loader.load();
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

    public Tema extractTema()
    {
        try{
            return new Tema(Integer.parseInt(NrTemaTextField.getText()),DescriereTextField.getText(),Integer.parseInt(DeadlineTextField.getText()),Integer.parseInt(PredareTextField.getText()));
        }catch (NullPointerException e) {
            ShowError.showErrorMessage(e.getMessage());
        }
        return null;
    }

    public Inregistrare extractInregistrare(){
        try{
            return new Inregistrare(1,Integer.parseInt(idStudentCOMBObox.getValue().toString()),Integer.parseInt(idTemaCombobox.getValue().toString()),Float.parseFloat(NotaTextField.getText()),FeedbackTextArea.getText());
        }catch (NullPointerException e) {
            ShowError.showErrorMessage(e.getMessage());
        }
        return null;
    }

    public void setBarChar(){
       // BarChartCuStudentiTeme = new BarChart<String,Number>(TemaBARA,StudentiBara);
        // eleviPerProfesorBarChart = new BarChart<Number, String>(Studentiaxa,ProfesorBaraAxa);
        BarChartCuStudentiTeme.getData().clear();
        Iterable<Pair<Integer,Integer>> temele = catalogService.raportTemeNote();
        TemaBARA.setLabel("idTeme");
        StudentiBara.setLabel("Studenti");
        StudentiBara.setTickLabelRotation(90);

        for(Pair<Integer,Integer> pereche : temele)
        {
           // XYChart.Data series1 = new XYChart.Data(pereche.getKey().toString(),pereche.getValue());
            XYChart.Series series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(pereche.getKey().toString(),pereche.getValue()));
            BarChartCuStudentiTeme.getData().add(series1);
        }
        // series1.getData().add(new XYChart.Data("performance",80));

    }

    public void setMedii()
    {
        MediaGrupelorBarChart.getData().clear();
       // MediaGrupelorBarChart = new BarChart<>(grupaBara,mediaBara);
        Iterable<Pair<Integer,Float>> grupele = catalogService.raportMediaGrupe();
        grupaBara.setLabel("Grupa");
        mediaBara.setLabel("Meedia");
        mediaBara.setTickLabelRotation(90);
        for(Pair<Integer,Float> pereche : grupele)
        {
            XYChart.Series series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(pereche.getKey().toString(),pereche.getValue()));
            MediaGrupelorBarChart.getData().add(series1);
        }

    }
    public void setPonderi(){
        TemePredatePieChart.getData().clear();
        //TemePredatePieChart = new PieChart();
        Iterable<Pair<Integer,Integer>> grupe = catalogService.raportMediiStudenti();
        for(Pair<Integer,Integer> pereche : grupe)
        {
            TemePredatePieChart.getData().add(new PieChart.Data(Integer.toString(pereche.getKey()),pereche.getValue()));
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


    public void handleArataToateNotele(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/View/StudentView.fxml"));
        loader.setLocation(getClass().getResource("/View/NoteFiltrare.fxml"));
        AnchorPane rootLayout = loader.load();
        ProfesorController studentController = loader.getController();
        studentController.setService(studentService,temeService,catalogService,userService);
        studentController.setTemeModel(modelTema);
        // studentController.setStage(stage);
        Stage newStage = new Stage();
        Scene myScene = new Scene(rootLayout);
        newStage.setTitle("Note");
        newStage.getIcons().add(new Image("http://www.minifigure.org/wp-content/uploads/2012/09/minifigure-catalog-icon-512.png"));
        newStage.setScene(myScene);
        newStage.setResizable(false);
        newStage.show();
    }
}

//    public static <K, V> void generatePDF(String fileName, Map<K,V> map){
//        OutputStream file = null;
//        Stage stage = new Stage ();
//        if(fileName.equals("")){
//            return;
//        }
//        try {
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            directoryChooser.setTitle("Open Resource File");
//            File selectedDirectory = directoryChooser.showDialog(stage);
//            if(selectedDirectory == null) //daca nu e selectat un director
//                return;
//
//            String path = selectedDirectory.toPath().toString().replace( '\\','/');
//
//            File filePath = new File(path+"/"+fileName+".pdf");
//            if(filePath.exists()){
//                filePath.delete();
//            }
//            file = new FileOutputStream(filePath);
//
//            Document document = new Document();
//            PdfWriter.getInstance(document, file);
//
//            document.open();
//
//            document.addTitle("Raport");
//            map.forEach((k,v)-> {
//                PdfPTable table = new PdfPTable(2);
//                PdfPCell cell1 = new PdfPCell(new Paragraph(new Paragraph(k.toString() )));
//                PdfPCell cell2 = new PdfPCell(new Paragraph(new Paragraph(" Media: "+v.toString())));
//                table.addCell(cell1);
//                table.addCell(cell2);
//                try {
//                    document.add(table);
//                } catch (DocumentException e) {
//                    e.printStackTrace();
//                }
//
//            });
//
//            //document.add(new Paragraph("Hello World, iText"));
//
//            /// document.add(new Paragraph(new Date().toString()));
//
//
//            document.close();
//
//            file.close();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
