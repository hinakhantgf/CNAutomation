package MyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Indicators extends CNBase {
	
	 @Test (priority= 0)
		
	 	public void GoalCreation() throws InterruptedException, BiffException, IOException{
	 		
	 		 Workbook wb = Workbook.getWorkbook(srcCNRegression);
	 	     Goaltitle = wb.getSheet("GoalDetails").getCell(0, 1).getContents();
	 	     rowcount = wb.getSheet("GoalDetails").getRows();
	 	     Record = wb.getSheet("Record").getCell(0,1).getContents();
			 Thread.sleep(5000); 
			 driver.findElement(By.linkText("Concept Notes")).click();
			 Thread.sleep(5000);
			 RecordLink = driver.findElement(By.linkText(Record));
			 //HighlightElement.elementHighlight(RecordLink);
			 Thread.sleep(5000);
			 RecordLink.click();
	 		 Thread.sleep(5000);
	 		 for (i=1; i<rowcount; ++i) {
	 			 driver.findElement(By.linkText("GOALS & IMPACT INDICATORS")).click();
	 			 Thread.sleep(5000);
	 			 driver.findElement(By.linkText("Add Goal")).click();  
	 			 Thread.sleep(5000);
	 			 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	 		     WebElement goalname = driver.findElement(By.id("pg:frm:itaGDec"));
	 		     HighlightElement.elementHighlight(goalname);
	 		     goalname.sendKeys(Goaltitle);
	 		     Thread.sleep(5000);
	 		     driver.findElement(By.linkText("Save")).click(); 
	 		     Thread.sleep(5000);
	 		 }
	 		   }
	  
	  @Test (priority = 1)
		
		public void StdImpactIndicator() throws BiffException, IOException, InterruptedException {
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
		    ImpactIndicatorName = wb.getSheet("ImpactIndicatorList").getCell(1,1).getContents();
			Integer x = Integer.valueOf(ImpactIndicatorName);
			String BaseLineval = wb.getSheet("ImpactIndicatorList").getCell(2, 1).getContents();
			String YearVal = wb.getSheet("ImpactIndicatorList").getCell(3, 1).getContents();
			String Sourceval = wb.getSheet("ImpactIndicatorList").getCell(4, 1).getContents();
			String DecimalPlaces = wb.getSheet("ImpactIndicatorList").getCell(5, 1).getContents();	
			String Impval2014 = wb.getSheet("ImpactIndicatorList").getCell(1,4).getContents();
			String Impval2015 = wb.getSheet("ImpactIndicatorList").getCell(1,5).getContents();
			String Impval2016 = wb.getSheet("ImpactIndicatorList").getCell(1,6).getContents();
			String Comment = wb.getSheet("ImpactIndicatorList").getCell(1, 7).getContents();
			String DueDate1 = wb.getSheet("ImpactIndicatorList").getCell(2,4).getContents();
			String DueDate2 = wb.getSheet("ImpactIndicatorList").getCell(2, 5).getContents();
			String DueDate3 = wb.getSheet("ImpactIndicatorList").getCell(2, 6).getContents();
			Thread.sleep(2000);
			Record = wb.getSheet("Record").getCell(0,1).getContents();
			Thread.sleep(5000); 
			driver.findElement(By.linkText("Concept Notes")).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText(Record)).click();
			Thread.sleep(5000);
			//HighlightElement.elementHighlight(RecordLink);
			//RecordLink.click();
			WebElement GoalImpLink  = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
			HighlightElement.elementHighlight(GoalImpLink);
			GoalImpLink.click();
			Thread.sleep(5000);
			//Click on Add Standard Indicator button
			WebElement StdAddInd = driver.findElement(By.linkText("Add Standard Indicator"));
			HighlightElement.elementHighlight(StdAddInd);
			StdAddInd.click();
			Thread.sleep(5000);
			// Select Standard Indicator value
			WebElement selectIndicator=driver.findElement(By.id("pg:frm:SelectCatIndicator"));
			HighlightElement.elementHighlight(selectIndicator);
			Select Indicator=new Select(selectIndicator);
			Thread.sleep(5000);
			Indicator.selectByIndex(x);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@id='pg:frm:SelectIndicatorPicklist']/strong")).click();
			Thread.sleep(20000);
			// select Goal value
			driver.findElement(By.id("pg:frm:StdPanelGoal")).click();
			Thread.sleep(15000);
			WebElement DecimalPlace= driver.findElement(By.id("pg:frm:ipdecimalplacestand"));
			DecimalPlace.click();
			HighlightElement.elementHighlight(DecimalPlace);
			Thread.sleep(2000);
			Select DecimalPick = new Select(DecimalPlace);
			Thread.sleep(2000);
			DecimalPick.selectByValue(DecimalPlaces);
			Thread.sleep(2000);
			// Select Baseline Value
			WebElement BaseLine = driver.findElement(By.id("pg:frm:ipStdBy1"));
			BaseLine.clear();
			Thread.sleep(2000);
			HighlightElement.elementHighlight(BaseLine);
			Thread.sleep(2000);
			BaseLine.sendKeys(BaseLineval);
			Thread.sleep(5000);
			// Select Baseline Year
			WebElement Year = driver.findElement(By.id("pg:frm:j_id328"));
			Year.click();
			Select YearPick = new Select(Year);
			Thread.sleep(5000);
			YearPick.selectByValue(YearVal);
			Thread.sleep(5000);
			// Select Baseline Source
			WebElement Source = driver.findElement(By.id("pg:frm:j_id331"));
			Source.click();
			Select SourcePick = new Select(Source);
			SourcePick.selectByValue(Sourceval);
			//HighlightElement.elementHighlight(Sourceval);
			//new Select(driver.findElement(By.id("pg:frm:j_id331"))).selectByVisibleText("Administrative records");
			driver.findElement(By.id("pg:frm:j_id337")).click();
			Thread.sleep(5000);
			// Enter value for Year 2014
			WebElement val2014 = driver.findElement(By.id("pg:frm:ipStdTy1"));
			val2014.clear();
			HighlightElement.elementHighlight(val2014);
			val2014.sendKeys(Impval2014);
			//// Enter value for Year 2015
			WebElement val2015 = driver.findElement(By.id("pg:frm:ipStdTy2"));
			val2015.clear();
			HighlightElement.elementHighlight(val2015);
			val2015.sendKeys(Impval2015);
			//driver.findElement(By.id("pg:frm:j_id339")).click();
			// Enter value for Year 2016
			WebElement val2016 = driver.findElement(By.id("pg:frm:ipStdTy3"));
			val2016.clear();
			HighlightElement.elementHighlight(val2016);
			val2016.sendKeys(Impval2016);
			// Enter Due Date Value
			WebElement DDate2 = driver.findElement(By.id("pg:frm:j_id339"));
			DDate2.clear();
			DDate2.sendKeys(DueDate2);
			driver.findElement(By.xpath("//td[@onclick='DatePicker.datePicker.selectDate(this);']")).click();
			// Enter Due date Value
			WebElement DDate1 = driver.findElement(By.id("pg:frm:j_id337"));
			DDate1.clear();
			HighlightElement.elementHighlight(DDate1);
			DDate1.sendKeys(DueDate1);
			// Enter Due Date value
			WebElement DDate3 = driver.findElement(By.id("pg:frm:j_id341"));
			DDate3.click();
			DDate3.clear();
			HighlightElement.elementHighlight(DDate3);
			DDate3.sendKeys(DueDate3);
			// Enter Comment in the comment section for indicator
			WebElement CommentSection = driver.findElement(By.name("pg:frm:j_id345"));
			HighlightElement.elementHighlight(CommentSection);
			CommentSection.sendKeys(Comment);
			//Click on save button
			WebElement Save = driver.findElement(By.xpath("//a[@id='pg:frm:j_id312']/strong"));
			HighlightElement.elementHighlight(Save);
			Save.click();
				
				}
		
	   //Add a custom Impact indicator to the CN with status as not yet submitted
	 @Test  (priority = 2)
	 public static void CustomImpactInd() throws BiffException, IOException, Exception  {
				
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			Record = wb.getSheet("Record").getCell(0,1).getContents();
			String data01= wb.getSheet("Impact_Ind_Details_Custom").getCell(0,1).getContents();
			String data02= wb.getSheet("Impact_Ind_Details_Custom").getCell(1,1).getContents();
			String data03= wb.getSheet("Impact_Ind_Details_Custom").getCell(2,1).getContents();
			String data04= wb.getSheet("Impact_Ind_Details_Custom").getCell(3,1).getContents();
			String data05= wb.getSheet("Impact_Ind_Details_Custom").getCell(4,1).getContents();
			String data06= wb.getSheet("Impact_Ind_Details_Custom").getCell(5,1).getContents();
			String data07= wb.getSheet("Impact_Ind_Details_Custom").getCell(6,1).getContents();
			String data08= wb.getSheet("Impact_Ind_Details_Custom").getCell(7,1).getContents();
			String data09= wb.getSheet("Impact_Ind_Details_Custom").getCell(8,1).getContents();
			String data10= wb.getSheet("Impact_Ind_Details_Custom").getCell(9,1).getContents();
			String data11= wb.getSheet("Impact_Ind_Details_Custom").getCell(10,1).getContents();
			String data12= wb.getSheet("Impact_Ind_Details_Custom").getCell(11,1).getContents();
			String data13= wb.getSheet("Impact_Ind_Details_Custom").getCell(12,1).getContents();
			String data14= wb.getSheet("Impact_Ind_Details_Custom").getCell(13,1).getContents();
		    System.out.println(Record);
			Thread.sleep(5000);
			WebElement record=driver.findElement(By.linkText(Record));
			HighlightElement.elementHighlight(record);
			record.click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Click on GOALS & IMPACT INDICATORS link
			WebElement impact = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
			Thread.sleep(3000);
			HighlightElement.elementHighlight(impact);
			Thread.sleep(3000);
			impact.click();
			Thread.sleep(3000);
			//Click on Add Custom Indicator link
			WebElement ind = driver.findElement(By.linkText("Add Custom Indicator"));
			Thread.sleep(3000);
			HighlightElement.elementHighlight(ind);
			Thread.sleep(2000);
			ind.click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter indicator name
			WebElement indname = driver.findElement(By.id("pg:frm:Fullname"));
			//HighlightElement.elementHighlight(indname);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			indname.sendKeys(data01);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Select the datatype
			WebElement datatype = driver.findElement(By.id("pg:frm:datatype"));
			//HighlightElement.elementHighlight(datatype);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			datatype.sendKeys(data02);
			//Select goal to link
			driver.findElement(By.xpath("//div[2]/div/div/table/tbody/tr/td/span/span")).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//WebElement country = driver.findElement(By.id("pg:frm:CustomSelectCountry"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//country.sendKeys(data03);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Select decimal places field
			WebElement decimal = driver.findElement(By.id("pg:frm:ipdecimalplacenew"));
			//HighlightElement.elementHighlight(decimal);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			decimal.sendKeys(data04);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter the baseline value
			WebElement value = driver.findElement(By.id("pg:frm:BaselineValue"));
			//HighlightElement.elementHighlight(value);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			value.sendKeys(data05);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter baseline year
			WebElement year = driver.findElement(By.id("pg:frm:BaselineYear"));
			//HighlightElement.elementHighlight(year);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			year.sendKeys(data06);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Select baseline source value from dropdown
			WebElement source = driver.findElement(By.id("pg:frm:BaselineSource"));
			//HighlightElement.elementHighlight(source);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(5000);
			source.sendKeys(data07);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target year value
			WebElement year1 = driver.findElement(By.id("pg:frm:Tyear1"));
			//HighlightElement.elementHighlight(year1);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			year1.sendKeys(data08);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target year value
			WebElement year2 = driver.findElement(By.id("pg:frm:Tyear2"));
			//HighlightElement.elementHighlight(year2);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			year2.sendKeys(data09);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target year value
			WebElement year3 = driver.findElement(By.id("pg:frm:Tyear3"));
			//HighlightElement.elementHighlight(year3);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			year3.sendKeys(data10);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter report due date
			WebElement report1 = driver.findElement(By.id("pg:frm:Ryear1"));
			HighlightElement.elementHighlight(report1);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			report1.sendKeys(data11);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter report due date
			WebElement report2 = driver.findElement(By.id("pg:frm:Ryear2"));
			//HighlightElement.elementHighlight(report2);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			report2.sendKeys(data12);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter report due date
			WebElement report3 = driver.findElement(By.id("pg:frm:Ryear3"));
			//HighlightElement.elementHighlight(report3);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			report3.sendKeys(data13);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter the comment
			WebElement comment = driver.findElement(By.id("pg:frm:comment"));
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//HighlightElement.elementHighlight(comment);
			comment.sendKeys(data14);
			//Click on save button
			WebElement save=driver.findElement(By.xpath("//span[@id='pg:frm:j_id253']/a/strong"));
			HighlightElement.elementHighlight(save);
			Thread.sleep(5000);
			save.click();
		}

	  @Test (priority = 3)
		
		public void ObjectiveCreation() throws InterruptedException, BiffException, IOException{
			
			 Workbook wb = Workbook.getWorkbook(srcCNRegression);
			 Objectivetitle = wb.getSheet("ObjectiveDetails").getCell(0, 1).getContents();
			 rowcount = wb.getSheet("ObjectiveDetails").getRows();

			 Record = wb.getSheet("Record").getCell(0,1).getContents();
			 driver.findElement(By.linkText("Concept Notes")).click();
			 Thread.sleep(2000);
			 RecordLink = driver.findElement(By.linkText(Record));
			 Thread.sleep(2000);
			 //HighlightElement.elementHighlight(RecordLink);
			 Thread.sleep(5000);
			 RecordLink.click();
			 Thread.sleep(2000);
			 driver.findElement(By.linkText("OBJECTIVES & OUTCOME INDICATORS")).click();
			 Thread.sleep(2000);
			 for (i=1; i<rowcount; ++i) {
				 Thread.sleep(5000);
				 driver.findElement(By.linkText("Add Objective")).click();  
				 Thread.sleep(5000);
				 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				 Thread.sleep(5000);
				 WebElement objectivename = driver.findElement(By.id("page:frm:FullNameNew"));
			     objectivename.sendKeys(Objectivetitle);
			     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			     driver.findElement(By.linkText("Save")).click(); 
			     Thread.sleep(5000);
			   }
			 }			

	  @Test  (priority = 4)
		
		public void StdOutcomeIndicator() throws InterruptedException, BiffException, IOException {
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
		    OutcomeIndicatorName = wb.getSheet("OutcomeIndicator").getCell(1,1).getContents();
			System.out.println(OutcomeIndicatorName);
			Integer x = Integer.valueOf(OutcomeIndicatorName);
			System.out.println(x);
			String BaseLineval = wb.getSheet("OutcomeIndicator").getCell(2, 1).getContents();
			String YearVal = wb.getSheet("OutcomeIndicator").getCell(3, 1).getContents();
			String Sourceval = wb.getSheet("OutcomeIndicator").getCell(4, 1).getContents();
			String DecimalPlaces = wb.getSheet("OutcomeIndicator").getCell(5, 1).getContents();	
			String Impval2014 = wb.getSheet("OutcomeIndicator").getCell(1,4).getContents();
			String Impval2015 = wb.getSheet("OutcomeIndicator").getCell(1,5).getContents();
			String Impval2016 = wb.getSheet("OutcomeIndicator").getCell(1,6).getContents();
			String Comment = wb.getSheet("OutcomeIndicator").getCell(1, 7).getContents();
			String DueDate1 = wb.getSheet("OutcomeIndicator").getCell(2,4).getContents();
			String DueDate2 = wb.getSheet("OutcomeIndicator").getCell(2, 5).getContents();
			String DueDate3 = wb.getSheet("OutcomeIndicator").getCell(2, 6).getContents();
			Thread.sleep(1000);
			driver.findElement(By.linkText("Concept Notes")).click();
			Thread.sleep(2000);
			RecordLink = driver.findElement(By.linkText(Record));
			HighlightElement.elementHighlight(RecordLink);
			RecordLink.click();
			Thread.sleep(5000);
			WebElement GoalImpLink  = driver.findElement(By.linkText("OBJECTIVES & OUTCOME INDICATORS"));
			HighlightElement.elementHighlight(GoalImpLink);
			GoalImpLink.click();
			Thread.sleep(5000);
			// Click on Add Indicator button
			WebElement StdAddInd = driver.findElement(By.linkText("Add Standard Indicator"));
			HighlightElement.elementHighlight(StdAddInd);
			StdAddInd.click();
			Thread.sleep(5000);
			// Select Indicator
			WebElement selectIndicator=driver.findElement(By.id("page:frm:SelectCatIndicator"));
			HighlightElement.elementHighlight(selectIndicator);
			Select Indicator=new Select(selectIndicator);
			Thread.sleep(5000);
			Indicator.selectByIndex(x);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@id='page:frm:SelectIndicatorPicklist']/strong")).click();
			Thread.sleep(20000);
			driver.findElement(By.id("page:frm:StdPanelGoal")).click();
			Thread.sleep(15000);
			//Select Decimal place value
			WebElement DecimalPlace= driver.findElement(By.id("page:frm:ipdecimalplacestand"));
			DecimalPlace.click();
			Select DecimalPick = new Select(DecimalPlace);
			DecimalPick.selectByValue(DecimalPlaces);
			//Select Baseline Value
			WebElement BaseLine = driver.findElement(By.id("page:frm:stdBvalue"));
			BaseLine.clear();
			BaseLine.sendKeys(BaseLineval);
			Thread.sleep(5000);
			//Select Baseline  year
			WebElement Year = driver.findElement(By.id("page:frm:j_id340"));
			Year.click();
			Select YearPick = new Select(Year);
			Thread.sleep(5000);
			YearPick.selectByValue(YearVal);
			Thread.sleep(5000);
			//Select source value
			WebElement Source = driver.findElement(By.id("page:frm:j_id343"));
			Source.click();
			Select SourcePick = new Select(Source);
			SourcePick.selectByValue(Sourceval);
			Thread.sleep(5000);
			//Enter Value in Year 2014
			WebElement val2014 = driver.findElement(By.id("page:frm:stdTargetY1"));
			val2014.clear();
			val2014.sendKeys(Impval2014);
			//Enter Value in Year 2015
			WebElement val2015 = driver.findElement(By.id("page:frm:stdTargetY2"));
			val2015.clear();
			val2015.sendKeys(Impval2015);
			//driver.findElement(By.id("pg:frm:j_id339")).click();
			// Enter Value in Year 2016
			WebElement val2016 = driver.findElement(By.id("page:frm:stdTargetY3"));
			val2016.clear();
			val2016.sendKeys(Impval2016);
			// Select Due Date
			WebElement DDate2 = driver.findElement(By.id("page:frm:j_id349"));
			DDate2.clear();
			HighlightElement.elementHighlight(DDate2);
			DDate2.sendKeys(DueDate2);
			driver.findElement(By.xpath("//td[@onclick='DatePicker.datePicker.selectDate(this);']")).click();
			WebElement DDate1 = driver.findElement(By.id("page:frm:j_id351"));
			DDate1.clear();
			HighlightElement.elementHighlight(DDate1);
			DDate1.sendKeys(DueDate1);
			// Select Due date
			WebElement DDate3 = driver.findElement(By.id("page:frm:j_id353"));
			DDate3.click();
			HighlightElement.elementHighlight(DDate3);
			DDate3.clear();
			Thread.sleep(1000);
			DDate3.sendKeys(DueDate3);
			//Enter Comment in the comment section of indicator
			WebElement commentSection =  driver.findElement(By.name("page:frm:j_id357"));
			HighlightElement.elementHighlight(commentSection);
			commentSection.sendKeys(Comment);
			//Click on save button
			driver.findElement(By.xpath("//a[@id='page:frm:j_id315']/strong")).click();
				
				}
	  
	//Add a custom Outcome indicator to the CN with status as not yet submitted
	 @Test  (priority = 5)
	 public static void CustomOutcomeInd() throws BiffException, IOException, Exception  {
				
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		String data01=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(0,1).getContents();
		String data02=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(1,1).getContents();
		String data03=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(2,1).getContents();
		String data04=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(3,1).getContents();
		String data05=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(4,1).getContents();
		String data06=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(5,1).getContents();
		String data07=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(6,1).getContents();
		String data08=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(7,1).getContents();
		String data09=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(8,1).getContents();
		String data10=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(9,1).getContents();
		String data11=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(10,1).getContents();
		String data12=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(11,1).getContents();
		String data13=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(12,1).getContents();
		String data14=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(13,1).getContents();
		
		Thread.sleep(5000);
		//Click on "OBJECTIVES & OUTCOME INDICATORS link
		WebElement outcome = driver.findElement(By.linkText("OBJECTIVES & OUTCOME INDICATORS"));
		HighlightElement.elementHighlight(outcome);
		Thread.sleep(5000);
		outcome.click();
		//driver.findElement(By.xpath("//td[2]/div/a/strong")).click();
		//Click on add custom indicator link
		Thread.sleep(1000);
		WebElement ind =driver.findElement(By.linkText("Add Custom Indicator"));
		HighlightElement.elementHighlight(ind);
		Thread.sleep(2000);
		ind.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//Enter indicator name
		WebElement indname = driver.findElement(By.id("page:frm:Fullname"));
		//HighlightElement.elementHighlight(indname);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		indname.sendKeys(data01);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Select datatype value
		WebElement datatype = driver.findElement(By.id("page:frm:datatype"));
		//HighlightElement.elementHighlight(datatype);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		datatype.sendKeys(data02);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[2]/div/div/table/tbody/tr/td/span/span")).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//WebElement country = driver.findElement(By.id("page:frm:CustomSelectCountry"));
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//country.sendKeys(data03);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Select decimal value
		WebElement decimal = driver.findElement(By.id("page:frm:ipdecimalplacenew"));
		//HighlightElement.elementHighlight(decimal);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		decimal.sendKeys(data04);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline value
		WebElement value = driver.findElement(By.id("page:frm:BaselineValue"));
		//HighlightElement.elementHighlight(value);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		value.sendKeys(data05);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline year value
		WebElement year = driver.findElement(By.id("page:frm:BaselineYear"));
		//HighlightElement.elementHighlight(year);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		year.sendKeys(data06);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline source value
		WebElement source = driver.findElement(By.id("page:frm:BaselineSource"));
		//HighlightElement.elementHighlight(source);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		source.sendKeys(data07);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target year
		WebElement year1 = driver.findElement(By.id("page:frm:Tyear1"));
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//HighlightElement.elementHighlight(year1);
		Thread.sleep(2000);
		year1.sendKeys(data08);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target year
		WebElement year2 = driver.findElement(By.id("page:frm:Tyear2"));
		//HighlightElement.elementHighlight(year2);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		year2.sendKeys(data09);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target year
		WebElement year3 = driver.findElement(By.id("page:frm:Tyear3"));
		//HighlightElement.elementHighlight(year3);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		year3.sendKeys(data10);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter report due date
		WebElement report1 = driver.findElement(By.id("page:frm:Ryear1"));
		//HighlightElement.elementHighlight(report1);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		report1.sendKeys(data11);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter report due date
		WebElement report2 = driver.findElement(By.id("page:frm:Ryear2"));
		//HighlightElement.elementHighlight(report2);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		report2.sendKeys(data12);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter report due date
		WebElement report3 = driver.findElement(By.id("page:frm:Ryear3"));
		//HighlightElement.elementHighlight(report3);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		report3.sendKeys(data13);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter comment
		WebElement comment = driver.findElement(By.id("page:frm:comment"));
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//HighlightElement.elementHighlight(comment);
		Thread.sleep(2000);
		comment.sendKeys(data14);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		//Click on save button
		WebElement save=driver.findElement(By.xpath("//span[@id='page:frm:j_id265']/a/strong"));
			HighlightElement.elementHighlight(save);
			Thread.sleep(5000);
			save.click();
			
			}
	 

		//Add a custom Output indicator to the CN with status as not yet submitted
	@Test  (priority = 6)
	public static void CustomeCoverageOutput() throws BiffException, IOException, Exception  {
					
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			String data00 = wb.getSheet("Output_Ind_Details").getCell(0,1).getContents();
			String data01=  wb.getSheet("Output_Ind_Details").getCell(1,1).getContents();
			String data02=  wb.getSheet("Output_Ind_Details").getCell(2,1).getContents();
			String data03=  wb.getSheet("Output_Ind_Details").getCell(3,1).getContents();
			String data04=  wb.getSheet("Output_Ind_Details").getCell(4,1).getContents();
			String data05=  wb.getSheet("Output_Ind_Details").getCell(5,1).getContents();
			String data06=  wb.getSheet("Output_Ind_Details").getCell(6,1).getContents();
			String data07=  wb.getSheet("Output_Ind_Details").getCell(7,1).getContents();
			String data08=  wb.getSheet("Output_Ind_Details").getCell(8,1).getContents();
			String data09=  wb.getSheet("Output_Ind_Details").getCell(9,1).getContents();
			String data10=  wb.getSheet("Output_Ind_Details").getCell(10,1).getContents();
			String data11=  wb.getSheet("Output_Ind_Details").getCell(11,1).getContents();
			String data12=  wb.getSheet("Output_Ind_Details").getCell(12,1).getContents();
			String data13=  wb.getSheet("Output_Ind_Details").getCell(13,1).getContents();
			String data14=  wb.getSheet("Output_Ind_Details").getCell(14,1).getContents();
			String data15=  wb.getSheet("Output_Ind_Details").getCell(15,1).getContents();
			String data16=  wb.getSheet("Output_Ind_Details").getCell(16,1).getContents();
			String data17=  wb.getSheet("Output_Ind_Details").getCell(17,1).getContents();
			String data18=  wb.getSheet("Output_Ind_Details").getCell(18,1).getContents();
			String data19=  wb.getSheet("Output_Ind_Details").getCell(19,1).getContents();

			
			Thread.sleep(5000);
			//Click on arrow button of Coverage and Output Indicators link
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//img[@title='Expand'])[2]")).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//driver.findElement(By.xpath("//li[@id='Module7']/div/a")).click();
			WebElement intervention = driver.findElement(By.linkText(data00));
			HighlightElement.elementHighlight(intervention);
			Thread.sleep(2000);
			intervention.click();
			Thread.sleep(2000);
			//Click on Add Custom indicator link
			WebElement ind =driver.findElement(By.linkText("Add Custom Indicator"));
			HighlightElement.elementHighlight(ind);
			Thread.sleep(2000);
			ind.click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(5000);
			//Enter name of the indicator
			WebElement indname = driver.findElement(By.name("page:frm:j_id414"));
			//HighlightElement.elementHighlight(indname);
			Thread.sleep(2000);
			indname.sendKeys(data01);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Select datatype from drop down
			WebElement datatype = driver.findElement(By.id("page:frm:DataType"));
			//HighlightElement.elementHighlight(datatype);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			datatype.sendKeys(data02);
			//Select PR name
			WebElement PR = driver.findElement(By.id("page:frm:newCustPR"));
			//HighlightElement.elementHighlight(PR);
			PR.sendKeys(data03);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter geographical location
			WebElement Geographical = driver.findElement(By.id("page:frm:TargetArea1"));
			//HighlightElement.elementHighlight(Geographical);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Geographical.sendKeys(data04);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter decimal values
			WebElement decimal = driver.findElement(By.id("page:frm:ipdecimalplacenew"));
			//HighlightElement.elementHighlight(decimal);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		    decimal.sendKeys(data05);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target accumulation value
			WebElement target = driver.findElement(By.id("page:frm:j_id443"));
			//HighlightElement.elementHighlight(target);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
			target.sendKeys(data06);
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//WebElement country = driver.findElement(By.id("page:frm:custSelectCountry"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//country.sendKeys(data07);
			Thread.sleep(5000);
			//Enter baseline value
			WebElement baseline = driver.findElement(By.name("page:frm:ipbvaluenew"));
			Thread.sleep(5000);
			//HighlightElement.elementHighlight(baseline);
			Thread.sleep(9000);
			baseline.sendKeys(data08);
			Thread.sleep(5000);
			//Enter baseline year value
			Thread.sleep(5000);
			WebElement year = driver.findElement(By.name("page:frm:j_id455"));
			//HighlightElement.elementHighlight(year);
			Thread.sleep(5000);
			year.sendKeys(data09);
			Thread.sleep(5000);
			//Enter baseline source value
			WebElement source = driver.findElement(By.name("page:frm:j_id458"));
			//HighlightElement.elementHighlight(source);
			Thread.sleep(5000);
			source.sendKeys(data10);
			//Enter target allocation for year1
			WebElement target1 = driver.findElement(By.id("page:frm:ipIndIY1new"));
			//HighlightElement.elementHighlight(target1);
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			target1.sendKeys(data11);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year2
			WebElement target2 = driver.findElement(By.id("page:frm:ipIndIY2new"));
			//HighlightElement.elementHighlight(target2);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(5000);
			target2.sendKeys(data12);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year3
			WebElement target3 = driver.findElement(By.id("page:frm:ipIndIY3new"));
			//HighlightElement.elementHighlight(target3);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(5000);
			target3.sendKeys(data13);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year4
			WebElement target4 = driver.findElement(By.id("page:frm:ipIndIY4new"));
			//HighlightElement.elementHighlight(target4);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(2000);
			target4.sendKeys(data14);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year1
			WebElement target5 = driver.findElement(By.id("page:frm:ipIndAY1new"));
			//HighlightElement.elementHighlight(target5);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(2000);
			target5.sendKeys(data15);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year2
			WebElement target6 = driver.findElement(By.id("page:frm:ipIndAY2new"));
			//HighlightElement.elementHighlight(target6);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(2000);
			target6.sendKeys(data16);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year3
			WebElement target7 = driver.findElement(By.id("page:frm:ipIndAY3new"));
			//HighlightElement.elementHighlight(target7);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(2000);
			target7.sendKeys(data17);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Enter target allocation for year4
			WebElement target8 = driver.findElement(By.id("page:frm:ipIndAY4new"));
			//HighlightElement.elementHighlight(target8);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(2000);
			target8.sendKeys(data18);
			//Enter comment
			WebElement comment = driver.findElement(By.name("page:frm:j_id525"));
			//HighlightElement.elementHighlight(comment);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			comment.sendKeys(data19);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Click on save button
			WebElement save =driver.findElement(By.xpath("//a[@id='page:frm:j_id425']/strong"));
			HighlightElement.elementHighlight(save);
			Thread.sleep(2000);
			save.click();
		}

}
