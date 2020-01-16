import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.IOException;

public class UserInterface {

    @FXML
    private TextField UserInput = new TextField();

    @FXML
    private ChoiceBox<String> Selection = new ChoiceBox<>();

    @FXML
    private TextArea resultDisplay = new TextArea();

    public void initialize() {
        Selection.setValue("mRNA");
        Selection.getItems().addAll(FXCollections.observableArrayList("mRNA","Motif"));

        resultDisplay.setEditable(false);
        resultDisplay.setText("Result Shows Here");

        setBackground();
    }

    private void setBackground() {

        Image image = new Image(this.getClass().getResource("mrnabackground.png").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        GraphicMain.root.setBackground(background);
    }

    public void databaseSearch(){

        String searchableItem = UserInput.getText();

        if (searchableItem.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter First!");
            alert.setHeaderText("Invalid Input");
            alert.showAndWait();
        } else {
            String type = Selection.getValue();
            StringBuilder searchResult = Main.options(type, searchableItem);
            if (searchResult == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nothing is found under search for " + searchableItem);
                alert.setHeaderText("Invalid Input");
                alert.showAndWait();
            } else {
                resultDisplay.setText(searchResult.toString());
            }
        }


    }
}
