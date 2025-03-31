package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.switchTo;
import static helpers.Wait.waitUntilClickable;

public abstract class BasePage {

    protected void clickElement(SelenideElement element) {
        waitUntilClickable(element);
        element.click();
    }

    protected String getAlertText() {
        return switchTo().alert().getText();
    }

    protected void acceptAlert() {
        switchTo().alert().accept();
    }

}

