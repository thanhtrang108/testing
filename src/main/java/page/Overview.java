package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Overview {

    WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'FINISH')]")
    private WebElement finish;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> addedItem;

    public Overview(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String verifyItemCheckout(int itemNumber) {
        return addedItem.get(itemNumber).getText();
    }

    public void finish() {
        finish.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")));

    }

}
