
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.Stage;

public class Gui extends Application {

    private Socket socket;
    private Scene mainScene, add, listChoice, listAll, listByPt, listByClient, listByDate, update, delete;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //COMMAND SELECTION SCENE -----------------------------
        Label labelA0 = new Label("COMMAND SELECTION");
        Label labelB0 = new Label("-----------------");
        Button buttonA0 = new Button("ADD");
        Button buttonB0 = new Button("LIST");
        Button buttonC0 = new Button("UPDATE");
        Button buttonD0 = new Button("DELETE");
        Label labelC0 = new Label("-----------------");
        Button buttonE0 = new Button("EXIT");
        
        buttonA0.setMaxSize(100, 200);
        buttonB0.setMaxSize(100, 200);
        buttonC0.setMaxSize(100, 200);
        buttonD0.setMaxSize(100, 200);
        
        buttonA0.setOnAction(e -> primaryStage.setScene(add));
        buttonB0.setOnAction(e -> primaryStage.setScene(listChoice));
        buttonC0.setOnAction(e -> primaryStage.setScene(update));
        buttonD0.setOnAction(e -> primaryStage.setScene(delete));
        buttonE0.setOnAction(e -> primaryStage.close());

        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setColumnHalignment(HPos.CENTER);
        pane.setPadding(new Insets(20, 10, 10, 10));
        pane.setVgap(10);
        pane.getChildren().addAll(labelA0, labelB0, buttonA0, buttonB0, buttonC0, buttonD0, labelC0, buttonE0);
        
        mainScene = new Scene(pane, 200, 300);
        //---------------------------------------

        //ADD SCENE -----------------------------
        Label labelA1 = new Label("ADD");
        Label labelB1 = new Label("-----------------");
        TextField fieldA1 = new TextField("Customer ID");
        TextField fieldB1 = new TextField("PT ID");
        TextField fieldC1 = new TextField("Date (YYYY-MM-DD)");
        TextField fieldD1 = new TextField("Time (HH:MM:SS)");
        TextField fieldE1 = new TextField("Duration (HH:MM:SS)");
        TextField fieldF1 = new TextField("Focus");
        Label labelC1 = new Label("");
        Button buttonA1 = new Button("Add Booking");
        Label labelD1 = new Label("-----------------");
        Button buttonB1 = new Button("BACK");
        
        //buttonA1.setOnAction(new Client());
        buttonB1.setOnAction(e -> primaryStage.setScene(mainScene));

        FlowPane pane1 = new FlowPane(Orientation.VERTICAL);
        pane1.setAlignment(Pos.TOP_CENTER);
        pane1.setColumnHalignment(HPos.CENTER);
        pane1.setPadding(new Insets(20, 10, 10, 10));
        pane1.setVgap(10);
        pane1.getChildren().addAll(labelA1, labelB1, fieldA1, fieldB1, fieldC1, fieldD1, fieldE1, fieldF1, labelC1, buttonA1, labelD1, buttonB1);
        add = new Scene(pane1, 250, 450);
        //---------------------------------------

        //LIST SCENE -----------------------------
        Label labelA2 = new Label("LIST");
        Label labelB2 = new Label("-----------------");
        Button buttonA2 = new Button("ALL");
        Button buttonB2 = new Button("BY PT");
        Button buttonC2 = new Button("BY CLIENT");
        Button buttonD2 = new Button("BY DATE");
        Label labelC2 = new Label("-----------------");
        Button buttonE2 = new Button("BACK");
        
        buttonA2.setMaxSize(100, 200);
        buttonB2.setMaxSize(100, 200);
        buttonC2.setMaxSize(100, 200);
        buttonD2.setMaxSize(100, 200);
        
        buttonA2.setOnAction(e -> primaryStage.setScene(listAll));
        buttonB2.setOnAction(e -> primaryStage.setScene(listByPt));
        buttonC2.setOnAction(e -> primaryStage.setScene(listByClient));
        buttonD2.setOnAction(e -> primaryStage.setScene(listByDate));
        buttonE2.setOnAction(e -> primaryStage.setScene(mainScene));

