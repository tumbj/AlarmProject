package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class AddPageController {


    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<Integer> minuteSelect;

    @FXML
    private ChoiceBox<Integer> hourSelect;

    @FXML
    private Button submitBtn;

    static Stage addPageStage = new Stage();
    @FXML
    void submitBtnOnAction(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        HomePageController.data.add(new HomePageController.RecordTable(datePicker.getValue().format(formatter),(hourSelect.getValue())+":" +(minuteSelect.getValue())));
        addPageStage.close();
    }

    @FXML
    void initialize(){
        minuteSelect.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,
                28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59);
        hourSelect.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);


    }


}
