package termproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Formatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable, Runnable {

    ObservableList<String> calls = FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");

    private int first_move_suit = -1;
    private final double INFINITY = 1000000000000000000.0;
    private String LabelFont = "ALGERIAN";
    private String ButtonFont = "Vineta BT";
    private String CallSetButtonFont = "AR DELANEY";
    private int remainingCards = 52;
    private Boolean isMyMove = false, isMyCall = false, AreCardsReceivable = false;
    private Boolean Multiplayer = false, alreadyEnteredRefreshScene = false;
    private String crd = "file:///F:\\Java Project\\TermProject\\src\\termproject\\Cards\\";
    //private Socket clientSocket;
    private File InputFile, OutputFile;
    private PrintWriter outToServer;
    private BufferedReader inFromServer;
    private int cardID[] = new int[52];
    private int cards[] = new int[13];
    private Boolean isRefreshSceneActive = false;
    private Thread t;
    private Socket clientSocket;

    @FXML
    public void CardsReadyForPlaying() {

    }

    @FXML
    public void setUPCardsforplaying() {
        card.setImage(new Image(crd + "Card[" + (cards[0] / 13) + "][" + (cards[0] % 13) + "].jpg"));
        card1.setImage(new Image(crd + "Card[" + (cards[1] / 13) + "][" + (cards[1] % 13) + "].jpg"));
        card2.setImage(new Image(crd + "Card[" + (cards[2] / 13) + "][" + (cards[2] % 13) + "].jpg"));
        card3.setImage(new Image(crd + "Card[" + (cards[3] / 13) + "][" + (cards[3] % 13) + "].jpg"));
        card4.setImage(new Image(crd + "Card[" + (cards[4] / 13) + "][" + (cards[4] % 13) + "].jpg"));
        card5.setImage(new Image(crd + "Card[" + (cards[5] / 13) + "][" + (cards[5] % 13) + "].jpg"));
        card6.setImage(new Image(crd + "Card[" + (cards[6] / 13) + "][" + (cards[6] % 13) + "].jpg"));
        card7.setImage(new Image(crd + "Card[" + (cards[7] / 13) + "][" + (cards[7] % 13) + "].jpg"));
        card8.setImage(new Image(crd + "Card[" + (cards[8] / 13) + "][" + (cards[8] % 13) + "].jpg"));
        card9.setImage(new Image(crd + "Card[" + (cards[9] / 13) + "][" + (cards[9] % 13) + "].jpg"));
        card10.setImage(new Image(crd + "Card[" + (cards[10] / 13) + "][" + (cards[10] % 13) + "].jpg"));
        card11.setImage(new Image(crd + "Card[" + (cards[11] / 13) + "][" + (cards[11] % 13) + "].jpg"));
        card12.setImage(new Image(crd + "Card[" + (cards[12] / 13) + "][" + (cards[12] % 13) + "].jpg"));
        card13.setImage(new Image(crd + "back.jpg"));
        card14.setImage(new Image(crd + "back.jpg"));
        card15.setImage(new Image(crd + "back.jpg"));
        card16.setImage(new Image(crd + "back.jpg"));
        card17.setImage(new Image(crd + "back.jpg"));
        card18.setImage(new Image(crd + "back.jpg"));
        card19.setImage(new Image(crd + "back.jpg"));
        card20.setImage(new Image(crd + "back.jpg"));
        card21.setImage(new Image(crd + "back.jpg"));
        card22.setImage(new Image(crd + "back.jpg"));
        card23.setImage(new Image(crd + "back.jpg"));
        card24.setImage(new Image(crd + "back.jpg"));
        card25.setImage(new Image(crd + "back.jpg"));
        card26.setImage(new Image(crd + "back.jpg"));
        card27.setImage(new Image(crd + "back.jpg"));
        card28.setImage(new Image(crd + "back.jpg"));
        card29.setImage(new Image(crd + "back.jpg"));
        card30.setImage(new Image(crd + "back.jpg"));
        card31.setImage(new Image(crd + "back.jpg"));
        card32.setImage(new Image(crd + "back.jpg"));
        card33.setImage(new Image(crd + "back.jpg"));
        card34.setImage(new Image(crd + "back.jpg"));
        card35.setImage(new Image(crd + "back.jpg"));
        card36.setImage(new Image(crd + "back.jpg"));
        card37.setImage(new Image(crd + "back.jpg"));
        card38.setImage(new Image(crd + "back.jpg"));
        card39.setImage(new Image(crd + "back.jpg"));
        card40.setImage(new Image(crd + "back.jpg"));
        card41.setImage(new Image(crd + "back.jpg"));
        card42.setImage(new Image(crd + "back.jpg"));
        card43.setImage(new Image(crd + "back.jpg"));
        card44.setImage(new Image(crd + "back.jpg"));
        card45.setImage(new Image(crd + "back.jpg"));
        card46.setImage(new Image(crd + "back.jpg"));
        card47.setImage(new Image(crd + "back.jpg"));
        card48.setImage(new Image(crd + "back.jpg"));
        card49.setImage(new Image(crd + "back.jpg"));
        card50.setImage(new Image(crd + "back.jpg"));
        card51.setImage(new Image(crd + "back.jpg"));
    }
    @FXML
    private Label ServerMessage, call1, call2, call3, call4, hands1, hands2, hands3, hands4, points1, points2, points3, points4;
    @FXML
    private ImageView player1, player2, player3, player4, card, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, card21, card22, card23, card24, card25, card26, card27, card28, card29, card30, card31, card32, card33, card34, card35, card36, card37, card38, card39, card40, card41, card42, card43, card44, card45, card46, card47, card48, card49, card50, card51;
    @FXML
    private Button single_player, multiplayer, callSetButton;
    @FXML
    private AnchorPane background;
    @FXML
    private ChoiceBox SetCall;

    private void initializeBooleanVariables() {
        callSetButton.setDisable(false);
        SetCall.setDisable(false);
        SetCall.setVisible(true);
        callSetButton.setVisible(true);
        call1.setVisible(true);
        call2.setVisible(true);
        call3.setVisible(true);
        call4.setVisible(true);
        hands1.setVisible(true);
        hands2.setVisible(true);
        hands3.setVisible(true);
        hands4.setVisible(true);
        points1.setVisible(true);
        points2.setVisible(true);
        points3.setVisible(true);
        points4.setVisible(true);

    }

    private void finalizeBooleanVariables() {
        SetCall.setDisable(true);
        callSetButton.setDisable(true);
        SetCall.setVisible(false);
        callSetButton.setVisible(false);
        call1.setVisible(false);
        call2.setVisible(false);
        call3.setVisible(false);
        call4.setVisible(false);
        hands1.setVisible(false);
        hands2.setVisible(false);
        hands3.setVisible(false);
        hands4.setVisible(false);
        points1.setVisible(false);
        points2.setVisible(false);
        points3.setVisible(false);
        points4.setVisible(false);
    }

    @FXML
    private void PressedOnMultiplayerButton(ActionEvent event) {
        if (Multiplayer) {
            return;
        }
        try {
            //clientSocket = new Socket("localhost", 6789);
            //Multiplayer = true;
            //System.out.println("Connected to server");
//            clientSocket = new Socket("localhost", 6789);
            clientSocket = new Socket("localhost", 6789);
            
            Multiplayer = true;
            System.out.println("Connected to server");

            outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("server done");
            multiplayer.setDisable(true);
            multiplayer.setVisible(false);
            new Thread(this).start();

        } catch (IOException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot be connected with server");
            interruption();
        }
        System.out.println("Info received");
        //setUPCardsforplaying();
        System.out.println("Set Image Done!!");
        remainingCards = 52;

        isRefreshSceneActive = true;

    }

    @FXML
    private void interruption() {
        finalizeBooleanVariables();
        player1.setImage(null);
        player2.setImage(null);
        player3.setImage(null);
        player4.setImage(null);
        ServerMessage.setText("Game is interrupted!");
        ServerMessage.setVisible(true);
        isRefreshSceneActive = false;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Parent bridgegameparent = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            Scene bridgegamescene = new Scene(bridgegameparent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(bridgegamescene);
            window.show();
        } catch (IOException ex) {
            //Logger.getLogger(SinglePlayerCallBridgeController.class.getName()).log(Level.SEVERE, null, ex);
            interruption();
        }
    }

    @FXML
    private void callSetButtonPressed(ActionEvent event) {
        if (!isMyCall) {
            return;
        }
        int i, call = -1;
        String getCall = null;
        System.out.println("You clicked me!");
        final Formatter file_creator;
        try {
            file_creator = new Formatter("call.txt");
            File call_file = new File("call.txt");
            PrintStream pr = new PrintStream(call_file);
            PrintStream System_Out = new PrintStream(System.out);
            System.setOut(pr);
            System.out.println(SetCall.getValue());
            System.setOut(System_Out);
            pr.close();
            Scanner call_receive = new Scanner(call_file);
            getCall = call_receive.nextLine();
            //call = Integer.parseInt(getCall);
            call_receive.close();
            //System_Out.close();
            call_file.delete();

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Call is not set properly");
            interruption();
        }
        if (getCall != null) {
            outToServer.println(getCall);
            outToServer.flush();
            isMyCall = false;
            isRefreshSceneActive = true;
            SetCall.setVisible(false);
            callSetButton.setVisible(false);
            SetCall.setDisable(true);
            //callSetButton.setTranslateX(INFINITY);
            callSetButton.setDisable(true);
        }
        //SetCall.setTranslateX(100000);
        //SetCall = null;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        player1.setImage(null);
        player2.setImage(null);
        player3.setImage(null);
        player4.setImage(null);
        card.setImage(null);
        card1.setImage(null);
        card2.setImage(null);
        card3.setImage(null);
        card4.setImage(null);
        card5.setImage(null);
        card6.setImage(null);
        card7.setImage(null);
        card8.setImage(null);
        card9.setImage(null);
        card10.setImage(null);
        card11.setImage(null);
        card12.setImage(null);
        card13.setImage(null);
        card14.setImage(null);
        card15.setImage(null);
        card16.setImage(null);
        card17.setImage(null);
        card18.setImage(null);
        card19.setImage(null);
        card20.setImage(null);
        card21.setImage(null);
        card22.setImage(null);
        card23.setImage(null);
        card24.setImage(null);
        card25.setImage(null);
        card26.setImage(null);
        card27.setImage(null);
        card28.setImage(null);
        card29.setImage(null);
        card30.setImage(null);
        card31.setImage(null);
        card32.setImage(null);
        card33.setImage(null);
        card34.setImage(null);
        card35.setImage(null);
        card36.setImage(null);
        card37.setImage(null);
        card38.setImage(null);
        card39.setImage(null);
        card40.setImage(null);
        card41.setImage(null);
        card42.setImage(null);
        card43.setImage(null);
        card44.setImage(null);
        card45.setImage(null);
        card46.setImage(null);
        card47.setImage(null);
        card48.setImage(null);
        card49.setImage(null);
        card50.setImage(null);
        card51.setImage(null);
        SetCall.setId("Give your call");
        SetCall.setItems(calls);
        background.setBackground(Background.EMPTY);
        callSetButton.setDisable(true);
        hands1.setTextFill(Color.BLUE);
        hands2.setTextFill(Color.BLUE);
        hands3.setTextFill(Color.BLUE);
        hands4.setTextFill(Color.BLUE);
        hands1.setFont(Font.font(LabelFont));
        hands2.setFont(Font.font(LabelFont));
        hands3.setFont(Font.font(LabelFont));
        hands4.setFont(Font.font(LabelFont));

        call1.setTextFill(Color.BLUE);
        call2.setTextFill(Color.BLUE);
        call3.setTextFill(Color.BLUE);
        call4.setTextFill(Color.BLUE);
        call1.setFont(Font.font(LabelFont));
        call2.setFont(Font.font(LabelFont));
        call3.setFont(Font.font(LabelFont));
        call4.setFont(Font.font(LabelFont));
        System.out.println("Okay upto calls");
        points1.setTextFill(Color.BLUE);
        points2.setTextFill(Color.BLUE);
        points3.setTextFill(Color.BLUE);
        points4.setTextFill(Color.BLUE);
        points1.setFont(Font.font(LabelFont));
        points2.setFont(Font.font(LabelFont));
        points3.setFont(Font.font(LabelFont));
        points4.setFont(Font.font(LabelFont));
        ServerMessage.setFont(Font.font(LabelFont));
        ServerMessage.setTextFill(Color.BLUE);
        ServerMessage.setVisible(false);
        finalizeBooleanVariables();
        multiplayer.setTextFill(Color.CRIMSON);
        multiplayer.setFont(Font.font(ButtonFont));
        single_player.setFont(Font.font(ButtonFont));
        single_player.setTextFill(Color.CRIMSON);
        callSetButton.setFont(Font.font(CallSetButtonFont));
        callSetButton.setTextFill(Color.CRIMSON);
        ServerMessage.setAlignment(Pos.CENTER);
        points1.setAlignment(Pos.CENTER);
        points2.setAlignment(Pos.CENTER);
        points3.setAlignment(Pos.CENTER);
        points4.setAlignment(Pos.CENTER);
        call1.setAlignment(Pos.CENTER);
        call2.setAlignment(Pos.CENTER);
        call3.setAlignment(Pos.CENTER);
        call4.setAlignment(Pos.CENTER);
        hands1.setAlignment(Pos.CENTER);
        hands2.setAlignment(Pos.CENTER);
        hands3.setAlignment(Pos.CENTER);
        hands4.setAlignment(Pos.CENTER);
        ServerMessage.setScaleX(2);
        ServerMessage.setScaleY(2);
        ServerMessage.setScaleZ(2);
        //while(!Multiplayer) continue;
    }

    @FXML
    public void PlaytheCard(MouseEvent event) {
        if (((ImageView) event.getSource()).getImage() != null && isMyMove) {

            //player1.setImage(((ImageView)event.getSource()).getImage());
            int ID = cardID(((ImageView) event.getSource()));
            if (Multiplayer) {
                String Message = ID + "#" + cardID[ID];

                //outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
                //inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.print(Message + '\n');
                outToServer.flush();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    interruption();
                }
                //if(!isMyMove) playOtherPlayerCards();
            } else {
                System.out.println("Clicked on card " + cardID[ID]);
                ((ImageView) event.getSource()).setImage(null);
            }

        }
    }

    @FXML
    public void updateCurrentHandCards() {
        if (player1.getImage() != null && player2.getImage() != null && player3.getImage() != null && player4.getImage() != null) {
            player1.setImage(null);
            player2.setImage(null);
            player3.setImage(null);
            player4.setImage(null);
        }
    }

    public int cardID(ImageView a) {
        if (a.getImage() == card.getImage()) {
            return 0;
        } else if (a.getImage() == card1.getImage()) {
            return 1;
        } else if (a.getImage() == card2.getImage()) {
            return 2;
        } else if (a.getImage() == card3.getImage()) {
            return 3;
        } else if (a.getImage() == card4.getImage()) {
            return 4;
        } else if (a.getImage() == card5.getImage()) {
            return 5;
        } else if (a.getImage() == card6.getImage()) {
            return 6;
        } else if (a.getImage() == card7.getImage()) {
            return 7;
        } else if (a.getImage() == card8.getImage()) {
            return 8;
        } else if (a.getImage() == card9.getImage()) {
            return 9;
        } else if (a.getImage() == card10.getImage()) {
            return 10;
        } else if (a.getImage() == card11.getImage()) {
            return 11;
        } else if (a.getImage() == card12.getImage()) {
            return 12;
        } else if (a.getImage() == card13.getImage()) {
            return 13;
        } else if (a.getImage() == card14.getImage()) {
            return 14;
        } else if (a.getImage() == card15.getImage()) {
            return 15;
        } else if (a.getImage() == card16.getImage()) {
            return 16;
        } else if (a.getImage() == card17.getImage()) {
            return 17;
        } else if (a.getImage() == card18.getImage()) {
            return 18;
        } else if (a.getImage() == card19.getImage()) {
            return 19;
        } else if (a.getImage() == card20.getImage()) {
            return 20;
        } else if (a.getImage() == card21.getImage()) {
            return 21;
        } else if (a.getImage() == card22.getImage()) {
            return 22;
        } else if (a.getImage() == card23.getImage()) {
            return 23;
        } else if (a.getImage() == card24.getImage()) {
            return 24;
        } else if (a.getImage() == card25.getImage()) {
            return 25;
        } else if (a.getImage() == card26.getImage()) {
            return 26;
        } else if (a.getImage() == card27.getImage()) {
            return 27;
        } else if (a.getImage() == card28.getImage()) {
            return 28;
        } else if (a.getImage() == card29.getImage()) {
            return 29;
        } else if (a.getImage() == card30.getImage()) {
            return 30;
        } else if (a.getImage() == card31.getImage()) {
            return 31;
        } else if (a.getImage() == card32.getImage()) {
            return 32;
        } else if (a.getImage() == card33.getImage()) {
            return 33;
        } else if (a.getImage() == card34.getImage()) {
            return 34;
        } else if (a.getImage() == card35.getImage()) {
            return 35;
        } else if (a.getImage() == card36.getImage()) {
            return 36;
        } else if (a.getImage() == card37.getImage()) {
            return 37;
        } else if (a.getImage() == card38.getImage()) {
            return 38;
        } else if (a.getImage() == card39.getImage()) {
            return 39;
        } else if (a.getImage() == card40.getImage()) {
            return 40;
        } else if (a.getImage() == card41.getImage()) {
            return 41;
        } else if (a.getImage() == card42.getImage()) {
            return 42;
        } else if (a.getImage() == card43.getImage()) {
            return 43;
        } else if (a.getImage() == card44.getImage()) {
            return 44;
        } else if (a.getImage() == card45.getImage()) {
            return 45;
        } else if (a.getImage() == card46.getImage()) {
            return 46;
        } else if (a.getImage() == card47.getImage()) {
            return 47;
        } else if (a.getImage() == card48.getImage()) {
            return 48;
        } else if (a.getImage() == card49.getImage()) {
            return 49;
        } else if (a.getImage() == card50.getImage()) {
            return 50;
        } else if (a.getImage() == card51.getImage()) {
            return 51;
        } else {
            return -1;
        }
    }

    @FXML
    public void updateHands(String hands) {
        String spilled[] = hands.split("#");
        hands1.setText("Hands: " + spilled[0]);
        hands2.setText("Hands: " + spilled[1]);
        hands3.setText("Hands: " + spilled[2]);
        hands4.setText("Hands: " + spilled[3]);
    }

    @FXML
    public void updateCalls(String calls) {
        String spilled[] = calls.split("#");
        call1.setText("Call: " + spilled[0]);
        call2.setText("Call: " + spilled[1]);
        call3.setText("Call: " + spilled[2]);
        call4.setText("Call: " + spilled[3]);
    }

    @FXML
    public void updatePoints(String points) {
        String spilled[] = points.split("#");
        points1.setText("Points: " + spilled[0]);
        points2.setText("Points: " + spilled[1]);
        points3.setText("Points: " + spilled[2]);
        points4.setText("Points: " + spilled[3]);
    }

    @FXML
    public void moveTheCardinGUI(int index, int cardValue) {

        if (index == 0) {
            card.setImage(null);
        } else if (index == 1) {
            card1.setImage(null);
        } else if (index == 2) {
            card2.setImage(null);
        } else if (index == 3) {
            card3.setImage(null);
        } else if (index == 4) {
            card4.setImage(null);
        } else if (index == 5) {
            card5.setImage(null);
        } else if (index == 6) {
            card6.setImage(null);
        } else if (index == 7) {
            card7.setImage(null);
        } else if (index == 8) {
            card8.setImage(null);
        } else if (index == 9) {
            card9.setImage(null);
        } else if (index == 10) {
            card10.setImage(null);
        } else if (index == 11) {
            card11.setImage(null);
        } else if (index == 12) {
            card12.setImage(null);
        } else if (index == 13) {
            card13.setImage(null);
        } else if (index == 14) {
            card14.setImage(null);
        } else if (index == 15) {
            card15.setImage(null);
        } else if (index == 16) {
            card16.setImage(null);
        } else if (index == 17) {
            card17.setImage(null);
        } else if (index == 18) {
            card18.setImage(null);
        } else if (index == 19) {
            card19.setImage(null);
        } else if (index == 20) {
            card20.setImage(null);
        } else if (index == 21) {
            card21.setImage(null);
        } else if (index == 22) {
            card22.setImage(null);
        } else if (index == 23) {
            card23.setImage(null);
        } else if (index == 24) {
            card24.setImage(null);
        } else if (index == 25) {
            card25.setImage(null);
        } else if (index == 26) {
            card26.setImage(null);
        } else if (index == 27) {
            card27.setImage(null);
        } else if (index == 28) {
            card28.setImage(null);
        } else if (index == 29) {
            card29.setImage(null);
        } else if (index == 30) {
            card30.setImage(null);
        } else if (index == 31) {
            card31.setImage(null);
        } else if (index == 32) {
            card32.setImage(null);
        } else if (index == 33) {
            card33.setImage(null);
        } else if (index == 34) {
            card34.setImage(null);
        } else if (index == 35) {
            card35.setImage(null);
        } else if (index == 36) {
            card36.setImage(null);
        } else if (index == 37) {
            card37.setImage(null);
        } else if (index == 38) {
            card38.setImage(null);
        } else if (index == 39) {
            card39.setImage(null);
        } else if (index == 40) {
            card40.setImage(null);
        } else if (index == 41) {
            card41.setImage(null);
        } else if (index == 42) {
            card42.setImage(null);
        } else if (index == 43) {
            card43.setImage(null);
        } else if (index == 44) {
            card44.setImage(null);
        } else if (index == 45) {
            card45.setImage(null);
        } else if (index == 46) {
            card46.setImage(null);
        } else if (index == 47) {
            card47.setImage(null);
        } else if (index == 48) {
            card48.setImage(null);
        } else if (index == 49) {
            card49.setImage(null);
        } else if (index == 50) {
            card50.setImage(null);
        } else if (index == 51) {
            card51.setImage(null);
        }
        String imageDirectory = crd + "Card[" + (cardValue / 13) + "][" + (cardValue % 13) + "].jpg";
        if (index / 13 == 0) {
            player1.setImage(new Image(imageDirectory));
        } else if (index / 13 == 1) {
            player2.setImage(new Image(imageDirectory));
        } else if (index / 13 == 2) {
            player3.setImage(new Image(imageDirectory));
        } else if (index / 13 == 3) {
            player4.setImage(new Image(imageDirectory));
        }

        System.out.println("Nullified Image Index=" + index);
        remainingCards--;

    }

    @FXML
    public void moveTheCard(int index, int cardValue) {
        updateCurrentHandCards();
        moveTheCardinGUI(index, cardValue);

    }

    @FXML
    public void GameOver(String result) {
        if (result.endsWith("You won!")) {
            ServerMessage.setTextFill(Color.GREEN);
        } else {
            ServerMessage.setTextFill(Color.RED);
        }
        ServerMessage.setText("Game Over! " + result);
        ServerMessage.setScaleX(7);
        ServerMessage.setScaleY(7);
        ServerMessage.setScaleZ(7);
        ServerMessage.setVisible(true);
    }

    @FXML
    public void RefreshScene(MouseEvent me) {
        /*if(!isRefreshSceneActive||alreadyEnteredRefreshScene){
            return;
        }
       
            alreadyEnteredRefreshScene = true;
           System.out.println("anchorpane mouse function entered");
           System.out.println(remainingCards>0);
           System.out.println(Multiplayer);
           System.out.println(!isMyMove);
           System.out.println(!isMyCall);
           if(remainingCards==0) updateCurrentHandCards();
           if(AreCardsReceivable)
           {
               CardsReadyForPlaying(); //Logger.getLogger(HeartsMultiplayerController.class.getName()).log(Level.SEVERE, null, ex);
               AreCardsReceivable = false;
           }
           else 
           if ( Multiplayer && !isMyMove && !isMyCall) {
               System.out.println("Ready to receive Message");
               String Message = null; //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
               //inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
               System.gc();
               try {
                   while(!inFromServer.ready()) continue;
                   Message = inFromServer.readLine();
                   System.out.println("(Inside anchorpane function) Message="+Message);
               } catch (IOException ex) {
                   //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                   System.out.println("When there are " + remainingCards + " remaining, problem happened");
                   interruption();
               }
               if(Message.equals("Receive your cards")){
                   AreCardsReceivable = true;
                   remainingCards = 52;
               }
               else if (Message.equals("Make your move")) {
                   isMyMove = true;
                   ServerMessage.setVisible(isMyMove);
                   ServerMessage.setText(Message);
                   System.out.println(Message);
               }
               else if(Message.equals("Make your call")) {
                   isMyCall = true;
                   System.out.println(Message);
               }
               else {
                   String splittedMessage[] = Message.split("#");
                   
                   if(splittedMessage[splittedMessage.length-1].equals("hands"))
                   {
                       updateHands(Message);
                   }
                   else if(splittedMessage[splittedMessage.length-1].equals("calls"))
                   {
                       updateCalls(Message);
                   }
                   else if(splittedMessage[splittedMessage.length-1].equals("points"))
                   {
                       updatePoints(Message);
                   }
                   //System.out.println("Index=" + Index + ", C=" + C);
                   else if(splittedMessage[0].equals("Game Over!"))
                   {
                       isRefreshSceneActive=false;
                       
                       finalizeBooleanVariables();
                       GameOver(splittedMessage[1]);
                   }
                   else
                   {
                       int index = Integer.parseInt(splittedMessage[0]);
                   int cardValue = Integer.parseInt(splittedMessage[1]);
                       moveTheCard(index, cardValue);
                   }
                   
               }
        }
           
        
        if(isMyMove||isMyCall) 
        {
            isRefreshSceneActive=false;
            System.out.println("anchorpane disabled");
            
        }
        alreadyEnteredRefreshScene = false;*/
    }
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        isRefreshSceneActive = true;
        while (isRefreshSceneActive) {
            //System.out.println(AreCardsReceivable+" "+Multiplayer+" "+!isMyMove+" "+!isMyCall);
            if (AreCardsReceivable) {
                remainingCards = 52;
                int i;
                initializeBooleanVariables();
                System.out.println("Okay upto declaration");
                for (i = 0; i < 13; i++) {
                    try {
                        System.out.print("When i=" + i + ",");
                        String cardMessage = inFromServer.readLine();
                        System.out.print(cardMessage);
                        String splittedMessage[] = cardMessage.split("#");
                        cards[i] = Integer.parseInt(splittedMessage[0]);
                        cardID[i] = cards[i];
                        System.out.println(", cardID[" + i + "]=" + cardID[i]);
                    } catch (IOException ex) {
                        //Logger.getLogger(SinglePlayerCallBridgeController.class.getName()).log(Level.SEVERE, null, ex);
                        interruption();
                    }
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        setUPCardsforplaying();
                    }
                });
                AreCardsReceivable = false;
                continue;
            } {
                System.out.println("Ready to receive Message");
                String Message = null; //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                //inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.gc();
                try {

                    Message = inFromServer.readLine();
                    System.out.println("(Inside anchorpane function) Message=" + Message);
                } catch (IOException ex) {
                    //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("When there are " + remainingCards + " remaining, problem happened");
                    interruption();
                }
                if (Message.equals("Receive your cards")) {
                    AreCardsReceivable = true;
                    remainingCards = 52;
                } else if (Message.equals("Make your move")) {
                    
                    isMyMove = true;
                    ServerMessage.setVisible(isMyMove);
                } else if (Message.equals("Make your call")) {
                    
                    //System.out.println(Message);
                    isMyCall = true;
                }
                else if(Message.equals("Call is set!"))
                {
                    isMyCall = false;
                }
                else if (Message.equals("Moved!")) {
                    isMyMove = false;
                    ServerMessage.setVisible(isMyMove);
                    //moveTheCard(ID, cardID[ID]);
                    isRefreshSceneActive = true;
                }
                else {
                    String splittedMessage[] = Message.split("#");

                    if (splittedMessage[splittedMessage.length - 1].equals("hands")) {
                        String inner_variable = Message;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updateHands(inner_variable);
                            }
                        });

                    } else if (splittedMessage[splittedMessage.length - 1].equals("calls")) {
                        String inner_variable = Message;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updateCalls(inner_variable);
                            }
                        });
                    } else if (splittedMessage[splittedMessage.length - 1].equals("points")) {
                        String inner_variable = Message;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updatePoints(inner_variable);
                            }
                        });

                    } //System.out.println("Index=" + Index + ", C=" + C);
                    else if (splittedMessage[0].equals("Game Over!")) {
                        isRefreshSceneActive = false;
                        String inner_variable = splittedMessage[1];
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                finalizeBooleanVariables();
                                GameOver(inner_variable);
                            }
                        });

                    } else {
                        int index = Integer.parseInt(splittedMessage[0]);
                        int cardValue = Integer.parseInt(splittedMessage[1]);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                moveTheCard(index, cardValue);
                            }
                        });

                    }

                }
            }
        }
    }

}