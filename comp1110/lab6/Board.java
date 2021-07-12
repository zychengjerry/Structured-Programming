package comp1110.lab6;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Board extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Board");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 519);
        primaryStage.setScene(scene);
        //(0.0, -86.6)
        Polygon polygon = new Polygon(0.0, -86.6, 100.0, 86.6, -100.0, 86.6);
        polygon.setFill(Color.LIGHTGREY);
        polygon.setLayoutX(300.0);
        polygon.setLayoutY(259.5);
        root.getChildren().add(polygon);
        scene.setRoot(root);
        primaryStage.show();

    }

    public static class Triangle extends Polygon{
        private double x;
        private double y;
        private double side;

        public Triangle(double x, double y, double side){
            this.x = x;
            this.y = y;
            this.side = side;
        }

    }

}