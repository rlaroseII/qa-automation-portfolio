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
    private By lastNameInput = By.id("lastName");
    private By searchButton = By.cssSelector("button[type='submit']");

    public OwnerSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("http://localhost:8080/owners/find");
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("/owners");
    }

    public void searchByLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(searchButton).click();
    }

    public List<WebElement> getOwnerRows() {
        wait.until(ExpectedConditions.presenceOfElementLocated(ownerTableRows));
        return driver.findElements(ownerTableRows);
    }

    public String getFirstOwnerName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(ownerTableRows));
        return driver.findElement(By.cssSelector("table.table tbody tr td a")).getText();
    }
}