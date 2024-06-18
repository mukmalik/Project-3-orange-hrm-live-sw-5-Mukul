package com.orangehrmlive.demo.pages;

import com.aventstack.extentreports.Status;
import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class HomePage extends Utility {

    @CacheLookup
    @FindBy(name = "username")
    WebElement usernameField;

    @CacheLookup
    @FindBy(name = "password")
    WebElement passwordField;

    @CacheLookup
    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginButton;

    @CacheLookup
    @FindBy(xpath = "//img[@alt='client brand banner']")
    WebElement orangeHRMLogo;


    public void enterUsername(String username){
        sendTextToElement(usernameField,username);
        Reporter.log("Enter user name"+username);
        CustomListeners.test.log(Status.PASS,"Enter value"+username);
    }

    public void enterPassword(String password){
        sendTextToElement(passwordField,password);
    }
    public void clickOnLoginButton(){
        clickOnElement(loginButton);
    }
    public void loginToApplication(String username,String password){
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
    }
    public boolean verifyOrangeHRMLogo() {
        return driver.findElement((By) orangeHRMLogo).isDisplayed();
    }


}