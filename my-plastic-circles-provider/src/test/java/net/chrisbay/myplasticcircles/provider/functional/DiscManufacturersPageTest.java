package net.chrisbay.myplasticcircles.provider.functional;

import net.chrisbay.myplasticcircles.provider.functional.pages.DiscManufacturersPage;
import net.chrisbay.myplasticcircles.provider.functional.pages.AbstractPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscManufacturersPageTest extends AbstractUITest {

    private DiscManufacturersPage page;

    @BeforeMethod
    public void loadPage() {
        driver.get(AbstractPage.BASE_URL + "/manufacturers");
        this.page = PageFactory.initElements(driver, DiscManufacturersPage.class);
    }

    @Test
    public void verifyDiscsPageContents() {
        assertEquals("Disc Manufacturers", this.page.getPageHeading());
        assertTrue(this.page.getRows().size() > 0);
    }

    @Test
    public void verifyAddManufacturerButton() {
        this.page.clickAddManufacturerBtn();
        assertEquals(AbstractPage.BASE_URL + "/manufacturers/new", driver.getCurrentUrl());
    }
}
