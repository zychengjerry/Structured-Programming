package comp1110.ass2.gui;

import comp1110.ass2.Games;
import comp1110.ass2.State;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * Main class to run
 * View of the board and all game elements
 * The main widows of this game, all user's movement will be in here
 * @author  Gong Chen, Yitao Chen
 * (Gong writes the difficulty change and generation function, while Yitao writes the others)
 */

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final String URI_BASE = "assets/";
    private static Set<Character> AllPiece = new HashSet<Character>(){{add('B');add('G');add('I');add('L');add('N');add('O');add('P');add('R');add('S');add('Y');}};

    State boardSituation = new State();

    Group keyboard = new Group();
    Group draggablePieces = new Group();
    Group pieces = new Group();
    Group controls = new Group();

    private TextField textField;

    /* the difficulty slider */
    private final Slider difficulty = new Slider();


    class GUIPiece extends ImageView{
        String OBJECT;
        char type;
        /**
         * construct that creates a basic piece
         * @param string 4-character-length string represents the piece
         * @author Yitao Chen
         */
        GUIPiece(String string) {
            //pre_handle the string
            char char_type = string.charAt(0); //type
            String object = char_num(char_type);
            char char_col = string.charAt(1); //column 0-9
            char char_row = string.charAt(2); //row 0-4
            char char_dir = string.charAt(3); //direction
            double x,y;
            this.OBJECT = object;
            this.type = char_type;

            // Find the corresponding image
            Image image = new Image(Viewer.class.getResource(URI_BASE + OBJECT + ".png").toString());
            setImage(image);

            // Standardized piece X-Y position and size
            if(OBJECT.charAt(0)=='G'||OBJECT.charAt(0)=='I'||OBJECT.charAt(0)=='L'||OBJECT.charAt(0)=='N') {
                setFitHeight(SQUARE_SIZE * 2);
                setFitWidth(SQUARE_SIZE * 3);
                if(char_dir=='N'||char_dir=='S'){
                    x = char_col - 48 + 1;
                    y = char_row - 48 + 1;
                }else {
                    x = char_col - 48 + 0.5;
                    y = char_row - 48 + 1.5;
                }
            }else{
                setFitHeight(SQUARE_SIZE * 2);
                setFitWidth(SQUARE_SIZE * 4);
                if(char_dir=='N'||char_dir=='S'){
                    x = char_col - 48 + 1;
                    y = char_row - 48 + 1;
                }else {
                    x = char_col - 48;
                    y = char_row - 48 + 2;
                }
            }
            setLayoutX(x*SQUARE_SIZE);
            setLayoutY(y*SQUARE_SIZE);
            setRotate(dir_degree(char_dir));//suppose to be more beautiful, I used a quite clumsy solution lol
        }
        /*
        public String getOBJECT() {
            return OBJECT;
        }

        //@param str length = 2 && first char is one in "BGILNOPRSY" and the second is ‘1’ or ‘2’

        void setOBJECT(String str) {
            this.OBJECT = str;
        }
        */

        /**
         *  change lower char to upper char + ‘1’
         *  change upper char to upper char + ‘2’
         *  @param a the original first char that represents the piece
         *  @return string a1 or a2 with a capital a
         *  @author Yitao Chen
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
         * change DIRECTION to rotate degree(90/180/270)
         * @author Yitao Chen
         */
        public int dir_degree(char a){
            switch (a){
                case 'N':
                    return 0;
                //break;
                case 'E':
                    return 90;
                //break;
                case 'S':
                    return 180;
                //break;
                default:
                    return 270;
            }
        }

        /**
         * change rotate degree to direction(char)
         * @param a (0/90/180/270)
         * @author Yitao Chen
         */
        public char degree_dir(int a){
            int b = a%360;
            switch (b){
                case 0:
                    return 'N';
                //break;
                case 90:
                    return 'E';
                //break;
                case 180:
                    return 'S';
                //break;
                default:
                    return 'W';
            }
        }
    }
    /**
     * This class extends piece with the capacity for it to be dragged and dropped,
     * double click to rotate,
     * and snap-to-grid.
     * @author Yitao Chen
     */
    class DraggablePiece extends GUIPiece{
        private Board board;
        private double x_set,y_set;
        private double x_original,y_original;
        DraggablePiece(String string, Board board) {
            super(string);
            this.board = board;
            this.setOnMousePressed(event ->{
                x_original = this.getLayoutX();
                y_original = this.getLayoutY();
                x_set = this.getLayoutX() - event.getSceneX();
                y_set = this.getLayoutY() - event.getSceneY();
            });

            this.setOnMouseDragged(event ->{
                this.setLayoutX(event.getSceneX()+x_set);
                this.setLayoutY(event.getSceneY()+y_set);
            });

            this.setOnMouseReleased(event ->{
                if(onBoard()){this.snapToGrid();}
                else{
                    setLayoutX(x_original);
                    setLayoutY(y_original);
                }
            });

            this.setOnMouseClicked(event -> {
                //double click right button to rotate
                if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    this.setRotate(this.getRotate()+90);
                }
            });
        }
        public boolean onBoard(){
            //pre-board to drag 12*8
            if(-45<=getLayoutX() && getLayoutX()<=675 && -30<=getLayoutY() && getLayoutY()<=420){
                return true;
            }
            return false;
        }
        public void snapToGrid(){
            char type1 = OBJECT.charAt(0);
            int gridX = (int) (getLayoutX()-15)/SQUARE_SIZE;
            int gridY = (int) (getLayoutY()-30)/SQUARE_SIZE;
            char gridDir = degree_dir((int)getRotate());
            double x,y;
            String currentPosition = ""+type+gridX+gridY+gridDir;
            if(boardSituation.checkPieceOccupied(currentPosition)){
                if(type1=='G'||type1=='I'||type1=='L'||type1=='N') {
                    if(gridDir=='N'||gridDir=='S'){
                        x = gridX + 1;
                        y = gridY + 1;
                    }else {
                        x = gridX + 0.5;
                        y = gridY + 1.5;
                    }
                }else{
                    if(gridDir=='N'||gridDir=='S'){
                        x = gridX + 1;
                        y = gridY + 1;
                    }else {
                        x = gridX;
                        y = gridY + 2;
                    }
                }
                setLayoutX(x*SQUARE_SIZE);
                setLayoutY(y*SQUARE_SIZE);
                //boardSituation.setPieceOccupied(currentPosition);
            }
        }
    }
    // FIXME Task 7: Implement a basic playable Fix Game in JavaFX that only allows pieces to be placed in valid places

    /**
     * Choose a new objective, given a difficulty level.
     *
     * The method should select a randomized objective from the 60 pre-defined solutions,
     * being sure to select an objective with the correct level of difficulty.
     *
     * (See the comments above the declaration of the OBJECTIVES array.)
     *
     * So, for example, if the difficulty is 0 (starter), then this function should use a randomized
     * index between 1 and 24 (inclusive) to return an objective from the OBJECTIVES array that is
     * level 0 difficulty.  On the other hand, if the difficulty is 3 (master), then this function
     * should use a randomized index between 50 and 73 (inclusive) to return an objective from the
     * OBJECTIVES array that is level 3 difficulty.
     *
     * The original code below simply returns Games.SOLUTIONS[0], which neither respects the difficulty
     * (it always returns a level 0 objective), nor is it randomized (it always returns the same
     * objective).
     *
     * @param difficulty The difficulty of the game (0 - starter, 1 - junior, 2 - expert, 3 - master, 4 - wizard)
     * @return An objective at the appropriate level of difficulty.
     * @author Gong Chen
     */
    public Games levels(int difficulty){
        assert difficulty >= 0 && difficulty <= 4;
        int level =0;
        Random r = new Random();
        int ranDomLevel = r.nextInt(23);

        switch (difficulty){
            case 0: level = 1;break;
            case 1: level = 25;break;
            case 2: level = 49;break;
            case 3: level = 73;break;
            case 4: level = 97;break;
        }

        level += ranDomLevel;
        return  Games.SOLUTIONS[level];
    }

    // FIXME Task 8: Implement challenges (you may use assets provided for you in comp1110.ass2.gui.assets) by Gong

    // FIXME Task 10: Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)


    /**
     *  display the keyboard
     *  @author Yitao Chen
     */
    private void makeBoard() {
        ImageView baseboard = new ImageView();
        baseboard.setImage(new Image(Viewer.class.getResource(URI_BASE  +"board.png").toString()));
        baseboard.setFitWidth(700);
        baseboard.setFitHeight(375);
        baseboard.setLayoutX(15);
        baseboard.setLayoutY(30);
        keyboard.getChildren().add(baseboard);
    }
    /**
     * Draw a placement in the window, removing any previously drawn one
     * Update char[] InputPiece
     * @author Yitao Chen
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
            GUIPiece p = new GUIPiece(sub);
            pieces.getChildren().add(p);
            boardSituation.setPieceOccupied(sub);
        }
    }

    /**
     * Present the remaining ones (draggable piece)
     * @author Yitao Chen
     * @param placement same as the above makePlacement method
     */
    void ShowRemainPiece(String placement){
        draggablePieces.getChildren().clear();
        int pieceNum = placement.length() / 4;
        Set<Character> InputPiece = new HashSet<Character>();
        for (int i = 0; i < pieceNum; i++){
            InputPiece.add(Character.toUpperCase(placement.charAt(0+i*4)));
        }
        //make a minus set
        Set<Character> result = new HashSet<Character>();
        result.clear();
        result.addAll(AllPiece);
        result.removeAll(InputPiece);
        //show the remaining ones (draggable piece)
        for (char char_type : result) {
            DraggablePiece draggablePiece = new DraggablePiece(char_type+"68N",this);
            draggablePieces.getChildren().add(draggablePiece);
        }
        //show the remaining ones different side (draggable piece)
        for (char char_type : result) {
            DraggablePiece draggablePiece_1 = new DraggablePiece(Character.toLowerCase(char_type)+"08N",this);
            draggablePieces.getChildren().add(draggablePiece_1);
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     *
     * @author Yitao Chen uptimize, Gong Chen add difficulty function
     */
    private void makeControls() {
        textField = new TextField();
        textField.setPrefWidth(300);
        final Object[] currentLevel = new Object[2];

        //--------------Gong start
        //levels
        difficulty.setMin(1);
        difficulty.setMax(5);
        difficulty.setValue(0);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(1);
        difficulty.setSnapToTicks(true);

        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        //end - levels

        //first time game start
        Games level = levels((int)difficulty.getValue()-1);
        currentLevel[0] = level.objective;
        currentLevel[1] = level.number;

        //The level obtained when using it is currentLevel[0]: the pieces information of all current levels currentLevel[1]: the specific number of current level difficulty
        System.out.println(level.objective+" "+ level.number);

        // The next thing seems to be to display the information I gave in your board
        makePlacement(currentLevel[0].toString());
        ShowRemainPiece(currentLevel[0].toString());

        //Here is a convenient restart to record the currently selected level

        Button newGame = new Button("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //save current info incase restart
                Games level = levels((int)difficulty.getValue()-1);
                currentLevel[0] = level.objective;
                currentLevel[1] = level.number;
                makePlacement(currentLevel[0].toString());
                ShowRemainPiece(currentLevel[0].toString());
            }
        });

        Button Restart = new Button("Restart the game");
        Restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(currentLevel[0].toString());
                ShowRemainPiece(currentLevel[0].toString());
            }
        });


        //-------------end of Gong

        Button button = new Button("Run your own game:");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                ShowRemainPiece(textField.getText());
                textField.clear();
            }
        });

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(difficultyCaption, difficulty, newGame, Restart);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(button,textField);

        VBox vb = new VBox();
        vb.getChildren().addAll(hb1,hb2);
        vb.setSpacing(20);
        vb.setLayoutX(130);
        vb.setLayoutY(430);

        controls.getChildren().add(vb);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ - FIT");
        Group root = new Group();
        Scene scene = new Scene(root,BOARD_WIDTH,BOARD_HEIGHT);
        primaryStage.setScene(scene);

        makeControls();
        makeBoard();
        root.getChildren().add(keyboard);
        root.getChildren().add(controls);
        root.getChildren().add(pieces);
        root.getChildren().add(draggablePieces);

        // display
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args);}
}
