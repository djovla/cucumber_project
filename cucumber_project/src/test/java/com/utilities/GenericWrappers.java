package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.Date;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

//Here we will store re-usable Functions/Methods
public class GenericWrappers extends BaseClass {

	/********* Chrome browser launch ****************/

	public void launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	/********* Firefox browser launch ****************/

	public void launchFirefoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	/********* Edge browser launch ****************/

	public void launchEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	/***** Validate the web element is enabled or not? *****/
	public void ValidateWebelementIsEnabledOrNot(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isEnabled()) {
			System.out.println("The webelement is Enabled state***");
		} else {
			System.out.println("The webelement is NOT in Enabled state***");
		}
	}

	/** sendkeys by any locators for Editbox/Textbox *********/
	public void sendKeyByAnyLocator(By locator, String testdata) {
		WebElement ele = driver.findElement(locator);
		if (ele.isDisplayed()) {
			if (ele.isEnabled()) {
				ele.clear();
				ele.sendKeys(testdata);
			} else {
				System.out.println("element is disable state, please check the locator***");
			}
		} else {
			System.out.println("element is not displayed, please check the locator***");
		}
	}

	/************ click on any webelement *************/
	public void clickByAnylocator(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isDisplayed() && ele.isEnabled()) {
			ele.click();
		} else {
			System.out.println("The given locator is not displayed on DOM or not in enabled state****");
		}
	}

	/************ Method added for the radio button *************/
	public void clickByAnylocatorButton(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isEnabled()) {
			ele.click();
		} else {
			System.out.println("The given locator is not displayed on DOM or not in enabled state****");
		}
	}

	/******** Implicit wait *****************//*
												 * public void implicitWait(int TimeInMillySeconds) {
												 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
												 * TimeInMillySeconds)); }
												 */

	/********* Read Data from Properties file *********************/

	// to get the test data from Property file
	public void loadTestData(String path) {
		file = new File(path);
		fi = null;
		try {
			fi = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getData(String key) {
		String keyvlaue = null;
		try {
			keyvlaue = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
		return keyvlaue;
	}

	/**********
	 * Verify the web element has present or not on DOM (Document Object Model)
	 ***************/
	public void findTheWebelementByAnyLocator(By locator) {
		// Find the given locator, if the locator has present on Screen then the size of
		// locator is ONE '1'
		// else size of locator is ZERO '0'
		if (driver.findElements(locator).size() > 0) {
			System.out.println("The Given locator is present on DOM****" + locator);
		} else {
			System.out.println("The Given locator is NOT present on DOM, please check again****" + locator);
		}

	}

	/*** navigational methods ************/
	public void refreshScreen() {
		driver.navigate().refresh();
		/* implicitWait(5); */
	}

	/*********** timestamp **********/
	public String timestamp() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("ddMMMyyy_HHmmss");
		String timeTamp = df.format(d);
		return timeTamp;
	}

	/*****
	 * takescreenshot
	 * 
	 * @throws Exception
	 ************/
	public void takeScreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = ".//Screenshots//";
		FileHandler.copy(scrFile, new File(screenshotPath + "Test" + timestamp() + ".PNG"));
		System.out.println("Screenshot taken*** ");
	}

	public void takeScreenshot(ITestResult res) throws Exception {

		projectDir = System.getProperty("user.dir");
		// code to change to work on mac system
		// screenshotPath = projectDir + "\\Screenshots\\";

		screenshotPath = projectDir + "//Screenshots//";
		className = res.getTestClass().getName().trim();//
		methodName = res.getName().trim();//
		// STATUS_PackageName.ClassName_MethodName_Timestamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "PASS_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "FAIL_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}

	}

	/***************** Classes Added JosepH ***********************/
	/***
	 * select value Randomly using the option the method use the index
	 ************/

	public void selectOptionRandomly(By locator) {
		Select se = new Select(driver.findElement(locator));
		int number = se.getOptions().size();
		Random r = new Random();

		se.selectByIndex(r.nextInt(number));

	}

	/*** Click One Randomly one Button with same value in the page ************/
	public void RandomClickOneButton(List<WebElement> buttons) {
		int number = buttons.size();
		Random r = new Random();
		buttons.get(r.nextInt(number)).click();

	}

	/*** get Text from element by locator in the page ************/
	public String getTextByLocator(By locator) {
		return driver.findElement(locator).getText();
	}

	/*** Get List of Element by locator same value in the page ************/
	public List<WebElement> getListElementByLocator(By locator) {
		return driver.findElements(locator);
	}

	/*** Print to Console List of Element in the page ************/
	public void printListElementConsole(List<WebElement> list) {
		for (WebElement element : list) {
			System.out.println(element.getText());
		}
	}

	/************* Close Current Window ***************/
	public void closeCurrentWindow() {
		driver.close();
	}

	/************************
	 * Tracking the window and store the value in trackofWindow
	 *********/
	public void trackingOfWindow() {
		trackOfWindow = driver.getWindowHandle();
	}

	/**********
	 * Overload Method to handle popup whit accuracy using the trackOfWindow
	 ********************/
	public void popupHandleToCloseAllChildWindow() throws InterruptedException {
		// get the main windown name from the static variable
		if (!trackOfWindow.equalsIgnoreCase("")) {
			String mainWindowName = trackOfWindow;
			System.out.println("mainWindowName:" + mainWindowName);

			Set<String> allWindowNames = driver.getWindowHandles();
			System.out.println("allWindowNames:" + allWindowNames);

			// Close the child window (popups)
			for (String abc : allWindowNames) {// 2
				// validate the window name is parent window /Child window?
				if (!mainWindowName.equals(abc)) {
					// switch to child window
					driver.switchTo().window(abc);
					Thread.sleep(3000);
					// Close my child window
					driver.close();
				}
			}
			// move cursor point to back to mainwindow
			driver.switchTo().window(mainWindowName);
			// driver.close();
		} else {
			System.out.println("There is no active Window register in the track Window");
		}
	}

	/*****
	 * Method that initialize the Actions Variable from the Base Class
	 ***********/
	public void initiateAction() {
		act = new Actions(driver);
	}

	/**************
	 * Method that Drag From and Drop to the destination
	 ************************/
	public void dragAndDrop(By from, By to) {
		initiateAction();
		WebElement efrom = driver.findElement(from);
		WebElement eto = driver.findElement(to);
		act.dragAndDrop(efrom, eto).build().perform();
	}

	public void moveToElement(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).perform();
	}

	public void moveToElementAndClick(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).click().perform();
	}

	public void moveMouseToElement(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).build().perform();
	}

	// Right click on Element method
	public void rightClickOnElement(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.contextClick(element).perform();
	}

	// Change Input to Capital Letter in the input text
	public void ChangeInputToCapitalOnElement(By locator, String input) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys(input).build().perform();
	}

	// overload method sendKeyByAnyLocator--------------------------
	public void sendKeyByAnyLocator(By locator, Keys key) {
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.clear();
				element.sendKeys(key);
			} else {
				System.out.println("element is disable state, please check the locator***");
			}
		} else {
			System.out.println("element is not displayed, please check the locator***");
		}
	}

	/********************* END Joseph Method adding *************************/

	/******************** Frames Handling *******************/

	public int iframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesForElement(By locator) {
		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramaecount = iframeCount();// 3
		// if given locater has present on webpage, then the element size would be '1'
		// else '0'
		// elementpresenceCount = driver.findElements(locator).size();// 0
		while (elementpresenceCount == 0 && loop < maxFramaecount) {
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();// 2
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "
						+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;// 1
		}
		return elementpresenceCount;
	}

	/************
	 * popupHandle
	 * 
	 * @throws InterruptedException
	 *********************************/
	public void popupHandleToCloseTheChildWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		for (String abc : allWindowNames) {// 2
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(abc)) {
				// switch to child window
				driver.switchTo().window(abc);
				Thread.sleep(3000);
				// Close my child window
				driver.close();
			}
		}
		// move cursor point to back to mainwindow
		driver.switchTo().window(mainWindowName);
		// driver.close();
	}

	public void navigateToPopupWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String string : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(string)) {
				// switch to child window
				driver.switchTo().window(string);
				Thread.sleep(3000);
			}
		}

	}

	/*********** SwithchToWindow using Tab ***************************/
	public void switchToNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();// abc

		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));

		// Switch back to original window
		// driver.switchTo().window(windowHandle);
	}

	/***********
	 * SwithchToWindow using Tab then close the New Tab againg back to Parent Window
	 ***************************/
	public void switchAndCloseNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		// Close the newly Opened Window
		driver.close();
		// Switch back to original window
		driver.switchTo().window(windowHandle);
	}

	/*************** Window handler ****************/

	public void WindowHandler() {
		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			System.out.println(driver.getCurrentUrl() + " " + driver.getTitle());

		}

	}

	/******** get text info *********/

	public void GetText(By ShahedProLocators) {
		WebElement el = driver.findElement(ShahedProLocators);
		String text = el.getText();
		System.out.println(text);

	}

	/************** Alert Handle *************************/
	public void alertHandleByAccept() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.accept();
	}

	public void alertHandleByDismiss() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.dismiss();
	}
	/********************* Close Current Window ******************************/

}