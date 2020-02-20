package Queens;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    public static List<int[]> solutions = new LinkedList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        primaryStage.setTitle("8 Queens Problem: Marra");
        GenerateBoard(root);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        int[] board = new int[8];
        queens(-1, board);
        int[] choice = solutions.get(new Random().nextInt(solutions.size()));



        for(int i = 0; i < 8; i++){
            int j = choice[i];
            String id = String.valueOf(j) + String.valueOf(i);
            StackPane square = (StackPane) root.lookup("#"+id);
            Label text = new Label("QUEEN");
            text.setTextFill(Color.web("#f44336"));
            square.setPrefSize(75.0,75.0);
            square.getChildren().addAll(text);
        }
//        for (int i = 0; i < 8; i++) {
//            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
//            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
//        }

        primaryStage.show();
    }


    private int[] queens(int index, int[] board){
        int col;

        if(promising(index, board)){
            if(index == 7){
                solutions.add(board.clone());
                return board; }
            else {
                for(col = 0; col < 8; col++){
                    board[index+1] = col;
                    queens(index+1, board);
                }
            }
        }
        return null;
    }

    private boolean promising(int index, int[] board){
        int k = 0;
        boolean promising  = true;
        while(k < index && promising){
            if(board[index] == board[k] || Math.abs(board[index] - board[k]) == index-k){
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
                    color = "#bdc3c7";
                }
                else {
                    //"black"
                    color = "#2c3e50";
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
