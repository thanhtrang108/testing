package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourCart {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'CHECKOUT')]")
    private WebElement checkOut;

    public YourCart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkOut() {
        checkOut.click();
    }

}
