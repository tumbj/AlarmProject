package sample.Controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.AlarmClock;
import sample.Model.Clock;
import sample.Model.Time;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static sample.Controller.AddPageController.addPageStage;
import static sample.Controller.AnswerDialogPageController.ansStage;


public class HomePageController implements Runnable{

    @FXML
    private TableView<RecordTable> tableView;

    @FXML
    private TableColumn<RecordTable, String> dateColumn;

    @FXML
    private TableColumn<RecordTable, String> timeColumn;

    @FXML
    private TableColumn<RecordTable, CheckBox> isWantColumn;

    @FXML
    private ChoiceBox<Integer> minuteSelect;

    @FXML
    private ChoiceBox<Integer> hourSelect;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button  updateBtn;

    @FXML
    private Button  deleteBtn;
    @FXML
    private Button addBtn;
    int index =-1;

    AlarmClock alarmClock= new AlarmClock();

    public static ObservableList<RecordTable> data = FXCollections.observableArrayList();
    Time time = new Time();

    @FXML
    public void initialize(){
        minuteSelect.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,
                28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59);
        hourSelect.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
        dateColumn.setCellValueFactory(new PropertyValueFactory<RecordTable,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<RecordTable,String>("time"));
        isWantColumn.setCellValueFactory(new PropertyValueFactory<RecordTable,CheckBox>("isWant"));

        tableView.getColumns().setAll(dateColumn,timeColumn,isWantColumn);
        tableView.setItems(data);

        for (Clock clock:alarmClock.allAlarm) {
            data.add(new RecordTable(clock.toStringDate(),clock.toStringTime()));
        }
        this.start();

        try {
            tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    datePicker.setValue(LOCAL_DATE(tableView.getSelectionModel().getSelectedItem().getDate()));
                    System.out.println(tableView.getSelectionModel().getSelectedItem().getDate());
                    String[]  tmpTime =tableView.getSelectionModel().getSelectedItem().getTime().split(":");
                    hourSelect.setValue(Integer.parseInt(tmpTime[0]));
                    minuteSelect.setValue(Integer.parseInt(tmpTime[1]));


                }
            });
        }catch (NullPointerException ne){
            ne.printStackTrace();
        }


    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
    @FXML
    public void onActionDeleteBtn(ActionEvent e){
        for (RecordTable recordTable:data) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            if (recordTable.getDate().equals(datePicker.getValue().format(formatter)) && recordTable.getTime().equals(hourSelect.getValue() + ":" + minuteSelect.getValue())) {
                data.remove(recordTable);
                System.out.println("delete");
                break;
            }
            System.out.println(datePicker.getValue());
        }
    }


    public  LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(dateString, formatter);
    }
    @FXML
    void addBtnOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/addPage.fxml"));
            addPageStage.setTitle("Add alarm");
            addPageStage.setScene(new Scene(loader.load(), 588, 173));
            addPageStage.setX(0);
            addPageStage.setY(0);
            addPageStage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateTo(){


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AnswerDialogPage.fxml"));
            ansStage.setTitle("Alarm!!!!!");
            ansStage.setScene(new Scene(loader.load(), 478, 353));
            ansStage.setX(0);
            ansStage.setY(0);
            ansStage.show();

            AnswerDialogPageController controller = (AnswerDialogPageController) loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int i =0;
        if(!data.isEmpty()) {
            while (true) {
                if (i >= data.size()) {
                    i = 0;
                }
                if (data.get(i).isUse) {
                    System.out.println(data.get(i).isUse);

                    String[] tmpMinNow = (time.getTime()[1]).split("\\+"); //0 is hour ,1 is min
                    String tmpYearNow = time.getDate()[0];
                    String tmpMonthNow = time.getDate()[1];
                    String tmpDayNow = time.getDate()[2];
                    String[] tmpDate = data.get(i).getDate().split("/");
                    String[] tmpTime = data.get(i).getTime().split(":");
                    int min = Integer.parseInt(tmpMinNow[0]);
                    if ((Integer.parseInt(tmpYearNow) == Integer.parseInt(tmpDate[0])) &&
                            (Integer.parseInt(tmpMonthNow) == Integer.parseInt(tmpDate[1])) &&
                            (Integer.parseInt(tmpDayNow) == Integer.parseInt(tmpDate[2]))) {
                        if (Integer.parseInt(time.getTime()[0]) == Integer.parseInt(tmpTime[0])) {
                            if (min >= Integer.parseInt(tmpTime[1])) {
                                data.get(i).setUse(false);

                                Platform.runLater(() -> navigateTo());
                                CheckBox checkBox = new CheckBox();
                                checkBox.setSelected(false);
                                data.get(i).setIsWant(checkBox);
                            }
                            if (data.size() == 0) {
                                break;
                            }

                        }
                    }

                }
                System.out.println(data.get(i).isUse);

//            System.out.println((allAlarm.get(i).getHour()) +"----" + (allAlarm.get(i).getMinute())+":::::"+ time.getTime()[0]+" "+time.getTime()[1]);
                i++;
            }
        }
    }

    public static class RecordTable{
        private SimpleStringProperty date;
        private SimpleStringProperty time;
        private boolean isUse;
        private CheckBox isWant;

        public RecordTable(String date, String time, boolean isUse) {
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.isUse = isUse;
            isWant = new CheckBox();
            isWant.setSelected(false);

        }
        public RecordTable(String date, String time) {
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.isUse = false;
            isWant = new CheckBox();
            isWant.setSelected(isUse);

            isWant.setOnAction( e->{
                if(isUse){
                    isUse = false;
                }else{
                    isUse =true;
                }
            });
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public String getTime() {
            return time.get();
        }

        public SimpleStringProperty timeProperty() {
            return time;
        }

        public boolean isUse() {
            return isUse;
        }

        public CheckBox getIsWant() {
            return isWant;
        }

        public void setUse(boolean use) {
            isUse = use;
        }

        public void setIsWant(CheckBox isWant) {
            this.isWant = isWant;
        }
    }

}
