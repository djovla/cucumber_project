package stepdefinitions;

import com.objectrepository.TDD_Locators;
import com.utilities.GenericWrappers;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login extends GenericWrappers{
	TDD_Locators loc = new TDD_Locators();
	public Login() {
		 loadTestData("./src/test/resources/TDD_inputdata.properties");
	}
	
	@Given("Open TDD URL")
	public void launch_TDD_application() throws Exception{
		launchChromeBrowser();
		driver.get(getData("TDD_URL"));
		Thread.sleep(3000);
		
    }
	
	@And("Click Institution Radion")
	public void click_Institution_Radion_Button(){
	  	clickByAnylocatorButton(loc.TDDPage_Institution_RadioButton);
    }
	
	@When("Click Institution Continue GetMessage")
	public void click_Institution_Continue_GetMessage_Button(){
	  	clickByAnylocator(loc.TDDPage_Continue_Button);
	  	clickByAnylocator(loc.TDDPage_Ok_Alert_Button);
	  	System.out.println("=================================");
	  	System.out.println(driver.findElement(loc.TDDPage_Error_Message_Div).getText());
	  	System.out.println("=================================");
     }
	@And("Refresh Page")
	public void refresh_Page(){
		  refreshScreen();
			
	  }
	@And("Type Invalid Address Line2")
	public void type_Invalid_Address_Line2_Input(){
		  sendKeyByAnyLocator(loc.TDDPage_AddressLine2_Input,getData("ADDR_INV"));
			
	  }
	@And("Type Invalid ZIPPIN")
	public void type_Invalid_ZIPPIN_Input(){
		  sendKeyByAnyLocator(loc.TDDPage_ZipPin_Input,getData("ZIPPIN_INV"));
			
	  }
	@Then("Click Continue")
	public void tc_007_Click_Continue_Button(){
		  clickByAnylocator(loc.TDDPage_Continue_Button);
			
	  }
}
