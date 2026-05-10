import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetClinicUiTest {

    private WebDriver driver;

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
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void petClinicHomePageLoads() {
        driver.get("http://localhost:9966/petclinic/");
        String source = driver.getPageSource();
        assertTrue(source.contains("Swagger"), "Page source should contain Swagger but did not");
    }
}