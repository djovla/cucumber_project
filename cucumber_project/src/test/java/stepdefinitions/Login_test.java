package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_test {
     
	WebDriver driver;
	
	@Given ("Open FB URL")
	 public void open_fb_url() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://en-gb.facebook.com/");
		System.out.println("Succesfully Open the FB URL");
	}
	@When("User enter invalid credentials")
     public void user_enter_invalid_credentials() {
		driver.findElement(By.name("email")).sendKeys("srewwvsr1234@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("asfaskjfask");	
	}	
	@And("Click on Login_test button")
	public void click_on_login_button() throws Exception{
		driver.findElement(By.name("login")).click();
		Thread.sleep(9000);
	}
	
	@Then("Application should display the validation Message")
	public void application_should_display_the_validation_message() {
		
		WebElement errorMessage = driver.findElement(By.linkText("Find your account and log in."));
		
		if(errorMessage.isDisplayed()) {
		 System.out.println("Given Credentials where invalid .......");	
		}
		else {
			System.out.println("Given Credentials where valid........");
		}
		}
}
