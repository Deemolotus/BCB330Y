import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class UserInterface {

    @FXML
    private TextField UserInput = new TextField();

    @FXML
    private ChoiceBox<String> Selection = new ChoiceBox<>();

    @FXML
    private TextArea resultDisplay = new TextArea();

    private FileChooser fileChooser = new FileChooser();

    private Map<String, String> result;
    private Map<String, Integer> status;
    private Map<String, Integer> collection;
    private Dictionary dictionary;


    public void initialize() {
        Selection.setValue("miRNA");
        Selection.getItems().addAll(FXCollections.observableArrayList("miRNA","Motif"));

        resultDisplay.setEditable(false);
        resultDisplay.setText("Result Shows Here");

        fileChooser.setInitialFileName("hairpin.dot");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Dot Files", "*.dot"));

        dictionary = new Dictionary("hairpin.dot");
        result = dictionary.getMotifMap();
        status = dictionary.getMotifToNum();

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
            StringBuilder searchResult = Main.options(type, searchableItem, result, status);
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

    public void fileChooserOnAction(){
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            resultDisplay.setText("Loading " + selectedFile.getName());

            try{
            Main.writeToFile(result, status, collection);
            } catch (IOException e){
                resultDisplay.setText("Fail to write to file");
            }


        } else {
            resultDisplay.setText("File selection cancelled.");
        }
    }
}
