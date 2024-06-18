package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.AddUserPage;
import com.orangehrmlive.demo.pages.DashboardPage;
import com.orangehrmlive.demo.pages.HomePage;
import com.orangehrmlive.demo.pages.ViewSystemUsersPage;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

import java.util.Random;

@Listeners(CustomListeners.class)
public class UserTest extends BaseTest {

    HomePage homePage;
    DashboardPage dashboardPage;
    ViewSystemUsersPage viewSystemUsersPage;
    AddUserPage addUserPage;
    Random random = new Random();
    int num = random.nextInt();


    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        dashboardPage = new DashboardPage();
        viewSystemUsersPage = new ViewSystemUsersPage();
        addUserPage = new AddUserPage();
    }

    // 1.adminShouldAddUserSuccessFully().
    @Test(groups = {"smoke", "sanity", "regression"})
    public void adminShouldAddUserSuccessFully() {
        // Login to Application
        homePage.loginToApplication("Admin", "admin123");
        // click On "Admin" Tab
        dashboardPage.clickOnAdminTab();
        // Verify "System Users" Text
        String expectedText = "System Users";
        String actualText = viewSystemUsersPage.verifySystemUserText();
        Assert.assertEquals(actualText, expectedText, "System Users is not displayed");
        // click On "Add" button
        addUserPage.mouseHoverAndClickOnUserRoleDropDownInAddUser();
        addUserPage.mouseHoverAndCLickOnAdminOptionInUserRoleDropDownInAddUser();
        // Verify "Add User" Text
        String expectedAddUsersMessage = "Add User";
        String actualAddUserMessage = addUserPage.verifyAddUserText();
        Assert.assertEquals(actualAddUserMessage, expectedAddUsersMessage);
        // Select User Role "Admin"
        addUserPage.mouseHoverAndClickOnUserRoleDropDownInAddUser();
        addUserPage.mouseHoverAndCLickOnAdminOptionInUserRoleDropDownInAddUser();
        // enter Employee Name "Ananya Dash" take other name
        addUserPage.sendDataToEmployeeNameFieldInAddUser(" x x Zenaida Schultz");
        // enter Username
        addUserPage.sendDataToUserNameFieldInAddUser("Prime" + num);
        // Select status "Disable"
        addUserPage.clickOnStatusDropDownMenuInAddUser();
        addUserPage.mouseHoverAndClickOnDisabledOptionInAddUser();
        // enter password
        addUserPage.sendDataToPasswordFieldInAddUser("prime123");
        // enter Confirm Password
        addUserPage.sendDataToConfirmPasswordFieldInAddUser("prime123");
        // click On "Save" Button
        addUserPage.clickOnSaveButtonInAddUser();
        // verify message "Successfully Saved"
        String expectedSuccessfullySavedMessage = "Successfully Saved";
        String actualSuccessfullySavedMessage = addUserPage.getSuccessfullySavedMessageInAddUser();
        Assert.assertEquals(actualSuccessfullySavedMessage, expectedSuccessfullySavedMessage);

    }

    // 2. searchTheUserCreatedAndVerifyIt().
    @Test(groups = {"smoke", "regression"})
    public void searchTheUserCreatedAndVerifyIt() {
        // Login to Application
        homePage.loginToApplication("Admin", "admin123");
        // click On "Admin" Tab
        dashboardPage.clickOnAdminTab();
        // Verify "System Users" Text
        String expectedText = "System Users";
        String actualText = viewSystemUsersPage.verifySystemUserText();
        Assert.assertEquals(actualText, expectedText, "System Users is not displayed");
        // Enter Username
        viewSystemUsersPage.sendDataToUserNameFieldInSystemUsers("Prime" + num);
        // Select User Role
        viewSystemUsersPage.clickOnUserRoleDropDownInSystemUsers();
        // Select Status
        viewSystemUsersPage.mouseHoverAndClickOnAdminOptionInUserRoleDropDownInSystemUsers();
        viewSystemUsersPage.clickOnStatusDropDownInSystemUsers();
        viewSystemUsersPage.mouseHoverAndCLickOndDisableStatusOptionInSystemUsers();
        // Click on "Search" Button
        viewSystemUsersPage.clickOnSearchButtonInSystemUsers();
        // Verify the User should be in Result list
        String expectedUserName = "Prime" + num;
        String actualUserName = viewSystemUsersPage.getDataFromUserNameInRecord();
        Assert.assertEquals(actualUserName, expectedUserName);
    }

    // 3. verifyThatAdminShouldDeleteTheUserSuccessFully().
    @Test(groups = {"regression"})
    public void verifyThatAdminShouldDeleteTheUserSuccessFully() {
        // Login to Application
        homePage.loginToApplication("Admin", "admin123");
        // click On "Admin" Tab
        dashboardPage.clickOnAdminTab();
        // Verify "System Users" Text
        String expectedText = "System Users";
        String actualText = viewSystemUsersPage.verifySystemUserText();
        Assert.assertEquals(actualText, expectedText, "System Users is not displayed");
        // Enter Username
        viewSystemUsersPage.sendDataToUserNameFieldInSystemUsers("Prime" + num);
        // Select User Role
        viewSystemUsersPage.clickOnUserRoleDropDownInSystemUsers();
        // Select Status
        viewSystemUsersPage.mouseHoverAndClickOnAdminOptionInUserRoleDropDownInSystemUsers();
        viewSystemUsersPage.clickOnStatusDropDownInSystemUsers();
        viewSystemUsersPage.mouseHoverAndCLickOndDisableStatusOptionInSystemUsers();
        // Click on "Search" Button
        viewSystemUsersPage.clickOnSearchButtonInSystemUsers();
        // Verify the User should be in Result list.
        String expectedUserName = "Prime" + num;
        String actualUserName = viewSystemUsersPage.getDataFromUserNameInRecord();
        Assert.assertEquals(actualUserName, expectedUserName);
        // Click on Check box
        viewSystemUsersPage.setClickOnCheckbox();
        // Click on Delete Button
        viewSystemUsersPage.setClickOnDeleteBtn();
        // Popup will display
        // Click on Ok Button on Popup
        viewSystemUsersPage.setClickOnYesDelete();
        // verify message "Successfully Deleted"
        String expectedDeletedMsg = "Successfully Deleted";
        String actualDeletedMsg = viewSystemUsersPage.getTextDeletedMsg();
        Assert.assertEquals(expectedDeletedMsg, actualDeletedMsg);

    }

    // 4. searchTheUserAndVerifyTheMessageRecordFound()
    @Test(dataProvider = "SystemUsers", dataProviderClass = TestData.class, groups = "regression")
    public void searchTheUserAndVerifyTheMessageRecordFound(String username, String userRole, String employeeName, String status) throws InterruptedException {
        // Login to Application
        homePage.loginToApplication("Admin", "admin123");
        // click On "Admin" Tab
        dashboardPage.clickOnAdminTab();
        // Verify "System Users" Text
        String expectedText = "System Users";
        String actualText = viewSystemUsersPage.verifySystemUserText();
        Assert.assertEquals(actualText, expectedText, "System Users is not displayed");
        // Enter Username <username >
        viewSystemUsersPage.setEnterUsername(username);
        // Select User Role <userRole >
        viewSystemUsersPage.clickOnUserRoleDropDownInSystemUsers();
        viewSystemUsersPage.setSelectUserRole(userRole);
        // Enter EmployeeName<employeeName>
        viewSystemUsersPage.setEmployeeName(employeeName);
        // Select Status <status >
        viewSystemUsersPage.clickOnStatus();
        viewSystemUsersPage.setSelectstatusDropdown(status);
        // Click on "Search"
        viewSystemUsersPage.clickOnSearchButtonInSystemUsers();
        // verify message "(1) Record Found"
        String expectedRecord = "Record Found";
        String actualRecord = viewSystemUsersPage.getTextRecord();
        Assert.assertEquals(expectedRecord, actualRecord);
        // Verify username <username >
        String expectedUsername = "username";
        String actualUsername = viewSystemUsersPage.getTextUsername();
        Assert.assertEquals(expectedUsername, actualUsername);
        // Click on Reset Tab
        viewSystemUsersPage.setClickOnResetBtn();
    }
}
