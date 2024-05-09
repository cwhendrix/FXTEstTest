package app;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
	  robot.clickOn("#NumTwoTF");
	  robot.write(num);
  }
  
  @SuppressWarnings("unchecked")
  private ListView<Operation> getOperations(FxRobot robot) {
	return (ListView<Operation>) robot.lookup("#HistoryView").queryAll().iterator().next();
	  
  }
  /*
  private void checkResult(FxRobot robot, String result) {
	  Assertions.assertThat(robot.lookup("#HistoryView").queryAs(ListView<Operation>)).hasText(result);
  }
  */
  @Test
  void testIsEmpty(FxRobot robot ) {
	  ListView<Operation> allOps = getOperations(robot);
	  Assertions.assertThat(allOps).hasExactlyNumItems(0);
  }
  
  @Test
  void testComputationsButton(FxRobot robot) {
	  addNum1(robot, "6");
	  addNum2(robot, "2");
	  robot.clickOn("#addButton");
	  
	  addNum1(robot, "6");
	  addNum2(robot, "2");
	  robot.clickOn("#subtractButton");
	  
	  addNum1(robot, "6");
	  addNum2(robot, "2");
	  robot.clickOn("#multiplyButton");
	  
	  addNum1(robot, "6");
	  addNum2(robot, "2");
	  robot.clickOn("#divideButton");
	  
	  ListView<Operation> allOps = getOperations(robot);
	  ObservableList<Operation> ops = allOps.getItems();
	  
	  Operation [] operations = {
			  new Operation(6, " + ", 2, 8),
			  new Operation(6, " - ", 2, 4),
			  new Operation(6, " * ", 2, 12),
			  new Operation(6, " / ", 2, 3)
	  };
	  Assertions.assertThat(allOps).hasExactlyNumItems(operations.length);
	  
	  for (int i=0; i<operations.length; i++) {
		  Assertions.assertThat(ops.get(i).toString()).isEqualTo(operations[i].toString());
	  }
  }
}