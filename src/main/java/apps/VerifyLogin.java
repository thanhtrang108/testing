package apps;

import constant.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LoginPage;
import utilities.ReadFile;

import java.util.concurrent.TimeUnit;

public class VerifyLogin {

    private WebDriver driver;
    private String baseUrL;
    private WebDriverWait wait;
    private LoginPage login;

    @Test(priority = 1, dataProvider = "loginPassData")
        public void testLoginPass(String username, String password) throws Exception {
        login.loginPass(username, password);
        driver.get(baseUrL);
    }

    @DataProvider(name = "loginPassData")
    public Object[][] loginPassProvider() throws Exception {
        ReadFile.setExcelFile(Constants.PATH_TO_EXCEL, "Account");
        Object[][] testData = ReadFile.getTestData("validAccount");
        return testData;
    }

    @Test(priority = 2, dataProvider = "loginFailData")
    public void testLoginFail(String username, String password) throws Exception {
        login.loginFail(username, password);
    }

    @DataProvider(name = "loginFailData")
    public Object[][] loginFailProvider() throws Exception {
        ReadFile.setExcelFile(Constants.PATH_TO_EXCEL, "Account");
        Object[][] testData = ReadFile.getTestData("unvalidAccount");
        return testData;
    }

    @BeforeClass
    public void beforeClass() {
        baseUrL = Constants.BASE_URL;
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        login = new LoginPage(driver, wait);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrL);
}

    @AfterClass
    public void afterTest() {
        driver.quit();
    }
}
