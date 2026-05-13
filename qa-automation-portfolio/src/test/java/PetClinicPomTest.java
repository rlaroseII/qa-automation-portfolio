import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.OwnerSearchPage;
import static org.junit.jupiter.api.Assertions.*;

public class PetClinicPomTest {

    private WebDriver driver;
    private HomePage homePage;
    private OwnerSearchPage ownerSearchPage;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "/home/dad/.cache/selenium/chromedriver/linux64/148.0.7778.97/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/opt/google/chrome/chrome");
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        ownerSearchPage = new OwnerSearchPage(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void homePageLoads() {
        homePage.open();
        assertTrue(homePage.isLoaded());
    }

    @Test
    public void ownerSearchPageLoads() {
        ownerSearchPage.open();
        assertTrue(ownerSearchPage.isLoaded());
    }

    @Test
    public void ownerListIsNotEmpty() {
        ownerSearchPage.open();
        ownerSearchPage.searchByLastName("");
        assertFalse(ownerSearchPage.getOwnerRows().isEmpty());
    }

    @Test
    public void searchByLastNameReturnsResults() {
        ownerSearchPage.open();
        ownerSearchPage.searchByLastName("Davis");
        assertFalse(ownerSearchPage.getOwnerRows().isEmpty());
    }

    @Test
    public void searchByLastNameShowsCorrectOwner() {
        ownerSearchPage.open();
        ownerSearchPage.searchByLastName("Davis");
        String ownerName = ownerSearchPage.getFirstOwnerName();
        assertTrue(ownerName.contains("Davis"));
    }
}