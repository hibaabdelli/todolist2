package com.example.todolist;


import com.example.todolist.Task.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox vbox ;
    @FXML
    private TextField tfSearch ;
    @FXML
    private Button add;
    @FXML
    private Button b1;
    @FXML
    private VBox vbox2 ;

    TaskListImpl taskes = new TaskListImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vbox.getChildren().clear();
        try {
            displayAllTasks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*private void rowHBox(String name, String discription, Complet state)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rowtask.fxml"));
        HBox row = fxmlLoader.load() ;
        Label namel =  (Label) row.getChildren().get(0) ;
        Label statl =  (Label) row.getChildren().get(1) ;
        CheckBox statc = (CheckBox) row.getChildren().get(2) ;
        Button delete = (Button) row.getChildren().get(3) ;
        Button edit = (Button) row.getChildren().get(4) ;


        namel.setText(name) ;
        statl.setText(state.toString()) ;
        if (state == Complet.not_complated) {
            statc.setSelected(false);
        } else if (state == Complet.complated) {
            statc.setSelected(true);
        }


        row.getChildren().clear();
        row.getChildren().add(namel) ;
        row.getChildren().add(statl);
        row.getChildren().add(statc);
        row.getChildren().add(delete);
        row.getChildren().add(edit);

        vbox.getChildren().add(row);
    }*/
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$



    public void createTodoRow(String task, Complet state,Taskk taskh ) {
        // Create HBox
        HBox hbox = new HBox();
        hbox.setPrefSize(300, 45);
        hbox.setStyle("-fx-background-color: #51717b; -fx-background-radius: 2em;");

        // Create Labels
        Label taskLabel = new Label(task);
        taskLabel.setPrefSize(120, 45);
        taskLabel.setTextFill(javafx.scene.paint.Color.web("#f2eeee"));
        taskLabel.setFont(new Font(17));
        taskLabel.setAlignment(javafx.geometry.Pos.CENTER);

        Label stateLabel = new Label(state.toString());
        stateLabel.setPrefSize(115, 45);
        stateLabel.setTextFill(javafx.scene.paint.Color.web("#f2ebeb"));
        stateLabel.setFont(new Font(15));
        stateLabel.setAlignment(javafx.geometry.Pos.CENTER);

        // Create CheckBox
        CheckBox checkBox = new CheckBox();
        if(taskh.getState().equals(Complet.complated)){checkBox.setSelected(true);System.out.println("fuch");}
        else checkBox.setSelected(false);
        checkBox.setPrefSize(30, 45);
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                taskh.markAsCompleted();
                taskes.editTask(taskh.getId_T(), taskh);
                stateLabel.setText("completed");
            } else {
                taskh.markAsNotComplated();
                taskes.editTask(taskh.getId_T(), taskh);
                stateLabel.setText(state.toString());
            }
        });


        /*checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                taskh.markAsCompleted();
                stateLabel.setText("completed");
            } else {
                taskh.markAsNotComplated();
                stateLabel.setText(state.toString()); // Reset to original state
            }
            taskes.editTask(taskh.getId_T(), taskh);
        });*/




        // Create Buttons
        Button deleteButton = new Button("delite");
        deleteButton.setPrefSize(57, 45);
        deleteButton.setStyle("-fx-background-color: #3366cc; -fx-background-radius: 2em;");
        deleteButton.setOnAction(event -> {
            // Handle task deletion
            taskes.deleteTask(taskh);

            // Remove the HBox from the VBox
            vbox.getChildren().remove(hbox);
        });

        /* Button editButton = new Button("edit");
        editButton.setPrefSize(54, 46);
        editButton.setStyle("-fx-background-color: #66ff99; -fx-background-radius: 2em;");*/

        //editButton.setOnAction(event -> onEdit());*/
        //$$$$$$$$$$$$$$$$$$$$$$$$$$
        hbox.setOnMouseClicked(event -> {
            try {
                taskes.editTask(taskh.getId_T(), taskh);
                taskes.deleteTask(taskh);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
                Parent root = loader.load();
                // desplay information of tesk on second page
                Taskcontroller page2 = loader.getController();
                page2.DisplayInfo(taskh);
                // Get the Stage from the Event
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("task");
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        //taskes.editTask(taskh.getId_T(), taskh);
        //$$$$$$$$$$$$$$$$$$$$$$$$$$
        // Add children to HBox
        hbox.getChildren().addAll(taskLabel, stateLabel, checkBox, deleteButton);
        vbox.getChildren().add(hbox);
    }



    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    public void displayAllTasks() throws IOException {

        vbox.getChildren().clear();
        for (Taskk task : taskes.displayTasks()){
            //rowHBox(task.getName(),task.getDescription(),task.getState());
            createTodoRow(task.getName(),task.getState(),task);
        }
    }
    /*public void displayAllTasks(ArrayList<Taskk> tasks) throws IOException {

        vbox.getChildren().clear();
        for (Taskk task : tasks){
            createTodoRow(task.getName(),task.getState(),task);
        }
    }*/


    public void ADDB(ActionEvent event)throws IOException {
        try {
            // Load the FXML file for Page 2
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("task");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void edit(ActionEvent event)throws IOException {
        try {
            // Load the FXML file for Page 2
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("task");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    protected void searshh() throws IOException {
        ArrayList<Taskk> searshingTasks = taskes.search(tfSearch.getText());
        System.out.println("hhhhhhhhhh");
        displayAllTasks1(searshingTasks);
    }
    public void displayAllTasks1(ArrayList<Taskk> tasks) throws IOException {

        vbox.getChildren().clear();
        for (Taskk task : tasks) {
            createTodoRow(task.getName(), task.getState(), task);
        }
    }
    //***************************************************************************$$$$$$$$$$$$$$$$$$$$$$$$$

    public void createTodoRow2(String task, Complet state,Taskk taskh ) {
        // Create HBox
        HBox hbox = new HBox();
        hbox.setPrefSize(300, 45);
        hbox.setStyle("-fx-background-color: #51717b; -fx-background-radius: 2em;");

        // Create Labels
        Label taskLabel = new Label(task);
        taskLabel.setPrefSize(120, 45);
        taskLabel.setTextFill(javafx.scene.paint.Color.web("#f2eeee"));
        taskLabel.setFont(new Font(17));
        taskLabel.setAlignment(javafx.geometry.Pos.CENTER);

        Label stateLabel = new Label(state.toString());
        stateLabel.setPrefSize(115, 45);
        stateLabel.setTextFill(javafx.scene.paint.Color.web("#f2ebeb"));
        stateLabel.setFont(new Font(15));
        stateLabel.setAlignment(javafx.geometry.Pos.CENTER);
        // Create CheckBox
        CheckBox checkBox = new CheckBox();
        checkBox.setPrefSize(30, 45);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                taskh.markAsCompleted();
                taskes.editTask(taskh.getId_T(),taskh);
                stateLabel.setText("completed");
            } else {
                taskh.markAsNotComplated();
                taskes.editTask(taskh.getId_T(),taskh);
                stateLabel.setText(state.toString()); // Reset to original state
            }
        });
        // Create Buttons
        Button deleteButton = new Button("delite");
        deleteButton.setPrefSize(57, 45);
        deleteButton.setStyle("-fx-background-color: #3366cc; -fx-background-radius: 2em;");
        deleteButton.setOnAction(event -> {
            // Handle task deletion
            taskes.deleteTask(taskh);

            // Remove the HBox from the VBox
            vbox.getChildren().remove(hbox);
        });

        hbox.setOnMouseClicked(event -> {
            try {
                taskes.editTask(taskh.getId_T(), taskh);
                taskes.deleteTask(taskh);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
                Parent root = loader.load();
                // desplay information of tesk on second page
                Taskcontroller page2 = loader.getController();
                page2.DisplayInfo(taskh);
                // Get the Stage from the Event
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("task");
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        hbox.getChildren().addAll(taskLabel, stateLabel, checkBox, deleteButton);
        vbox2.getChildren().add(hbox);
    }
    public void displayAllTasks3(ArrayList<Taskk> tasks) throws IOException {

        vbox2.getChildren().clear();
        for (Taskk task : tasks) {
            createTodoRow2(task.getName(), task.getState(), task);
        }
    }

    public void select(ActionEvent event)throws IOException {
        vbox.getChildren().clear();
        ArrayList<Taskk> searshingTasks = taskes.search2();
        System.out.println("hhhhhhhhhh22");
        displayAllTasks1(searshingTasks);
    }
    //***************************************************************************


}