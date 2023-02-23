package net.chrisbay.myplasticcirclesprovider.ui;

import net.chrisbay.myplasticcirclesprovider.ui.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends AbstractUITest {

    private HomePage page;

    @BeforeMethod
    public void loadPage() {
        driver.get("http://localhost:8080");
        this.page = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void verifyHomePageLoads() {
        assertEquals("Get Started", page.getGetStartedBtnText());
        page.clickGetStartedBtn();
        assertEquals("http://localhost:8080/discs", driver.getCurrentUrl());
    }

}
