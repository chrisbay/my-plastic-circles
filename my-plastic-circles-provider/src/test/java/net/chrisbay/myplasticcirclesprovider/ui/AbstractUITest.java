package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public abstract class AbstractUITest {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(AbstractPage.BASE_URL);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
    }

}
