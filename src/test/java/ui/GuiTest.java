package ui;

import config.Config;
import core.BaseGuiTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import pages.UserAccountPage;

public class GuiTest extends BaseGuiTest {



    @Test
    @Description("Tests that performs login with valid credentials and changes corresponding language to German")
    public void loginAndChangeLanguageTest(){

        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillOutEmailAddress(Config.username());
        loginPage.clickOnLoginWithPassword();
        loginPage.fillOutPassword(Config.password());

        mainPage = new MainPage(driver);
        mainPage.goToMyProfile();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.openCorrespondenceLanguageSetting();

        UserAccountPage userAccountPage = new UserAccountPage(driver);
        userAccountPage.selectLanguage("DE");

        String correspondingLanguage = accountPage.getCurrentCorrespondingLanguage();

        Assertions.assertEquals("German", correspondingLanguage, "Selected corresponding language.");
    }

}
