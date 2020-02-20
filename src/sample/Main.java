package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        primaryStage.setTitle("8 Queens Problem: Marra");
        GenerateBoard(root);
        int[] board = new int[8];
        queens(0,0, board);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i] > 0){
                    String id = String.valueOf(i) + String.valueOf(j);
                    StackPane square = (StackPane) root.lookup("#"+id);
                    Label text = new Label("Q");
                    text.setTextFill(Color.web("#f44336"));
                    square.getChildren().addAll(text);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    private void queens(int index, int col, int[] board){
        if(promising(index, col, board)){
            if(index == 7){
                return;
            }
            else {
                for(col = 0;col < 8; col++){
                    board[col] = col;
                    queens(index, col, board);
                }
            }
        }
    }

    private boolean promising(int index, int col, int[][] board){
        int k = 0;
        boolean promising  = true;
        while(k < index && promising){
            if(board[index][col] == board[index][k] || Math.abs(board[index][col] - board[index][k]) == index-k){
                promising = false;
            }
            k++;
        }
        return promising;
    }

    private void GenerateBoard(GridPane root) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                StackPane box = new StackPane();
                String color;
                String textColor = "#f44336";
                if((i+j)%2==0){
                    //white
                    color = "#EEEEEE";
                }
                else {
                    //"black"
                    color = "#212121";
                }
                box.setStyle("-fx-background-color: "+color+
                             ";-fx-text-fill: " + textColor+ ";");
                box.setId(String.valueOf(i)+String.valueOf(j));
                root.add(box, i,j);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
