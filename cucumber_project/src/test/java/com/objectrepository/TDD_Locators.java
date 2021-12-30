package com.objectrepository;

import org.openqa.selenium.By;

public class TDD_Locators {

	
		public final By TDDPage_Institution_RadioButton =By.id("entity");
		public final By TDDPage_Continue_Button =By.id("regi_continue");
		public final By TDDPage_Ok_Alert_Button = By.xpath("//*[@id=\"ErrorMessagePopUp\"]/div[1]/div/div/div[2]/button");
		public final By TDDPage_Error_Message_Div =By.xpath("//*[@id=\"container\"]/div[4]/div/div[1]/form/div[6]");
		public final By TDDPage_AddressLine2_Input = By.xpath("//*[@id=\"container\"]/div[4]/div/div[1]/form/div[2]/div[2]/input");
		public final By TDDPage_ZipPin_Input = By.xpath("//*[@id=\"container\"]/div[4]/div/div[1]/form/div[2]/div[6]/input");
}

/*
 * public final By TDDPage_Institution_RadioButton =By.id("entity"); public
 * final By TDDPage_Continue_Button =By.id("regi_continue"); public final By
 * TDDPage_Ok_Alert_Button =
 * By.xpath("//*[@id=\"ErrorMessagePopUp\"]/div[1]/div/div/div[2]/button");
 * public final By TDDPage_Error_Message_Div
 * =By.xpath("//*[@id=\"container\"]/div[4]/div/div[1]/form/div[6]"); public
 * final By TDDPage_AddressLine2_Input =
 * By.xpath("//*[@id=\"container\"]/div[4]/div/div[1]/form/div[2]/div[2]/input")
 * ; public final By TDDPage_ZipPin_Input =
 * By.xpath("//*[@id=\"container\"]/div[4]/div/div[1]/form/div[2]/div[6]/input")
 * ; }
 */

