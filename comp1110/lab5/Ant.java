package comp1110.lab5;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Ant extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Ant");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),
                ae -> {
                    /* your code goes here */
                    Random random = new Random();
                    double x = 100+random.nextDouble()*500;
                    double y = 100+random.nextDouble()*500;
                    //GraphicsContext context = canvas.getGraphicsContext2D();
                    //context.setFill(Color.BLACK);
                    //context.fillOval(x, y, 4, 4);
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();

    }

}
