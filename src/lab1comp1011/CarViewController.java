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
import javafx.scene.control.Label;
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
    @FXML private Label minYearLabel;
    @FXML private Label maxYearLabel;
    @FXML private Label Label;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.minYearSlider.setMin(2010);
        this.minYearSlider.setMax(2015);
        this.minYearSlider.setValue(2010);
        this.minYearLabel.setText(Integer.toString((int)minYearSlider.getValue()));
        
        this.maxYearSlider.setMin(2015);
        this.maxYearSlider.setMax(2018);
        this.maxYearSlider.setValue(2018);
        this.maxYearLabel.setText(Integer.toString((int)maxYearSlider.getValue()));
    }
  public void maxYearSliderMoved()
    {
        String label = String.format("%.0f", maxYearSlider.getValue());
        maxYearLabel.setText(label);
    }    
  public void minYearSliderMoved()
    {
        String label = String.format("%.0f", minYearSlider.getValue());
        minYearLabel.setText(label);
    }  
    
}
