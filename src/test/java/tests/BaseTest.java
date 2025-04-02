package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleMainPage;
import pages.TicTacToeGamePage;

public class BaseTest {
    protected WebDriver driver;
    protected TicTacToeGamePage gamePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        GoogleMainPage googlePage = new GoogleMainPage(driver);
        googlePage.searchGame();

        gamePage = new TicTacToeGamePage(driver);
        gamePage.selectPlayWithFriend();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}