package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'ADD TO CART')]")
    private List<WebElement> addItem;
    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement bag;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItem(int itemNumber) {
        addItem.get(itemNumber).click();
    }

    public void goToBag() throws InterruptedException {
        bag.click();
    }

}
