/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1comp1011;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CarViewController implements Initializable {
    @FXML private TableView<Car> tableView;
    @FXML private TableColumn<Car, String> makeColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car,Integer> yearColumn;
    @FXML private TableColumn<Car, Double> mileageColumn;
    @FXML private Slider minYearSlider;
    @FXML private Slider maxYearSlider;
    @FXML private Label minYearLabel;
    @FXML private Label maxYearLabel;
    @FXML private ComboBox<String> brandComboBox;
    
    
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
        
        this.makeColumn.setCellValueFactory(
                new PropertyValueFactory<Car, String>("make"));
        this.modelColumn.setCellValueFactory(
                new PropertyValueFactory<Car, String>("model"));
        this.yearColumn.setCellValueFactory(
                new PropertyValueFactory<Car,Integer>("year"));
        this.mileageColumn.setCellValueFactory(
                new PropertyValueFactory<Car, Double>("mileage"));
        
        try {
            loadData();
             loadComboBox();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+ex.getSQLState());
        }
         brandComboBox.getSelectionModel().select("Select");
       
        
    }
    public void maxYearSliderMoved() throws SQLException
    {
        String label = String.format("%.0f", maxYearSlider.getValue());
        maxYearLabel.setText(label);
        UpdateTableWithSliders();
    }    
    public void minYearSliderMoved() throws SQLException
    {
        String label = String.format("%.0f", minYearSlider.getValue());
        minYearLabel.setText(label);
        UpdateTableWithSliders();
       
    }  
    public void loadData() throws SQLException{
         
         ObservableList<Car> cars = FXCollections.observableArrayList();
         
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
     
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            statement = conn.createStatement();
            
            resultSet = statement.executeQuery("SELECT * FROM cars");
            
            while (resultSet.next())
            {
                Car newCar=new Car(
                    resultSet.getString("make"),
                    resultSet.getString("model"),
                    resultSet.getInt("year"),
                    resultSet.getDouble("mileage"));
                
                    cars.add(newCar);
               
            }
           
         tableView.getItems().addAll(cars);
            
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
   
     }
     
    public void loadComboBox() throws SQLException{
         ObservableList<ResultSet> cars = FXCollections.observableArrayList();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
     
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            statement = conn.createStatement();
            
            resultSet = statement.executeQuery("SELECT DISTINCT make FROM cars");
            

            
            while (resultSet.next())
            {
            brandComboBox.getItems().addAll(resultSet.getString("make"));
          
            }
                    
        }
        catch (SQLException e)
        { 
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
      
     
     }

    public void UpdateTableWithSliders() throws SQLException{
    
        this.tableView.getItems().clear();
        ObservableList<Car> cars = FXCollections.observableArrayList();
         
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            statement = conn.createStatement();
            
            if(brandComboBox.getValue()!=null && brandComboBox.getValue()!="Select"){
                
            resultSet = statement.executeQuery("SELECT * FROM cars WHERE year BETWEEN "
                    +minYearSlider.getValue()+" AND "+maxYearSlider.getValue()+
                    " AND make='"+this.brandComboBox.getValue()+"'");
            }
            else{
                
            resultSet = statement.executeQuery("SELECT * FROM cars WHERE year BETWEEN "
                    +minYearSlider.getValue()+" AND "+maxYearSlider.getValue());
            }
            while (resultSet.next())
            {
                Car newCar=new Car(
                    resultSet.getString("make"),
                    resultSet.getString("model"),
                    resultSet.getInt("year"),
                    resultSet.getDouble("mileage"));
                
                    cars.add(newCar);

            }
            tableView.getItems().addAll(cars);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
}

    public void changeScene(ActionEvent event) throws IOException{
    
     FXMLLoader loader=new FXMLLoader();
     loader.setLocation(getClass().getResource("Done.fxml"));
     Parent parent = loader.load();
     
     Scene scene = new Scene(parent);
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     stage.setTitle("Qoute");
     stage.setScene(scene);
     stage.show();
    
    }
    
    public void clearFilters(){
        
        this.minYearSlider.setValue(2010);
        this.maxYearSlider.setValue(2018);
       
        try {
                loadData();
                loadComboBox();
            }
        catch(SQLException ex) {
                System.err.println(ex.getMessage()+ex.getSQLState());
            }
        brandComboBox.getSelectionModel().select("Select");
       
    }
    
     }
