package MyPackage;


import java.io.File;
import java.io.PrintStream;
import java.util.Stack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CNBase {

	static File srcCNRegression = new File("C:\\Users\\554525\\Desktop\\Sneha\\CNProject\\RegressionCNTestData.xls");
	static File srccodeCleanUp = new File("C:\\Users\\499290\\Desktop\\TF\\TestData_CodeCleanUp.xls");
	static File srccrmdata = new File("C:\\Users\\499290\\Desktop\\TF\\CRMTestDataSheet.xls");
	static String screenshotDirectory = "C:\\Users\\499290\\Desktop\\TF\\screenshot";
	PrintStream verificationErrors = null;
	String TakescreenShot;
	static WebDriver driver;
	static String baseUrl1 = "https://sit-theglobalfund.cs41.force.com/GM/apex/OpenConceptNotesH" ;
	static String baseUrl2 = "https://test.salesforce.com";

	static String baseUrl3 = "https://test.salesforce.com";


	static int i;
	static String Record;
	WebElement RecordLink;
	WebElement ModuleLink;
	WebElement GoalsImpactIndicator;
	static WebElement Edit;
	static String Goaltitle;
	static String Objectivetitle;
	static int rowcount;
	static String ImpactIndicatorName;
	static String OutcomeIndicatorName;
	static String DecisiononConceptNote;
	static String GACConfirmationofTRPDecision;
	static String Status;


	

	
	
	
	
}
