package tests;

import com.codeborne.selenide.Configuration;
import helpers.PropertyProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest
{
    @BeforeClass
    void initBeforeClass()
    {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
    }

    @BeforeMethod
    public final void setup()
    {
        open(PropertyProvider.getBaseUrl());
    }

    @AfterClass
    public void tearDown() {
        closeWebDriver();
    }
}
