package app;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import app.*;
import model.*;
import view.*;

@ExtendWith(ApplicationExtension.class)
public class CalculatorTests
{
  @Start
  private void start(Stage stage) throws Exception
  {
    
       
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(CalculatorApp.class.getResource("../view/main.fxml"));
    BorderPane view;
    try {
      view = loader.load();
    
    MainController controller = loader.getController(); 
    CalcModel calculator = new CalcModel();
    controller.setModel(calculator);
   
    
    
    Scene s = new Scene(view);
    stage.setScene(s);
    stage.show();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  private void addNum1(FxRobot robot, String num) {
	  robot.clickOn("#NumOneTF");
	  robot.write(num);
  }
  private void addNum2(FxRobot robot, String num) {
	  robot.clickOn("NumTwoTF");
	  robot.write(num);
  }
  
  @Test
  void testAddButton(FxRobot robot) {
	  addNum1(robot, "6");
	  addNum2(robot, "2");
	  robot.clickOn("#addButton");
  }
}