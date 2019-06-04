package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Overview {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'FINISH')]")
    private WebElement finish;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> addedItem;

    public Overview(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyItemCheckout(int itemNumber) {
        return addedItem.get(itemNumber).getText();
    }

    public void finish() {
        finish.click();
    }

}