        FlowPane pane2 = new FlowPane(Orientation.VERTICAL);
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.setColumnHalignment(HPos.CENTER);
        pane2.setPadding(new Insets(20, 10, 10, 10));
        pane2.setVgap(10); 
        pane2.getChildren().addAll(labelA2, labelB2, buttonA2, buttonB2, buttonC2, buttonD2, labelC2, buttonE2);
        
        listChoice = new Scene(pane2, 200, 300);
        //---------------------------------------

        //LIST ALL SCENE -----------------------------
        Label labelA3 = new Label("LIST ALL");
        Label labelB3 = new Label("-----------------");
        Label labelC3 = new Label("");
        Button buttonA3 = new Button("Refresh");
        Label labelD3 = new Label("-----------------");
        Button buttonB3 = new Button("BACK");
        
        //buttonA3.setOnAction(e -> out.print("LISTALL"));
        buttonB3.setOnAction(e -> primaryStage.setScene(listChoice));

        FlowPane pane3 = new FlowPane(Orientation.VERTICAL);
        pane3.setAlignment(Pos.TOP_CENTER);
        pane3.setColumnHalignment(HPos.CENTER);
        pane3.setPadding(new Insets(20, 10, 10, 10));
        pane3.setVgap(10);
        pane3.getChildren().addAll(labelA3, labelB3, labelC3, buttonA3, labelD3, buttonB3);

        listAll = new Scene(pane3, 400, 220);
        //---------------------------------------

        //LIST BY PT SCENE -----------------------------
        Label labelA4 = new Label("LIST BY PT");
        Label labelB4 = new Label("-----------------");
        TextField fieldA4 = new TextField("PT ID");
        Label labelC4 = new Label("");
        Button buttonA4 = new Button("List Bookings");
        Label labelD4 = new Label("-----------------");
        Button buttonC4 = new Button("BACK");
        
        buttonC4.setOnAction(e -> primaryStage.setScene(listChoice));

        FlowPane pane4A = new FlowPane(Orientation.HORIZONTAL);
        pane4A.setAlignment(Pos.CENTER);
        pane4A.getChildren().addAll(fieldA4, buttonA4);
        pane4A.setHgap(10);

        FlowPane pane4B = new FlowPane(Orientation.VERTICAL);
        pane4B.setAlignment(Pos.TOP_CENTER);
        pane4B.setColumnHalignment(HPos.CENTER);
        pane4B.setPadding(new Insets(20, 10, 10, 10));
        pane4B.setVgap(10);
        pane4B.getChildren().addAll(labelA4, labelB4, pane4A, labelC4, labelD4, buttonC4);

        listByPt = new Scene(pane4B, 400, 220);
        //---------------------------------------

        //LIST BY CLIENT SCENE -----------------------------
        Label labelA5 = new Label("LIST BY CLIENT");
        Label labelB5 = new Label("-----------------");
        TextField fieldA5 = new TextField("Client ID");
        Label labelC5 = new Label("");
        Button buttonA5 = new Button("List Bookings");
        Label labelD5 = new Label("-----------------");
        Button buttonB5 = new Button("BACK");
        
        buttonB5.setOnAction(e -> primaryStage.setScene(listChoice));

        FlowPane pane5A = new FlowPane(Orientation.HORIZONTAL);
        pane5A.setAlignment(Pos.CENTER);
        pane5A.getChildren().addAll(fieldA5, buttonA5);
        pane5A.setHgap(10);

        FlowPane pane5B = new FlowPane(Orientation.VERTICAL);
        pane5B.setAlignment(Pos.TOP_CENTER);
        pane5B.setColumnHalignment(HPos.CENTER);
        pane5B.setPadding(new Insets(20, 10, 10, 10));
        pane5B.setVgap(10);
        pane5B.getChildren().addAll(labelA5, labelB5, pane5A, labelC5, labelD5, buttonB5);

        listByClient = new Scene(pane5B, 400, 220);
        //---------------------------------------

