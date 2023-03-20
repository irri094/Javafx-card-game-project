/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainmenuController implements Initializable {


    public void bridge_multiplayer(ActionEvent event) throws IOException{
        /// make multiplayer true
        Parent bridgegameparent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene bridgegamescene = new Scene(bridgegameparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(bridgegamescene);
        window.show();
    }
    public void bridge_singleplayer(ActionEvent event) throws IOException {
        /// make singleplayer true
        Parent bridgegameparent = FXMLLoader.load(getClass().getResource("SinglePlayerCallBridge.fxml"));
        Scene bridgegamescene = new Scene(bridgegameparent,Color.CYAN);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bridgegamescene);
        window.show();
    }
    public void hearts_multiplayer(ActionEvent event) throws IOException {
        /// make hearts true
        Parent bridgegameparent = FXMLLoader.load(getClass().getResource("HeartsMultiplayer.fxml"));
        Scene bridgegamescene = new Scene(bridgegameparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bridgegamescene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
