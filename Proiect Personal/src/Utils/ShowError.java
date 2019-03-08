package Utils;

import javafx.scene.control.Alert;

public interface ShowError {

    static void showMessage(Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);

        message.getDialogPane().setStyle("-fx-background-color:linear-gradient(#DAE3E9,#DAE3E9,#003244)");
        message.showAndWait();
    }

    static void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Eroare!!!!");
        message.setContentText(text);

        message.getDialogPane().setStyle("-fx-background-color:linear-gradient(#DAE3E9,#DAE3E9,#003244)");
        //message.getDialogPane().setStyle("-fx-header-color:#003244");
        message.getDialogPane();
        message.showAndWait();
    }
}
