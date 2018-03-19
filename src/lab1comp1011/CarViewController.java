/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1comp1011;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CarViewController implements Initializable {
    @FXML private TableColumn<?, ?> makeColumn;
    @FXML private TableColumn<?, ?> modelColumn;
    @FXML private TableColumn<?, ?> yearColumn;
    @FXML private TableColumn<?, ?> mileageColumn;
    @FXML private Slider minYearSlider;
    @FXML private Slider maxYearSlider;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.minYearSlider.setMin(2010);
        this.minYearSlider.setMax(2017);
        this.maxYearSlider.setMin(2017);
        this.maxYearSlider.setMax(2018);
    }    
    
}
