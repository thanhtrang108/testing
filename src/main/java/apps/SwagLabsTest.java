package apps;

import constant.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;
import utilities.ReadFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwagLabsTest {

    private WebDriver driver;
    private String baseUrL;
    private WebDriverWait wait;
    private LoginPage login;
    private ProductPage productPage;
    private YourCart yourCart;
    private YourInformation yourInformation;
    private Overview overview;

    @Test(priority = 1, dataProvider = "loginData")
    public void testLogin(String username, String password) throws Exception {
        login.setUserName(username);
        login.setPassword(password);
        login.login();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws Exception {
        ReadFile.setExcelFile(Constants.PATH_TO_EXCEL, "Account");
        Object[][] testData = ReadFile.getTestData("data");
        return testData;
    }

    @Test(priority = 2)
    public void testPickItem() throws Exception {
        productPage.addItem(0);
        productPage.addItem(1);
        productPage.goToBag();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Your Cart')]")));
        yourCart.checkOut();
    }

    @Test(priority = 3, dataProvider = "checkoutData")
    public void testCheckout(String firstName, String lastName, String zipCode) throws Exception {
        yourInformation.setFirstName(firstName);
        yourInformation.setLastName(lastName);
        yourInformation.setZipCode(zipCode);
        yourInformation.continues();
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutDataProvider() throws Exception {
        ReadFile.setExcelFile(Constants.PATH_TO_EXCEL, "Checkout");
        Object[][] testData = ReadFile.getTestData("data");
        return testData;
    }

    @Test(priority = 4, dataProvider = "verifyItemCheckoutProvider")
    public void testOverview(String itemNumber, String item) throws Exception {
        Assert.assertEquals(overview.verifyItemCheckout(Integer.valueOf(itemNumber)), item);
    }

    @DataProvider(name = "verifyItemCheckoutProvider")
    public Object[][] verifyItemCheckoutProvider() throws Exception {
        ReadFile.setExcelFile(Constants.PATH_TO_EXCEL, "Items");
        Object[][] testData = ReadFile.getTestData("data");
        return testData;
    }


    @Test(priority = 5)
    public void checkoutSuccess() throws Exception{
        overview.finish();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")));
    }

    @BeforeClass
    public void beforeClass() {
        baseUrL = Constants.BASE_URL;
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        login = new LoginPage(driver);
        productPage = new ProductPage(driver);
        yourCart = new YourCart(driver);
        yourInformation = new YourInformation(driver);
        overview = new Overview(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrL);
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
    }

}
