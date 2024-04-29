package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.beans.property.DoubleProperty;
import model.CalcModel;
import model.Operation;

class CalculatorAppTest
{
	CalcModel calculator = new CalcModel();
	DoubleProperty num1 = new SimpleDoubleProperty(6);
	DoubleProperty num2 = new SimpleDoubleProperty(2);
	
	@BeforeEach
	void setUp() throws Exception
	{
		calculator.setNum1(num1);
		calculator.setNum2(num2);
	}

	@Test
	void testGets()
	{
		assertEquals(calculator.getNum1().doubleValue(), 6);
		assertEquals(calculator.getNum2().doubleValue(), 2);
	}
	
	@Test
	void testAdd() {
		calculator.add();
		assertEquals(calculator.getResult().doubleValue(), 8);
		Operation addOperation = new Operation(6, " + ", 2, 8);
		
		ObservableList<Operation> allOperations = calculator.getOperations();
		assertEquals(allOperations.get(0).toString(), addOperation.toString());
	}
	
	@Test
	void testSubtract() {
		calculator.subtract();
		assertEquals(calculator.getResult().doubleValue(), 4);
		Operation subtractOperation = new Operation(6, " - ", 2, 4);
		
		ObservableList<Operation> allOperations = calculator.getOperations();
		assertEquals(allOperations.get(0).toString(), subtractOperation.toString());
	}
	
	@Test
	void testMultiply() {
		calculator.multiply();
		assertEquals(calculator.getResult().doubleValue(), 12);
		Operation multiplyOperation = new Operation(6, " * ", 2, 12);
		
		ObservableList<Operation> allOperations = calculator.getOperations();
		assertEquals(allOperations.get(0).toString(), multiplyOperation.toString());
	}
	
	@Test
	void testDivide() {
		calculator.divide();
		assertEquals(calculator.getResult().doubleValue(), 3);
		Operation divideOperation = new Operation(6, " / ", 2, 3);
		
		ObservableList<Operation> allOperations = calculator.getOperations();
		assertEquals(allOperations.get(0).toString(), divideOperation.toString());
	}

}
