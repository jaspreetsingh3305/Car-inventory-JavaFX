/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1comp1011;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DoneController implements Initializable {
    @FXML private ImageView imageView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageView.setImage(new Image("file:./src/image/guru-nanak.jpg"));
    }    
       
    public void changeScene(ActionEvent event) throws IOException{
    
     FXMLLoader loader=new FXMLLoader();
     loader.setLocation(getClass().getResource("CarView.fxml"));
     Parent parent = loader.load();
     
     Scene scene = new Scene(parent);
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     stage.setTitle("Cars");
     stage.setScene(scene);
     stage.show();
         
    
    }
    
}
