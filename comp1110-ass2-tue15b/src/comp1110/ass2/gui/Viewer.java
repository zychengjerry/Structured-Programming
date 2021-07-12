package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the IQ-Fit game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece placements.
 * It's a trial.
 * @author Yitao Chen
 */
public class Viewer extends Application {

    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group board = new Group();
    private TextField textField;

    private void makeBoard() {
        board.getChildren().clear();

        ImageView baseboard = new ImageView();
        baseboard.setImage(new Image(Viewer.class.getResource(URI_BASE  +"board.png").toString()));
        baseboard.setFitWidth(VIEWER_WIDTH-30);
        baseboard.setFitHeight(VIEWER_HEIGHT-105);
        baseboard.setLayoutX(15);
        baseboard.setLayoutY(30);
        board.getChildren().add(baseboard);
        board.toBack();
    }
    class GUIpiece extends ImageView {
        /**
         * A constructor used to build the objective piece.
         * @param OBJECT The piece to be displayed
         * @param piece_2   The x position of the piece
         * @param piece_3   The y position of the piece
         */
        GUIpiece(String OBJECT, char piece_2, char piece_3,char piece_4) {
            double x,y;
            Image image = new Image(Viewer.class.getResource(URI_BASE + OBJECT + ".png").toString());
            setImage(image);
            if(OBJECT.charAt(0)=='G'||OBJECT.charAt(0)=='I'||OBJECT.charAt(0)=='L'||OBJECT.charAt(0)=='N') {
                setFitHeight(SQUARE_SIZE * 2);
                setFitWidth(SQUARE_SIZE * 3);
                x = piece_2 - 48+1;
                y = piece_3 - 48+1;
                if(piece_4=='N'||piece_4=='S'){
                    x = piece_2 - 48+1;
                    y = piece_3 - 48+1;
                }else {
                    x = piece_2 - 48 + 0.5;
                    y = piece_3 - 48 + 1.5;
                }
            }else{
                setFitHeight(SQUARE_SIZE * 2);
                setFitWidth(SQUARE_SIZE * 4);
                if(piece_4=='N'||piece_4=='S'){
                    x = piece_2 - 48+1;
                    y = piece_3 - 48+1;
                }else {
                    x = piece_2 - 48;
                    y = piece_3 - 48+2;
                }
            }

            setLayoutX(x*SQUARE_SIZE);
            setLayoutY(y*SQUARE_SIZE);
            setRotate(dir_int(piece_4) * 90);//suppose to be more beautiful, I used a quite clumsy solution lol
        }
    }

    /**
     *
     * @param a direction of the piece
     * @return int 0-3 represents the direction
     */
    public int dir_int(char a){
        switch (a){
            case 'N':
                return 0;
                //break;
            case 'E':
                return 1;
                //break;
            case 'S':
                return 2;
                //break;
            default:
                return 3;
        }
    }

    /**
     * change lower char to upper char+1
     * change upper char to upper char+2
     * @param a the original first char that represents the piece
     * @return Capital a1 or a2
     */
    public String char_num(char a){
        if(Character.isLowerCase(a)){
            return Character.toUpperCase(a) + "1";
        }else if (Character.isUpperCase(a)){
            return a + "2";
        }else
            return null;
    }

    /**
     * Draw a placement in the window, removing any previously drawn one
     * @param placement A valid placement string *4 characters describe the piece to be displayed(80 in total)
     */
    void makePlacement(String placement) {
        pieces.getChildren().clear();
        if (placement.length()%4!=0){
            throw new IllegalArgumentException("Illegal Input");
        }
        int pieceNum = placement.length() / 4;
        for (int i = 0; i < pieceNum; i++) {
            String sub = placement.substring(i * 4, i * 4 + 4);

            char piece_1 = sub.charAt(0);//type
            String OBJECT = char_num(piece_1);
            char piece_11 = OBJECT.charAt(0);
            char piece_2 = sub.charAt(1);//column0-9
            char piece_3 = sub.charAt(2);//row0-4
            char piece_4 = sub.charAt(3);//direction
            if ((piece_11 == 'B' || piece_11 == 'G' || piece_11 == 'I' || piece_11 == 'L' || piece_11 == 'N' || piece_11 == 'O' || piece_11 == 'P' || piece_11 == 'R' || piece_11 == 'S' || piece_11 == 'Y') && (48 <= piece_2 && piece_2 <= 57) && (48 <= piece_3 && piece_3 <= 52) && (piece_4 == 'N' || piece_4 == 'E' || piece_4 == 'S' || piece_4 == 'W')) {
                pieces.getChildren().add(new GUIpiece(OBJECT, piece_2, piece_3, piece_4));
            }
        }
    }// FIXME Task 4: implement the simple placement viewer. Fixed by Yitao

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    //display the gui
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FitGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(board);
        root.getChildren().add(pieces);

        makeControls();
        makeBoard();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
