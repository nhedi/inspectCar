package se.kth.iv1350.carInspection.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.carInspection.integration.*;

public class InspectionResultTest {

	private DatabaseManager databaseManager;
	private InspectionResult inspectionResult;
	private List<InspectionItem> inspectionList;
	private Printer printer;
	private String regNo;
	//private String regNo;
	
	
	@Before
	public void setUp() throws Exception {
		databaseManager = new DatabaseManager();
		printer = new Printer();
		regNo = "ABC123";
		inspectionList = new ArrayList<>();
		inspectionList.add(new InspectionItem("Brakes", 105, "Failed"));
		inspectionList.add(new InspectionItem("Engine", 65, "Failed"));
		inspectionList.add(new InspectionItem("Lights", 25, "Failed"));
		inspectionResult = new InspectionResult(regNo, inspectionList, databaseManager, printer);
	}

	@After
	public void tearDown() throws Exception {
		databaseManager = null;
		printer = null;
		inspectionList = null;
		inspectionResult = null;
	}
	
	@Test
	public void testSaveFirstInspectionResultInDatabase() {		
		inspectionResult.handleInspectionResult("Passed", 0);
		assertEquals("Inspection result was not saved", "Passed", databaseManager.getInspections(regNo).get(0).getInspectionResult());	
	}
	
	@Test
	public void testSaveLastInspectionResultInDatabase() {		
		inspectionResult.handleInspectionResult("Passed", 2);
		assertEquals("Inspection result was not saved", "Passed", databaseManager.getInspections(regNo).get(2).getInspectionResult());	
	}
}
