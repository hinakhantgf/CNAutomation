package MyPackage;


import java.sql.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HighlightElement extends CNBase {

	public static void elementHighlight(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: red; border: 3px solid red;");
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");
		}
	}
}
