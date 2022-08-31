package com.example.javasudoku.userinterface;

import com.example.javasudoku.constants.GameState;
import com.example.javasudoku.problemdomain.Coordinates;
import com.example.javasudoku.problemdomain.SodukoGame;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class UserInterfaceImp1 implements IUserInterfaceContract.View, EventHandler<KeyEvent>
{
    private final Stage stage;
    private final Group root;

    private HashMap<Coordinates, SudokuTextField> textFieldCoordinates;

    private IUserInterfaceContract.EventListener listener;

    private static final double WINDOW_Y = 732;
    private static final double WINDOW_X = 668;
    private static final double BAORD_PADDING = 50;
    private static final double BOARD_X_AND_Y = 576;

    private static final Color WINDOW_BACKGROUND_COLOR = javafx.scene.paint.Color.RED;
    private static final javafx.scene.paint.Color BOARD_BACKGROUND_COLOR = javafx.scene.paint.Color.AQUA;
    private static final String SUDOKU = "Sudoku";

    public UserInterfaceImp1(Stage stage) {
        this.stage = stage;
        this.root= new Group();
        this.textFieldCoordinates = new HashMap<>();
        initializeUserInterface();
    }

    private void initializeUserInterface() {
        drawBackground(root);
        drawTitle(root);
        drawSudokuBoard(root);
        drawTextFields(root);
        drawGridLines(root);
        stage.show();
    }

    private void drawGridLines(Group root) {
        int xAndY = 144;
        int index= 0;
        while(index < 8){
            int thickness;
            if(index== 2 ||index ==  5){
                thickness =3;
            }else{
                thickness = 2;
            }

            Rectangle verticalLine = getLine(
                    xAndY + 64 * index,
                    BAORD_PADDING,
                    BOARD_X_AND_Y,
                    thickness
            );
            Rectangle horizontalLine = getLine(
                    BAORD_PADDING,
                    xAndY + 64*index,
                    thickness,
                    BOARD_X_AND_Y
            );

            root.getChildren().addAll(
                    verticalLine,
            horizontalLine
                    );

            index++;
        }
    }

    private Rectangle getLine(double x,
                              double y,
                              double height,
                              double width) {
        Rectangle line = new Rectangle();

        line.setX(x);
        line.setY(y);
        line.setHeight(height);
        line.setWidth(width);
        return line;
    }

    private void drawTextFields(Group root) {
        final int xOrigin = 50;
        final int yOrigin =50;

        final int xAndYDelta = 64;
        //O(n^2) RunTime Complexity
        for(int xIndex = 0;xIndex< 9;xIndex++)
        {
            for(int yIndex = 0;yIndex<9;yIndex++)
            {
                int x= xOrigin + xIndex*xAndYDelta;
                int y = yOrigin + yIndex*xAndYDelta;

                SudokuTextField title = new SudokuTextField(xIndex,yIndex);

                styleSudokuTitle(title, x,y);

                title.setOnKeyPressed(this);

                textFieldCoordinates.put(new Coordinates(xIndex,yIndex), title);

                root.getChildren().add(title);

            }
        }
    }

    private void styleSudokuTitle(SudokuTextField title, int x, int y) {
        Font numberFont = new Font(32);

        title.setFont(numberFont);
        title.setAlignment(Pos.CENTER);
        title.setLayoutX(x);
        title.setLayoutY(y);
        title.setPrefHeight(64);
        title.setPrefWidth(64);

        title.setBackground(Background.EMPTY);
    }

    private void drawSudokuBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BAORD_PADDING);
        boardBackground.setY(BAORD_PADDING);

        boardBackground.setWidth(BOARD_X_AND_Y);
        boardBackground.setHeight(BOARD_X_AND_Y);

        boardBackground.setFill(BOARD_BACKGROUND_COLOR);

        root.getChildren().addAll(boardBackground);
    }

    private void drawTitle(Group root) {
        Text title = new Text(235,609,SUDOKU);
        title.setFill(javafx.scene.paint.Color.WHITE);
        Font titleFont = new Font(43);
        title.setFont(titleFont);
        root.getChildren().add(title);
    }

    private void drawBackground(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BACKGROUND_COLOR);
        stage.setScene(scene);
    }

    @Override
    public void setListener(IUserInterfaceContract.EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile= textFieldCoordinates.get(new Coordinates(x,y));
        String value = Integer.toString(
                input
        );
        if(value.equals("0"))
            value="";
        tile.textProperty().setValue(value);
    }

    @Override
    public void updateBoard(SodukoGame game) {
        for(int xIndex = 0;xIndex< 9;xIndex++)
        {
            for(int yIndex=0;yIndex<9;yIndex++)
            {
                SudokuTextField tile = textFieldCoordinates.get(new Coordinates(xIndex,yIndex));
                String value = Integer.toString(
                        game.getCopyofGridState()[xIndex][yIndex]
                );

                if(value.equals("0"))
                    value = "";
                tile.setText(value);

                if(game.getGameState() == GameState.NEW){
                    if(value.equals("")){
                        tile.setStyle("-fx-opacity: 1;");
                        tile.setDisable(false);
                    }
                    else
                    {

                        tile.setStyle("-fx-opacity: 0.8;");
                        tile.setDisable(true);
                    }
            }
            }
        }
    }

    @Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

        if(dialog.getResult() == ButtonType.OK)
            listener.onDialogClick();
    }

    @Override
    public void showError(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED){
            if(keyEvent.getText().matches("[0-9]")){
                int value = Integer.parseInt(keyEvent.getText());
                handleInput(value,keyEvent.getSource());
            }
            else if(keyEvent.getCode() == KeyCode.BACK_SPACE){
                handleInput(0, keyEvent.getSource());
            }
            else{
                ((TextField) keyEvent.getSource()).setText("");
            }
        }
        keyEvent.consume();
    }

    private void handleInput(int i, Object source) {
        listener.onSudokuInput(
                ((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                i
        );
    }
}
