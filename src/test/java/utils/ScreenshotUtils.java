package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {
    public static void captureAndAttach(WebDriver driver, String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));

            Files.write(Paths.get("screenshot-" + System.currentTimeMillis() + ".png"), screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}