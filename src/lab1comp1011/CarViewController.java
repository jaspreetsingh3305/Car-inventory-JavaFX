/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1comp1011;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
         brandComboBox.getSelectionModel().selectFirst();
       
        
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
         
        //get the Phone data from the database
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
     
        
        try{
            //1. connect to the DB with the URL to the db, user name and password
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            //2.create a statement object to execute on the DB
            statement = conn.createStatement();
            
            //3. create & execute the SQL query
            resultSet = statement.executeQuery("SELECT * FROM cars");
            

            
            //5.  Loop over the result set and display to the screen
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
        //get the Phone data from the database
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
     
        
        try{
            //1. connect to the DB with the URL to the db, user name and password
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            //2.create a statement object to execute on the DB
            statement = conn.createStatement();
            
            //3. create & execute the SQL query
            resultSet = statement.executeQuery("SELECT DISTINCT make FROM cars");
            

            
            //5.  Loop over the result set and display to the screen
            while (resultSet.next())
            {
            brandComboBox.getItems().addAll(resultSet.getString("make"));
            
           // cars.add(resultSet);
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
         
        //get the Phone data from the database
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
     
        
        try{
            //1. connect to the DB with the URL to the db, user name and password
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/"
                    + "gc200360513", "gc200360513", "nUDgkNa2zj");
            
            //2.create a statement object to execute on the DB
            statement = conn.createStatement();
            
            //3. create & execute the SQL query
            resultSet = statement.executeQuery("SELECT * FROM cars WHERE year BETWEEN "
                    +minYearSlider.getValue()+" AND "+maxYearSlider.getValue());
            

            
            //5.  Loop over the result set and display to the screen
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
}