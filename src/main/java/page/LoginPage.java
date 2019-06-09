package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    @FindBy(id = "user-name")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(className = "btn_action")
    private WebElement login;
    @FindBy(xpath ="//button[contains(text(),'Open Menu')]")
    private WebElement menu;
    @FindBy(xpath ="//a[contains(text(),'Logout')]")
    private WebElement logout;
    private WebDriverWait wait;
    @FindBy(xpath ="//div[@class='bm-menu']")
    private WebElement menuBar;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    private void login(String username, String password) {
        this.username.clear();
        this.username.sendKeys(username);

        this.password.clear();
        this.password.sendKeys(password);

        this.login.click();
    }

    public void loginPass (String username, String password){
        login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product_label']")));
    }

    public void loginFail (String username, String password){
        login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='error-button']")));
    }

   public void logout() {
        this.menu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logout')]")));
        Actions builder = new Actions(driver);
        builder.moveToElement(menuBar).perform();
        this.logout.click();
    }

}
