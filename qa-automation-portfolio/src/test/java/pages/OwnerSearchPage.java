package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class OwnerSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By ownerTableRows = By.cssSelector("table.table tbody tr");

    public OwnerSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("http://localhost:8080/owners?lastName=");
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("/owners");
    }

    public List<WebElement> getOwnerRows() {
        wait.until(ExpectedConditions.presenceOfElementLocated(ownerTableRows));
        return driver.findElements(ownerTableRows);
    }
}