        //LIST BY DATE SCENE -----------------------------
        Label labelA6 = new Label("LIST BY DATE");
        Label labelB6 = new Label("-----------------");
        TextField fieldA6 = new TextField("Date (YYYY-MM-DD)");
        Label labelC6 = new Label("");
        Button buttonA6 = new Button("List Bookings");
        Label labelD6 = new Label("-----------------");
        Button buttonB6 = new Button("BACK");
        
        buttonB6.setOnAction(e -> primaryStage.setScene(listChoice));

        FlowPane pane6A = new FlowPane(Orientation.HORIZONTAL);
        pane6A.setAlignment(Pos.CENTER);
        pane6A.getChildren().addAll(fieldA6, buttonA6);
        pane6A.setHgap(10);

        FlowPane pane6B = new FlowPane(Orientation.VERTICAL);
        pane6B.setAlignment(Pos.TOP_CENTER);
        pane6B.setColumnHalignment(HPos.CENTER);
        pane6B.setPadding(new Insets(20, 10, 10, 10));
        pane6B.setVgap(10);
        pane6B.getChildren().addAll(labelA6, labelB6, pane6A, labelC6, labelD6, buttonB6);

        listByDate = new Scene(pane6B, 400, 220);
        //---------------------------------------

        //UPDATE SCENE -----------------------------
        Label labelA7 = new Label("UPDATE");
        Label labelB7 = new Label("-----------------");
        TextField fieldA7 = new TextField("Booking ID");
        TextField fieldB7 = new TextField("Customer ID");
        TextField fieldC7 = new TextField("PT ID");
        TextField fieldD7 = new TextField("Date (YYYY-MM-DD)");
        TextField fieldE7 = new TextField("Time (HH:MM:SS)");
        TextField fieldF7 = new TextField("Duration (HH:MM:SS)");
        TextField fieldG7 = new TextField("Focus");
        Label labelC7 = new Label("");
        Button buttonA7 = new Button("Update Booking");
        Label labelD7 = new Label("-----------------");
        Button buttonB7 = new Button("BACK");
        
        buttonB7.setOnAction(e -> primaryStage.setScene(mainScene));

        FlowPane pane7 = new FlowPane(Orientation.VERTICAL);
        pane7.setAlignment(Pos.TOP_CENTER);
        pane7.setColumnHalignment(HPos.CENTER);
        pane7.setPadding(new Insets(20, 10, 10, 10));
        pane7.setVgap(10);
        pane7.getChildren().addAll(labelA7, labelB7, fieldA7, fieldB7, fieldC7, fieldD7, fieldE7, fieldF7, fieldG7, labelC7, buttonA7, labelD7, buttonB7);
        update = new Scene(pane7, 250, 480);
        //---------------------------------------

        //DELETE SCENE -----------------------------
        Label labelA8 = new Label("DELETE");
        Label labelB8 = new Label("-----------------");
        TextField fieldA8 = new TextField("Booking ID");
        Label labelC8 = new Label("");
        Button buttonA8 = new Button("Delete Booking");
        Label labelD8 = new Label("-----------------");
        Button buttonC8 = new Button("BACK");
        
        buttonC8.setOnAction(e -> primaryStage.setScene(mainScene));

        FlowPane pane8A = new FlowPane(Orientation.HORIZONTAL);
        pane8A.setAlignment(Pos.CENTER);
        pane8A.getChildren().addAll(fieldA8, buttonA8);
        pane8A.setHgap(10);
        //pane6.setPadding(new Insets(20, 10, 10, 10));

        FlowPane pane8B = new FlowPane(Orientation.VERTICAL);
        pane8B.setAlignment(Pos.TOP_CENTER);
        pane8B.setColumnHalignment(HPos.CENTER);
        pane8B.setPadding(new Insets(20, 10, 10, 10));
        pane8B.setVgap(10);
        pane8B.getChildren().addAll(labelA8, labelB8, pane8A, labelC8, labelD8, buttonC8);

        delete = new Scene(pane8B, 400, 220);
        //---------------------------------------

        //Setting primary stage
        primaryStage.setTitle("PT Booking System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
