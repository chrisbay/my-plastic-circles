package net.chrisbay.myplasticcirclesprovider.functional;

import net.chrisbay.myplasticcirclesprovider.functional.pages.AbstractPage;
import net.chrisbay.myplasticcirclesprovider.functional.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends AbstractUITest {

    private HomePage page;

    @BeforeMethod
    public void loadPage() {
        driver.get(AbstractPage.BASE_URL);
        this.page = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void verifyHomePageLoads() {
        assertEquals("Get Started", page.getGetStartedBtnText());
        page.clickGetStartedBtn();
        assertEquals(AbstractPage.BASE_URL + "/discs", driver.getCurrentUrl());
    }

}
