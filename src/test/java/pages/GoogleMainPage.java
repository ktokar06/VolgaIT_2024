package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToGame() {
        driver.get("https://g.co/kgs/NKFefHW");
    }
}