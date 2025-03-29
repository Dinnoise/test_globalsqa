package helpers;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {

    public static WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    private static WebDriverWait getWait() {
        int timeout = Integer.parseInt(PropertyProvider.getProperty("explicit.timeout"));
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    public static void waitUntilVisible(SelenideElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(SelenideElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static Alert waitForAlert() {
        getWait().until(ExpectedConditions.alertIsPresent());
        return getDriver().switchTo().alert();
    }
}
