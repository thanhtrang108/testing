package constant;

import java.io.File;

public final class Constants {

    public static final String WEB_DRIVER_PATH = "driver/chromedriver-mac";
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String PATH_TO_EXCEL = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.xlsx";

    private Constants() {
    }
}
