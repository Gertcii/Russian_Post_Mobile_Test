package scenario;

import entities.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static AppiumDriver driver; // singleton
    BasePage page;

    public BasePage getPageByName(String pageName) throws Exception {
        return new BasePage(pageName, driver);
    }

    public void setUpDriver(String platformName,
                            String udid,
                            String bundleId,
                            String automationName,
                            String locale,
                            String language) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("bundleId", bundleId);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("locale", locale);
        capabilities.setCapability("language", language);
        try {
            driver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Parameters({"platformName", "udid", "bundleId", "automationName", "locale", "language"})
    @BeforeTest(alwaysRun = true)
    public void setUp(String platformName,
                      String udid,
                      String bundleId,
                      String automationName,
                      String locale,
                      String language) {
        setUpDriver(platformName, udid, bundleId, automationName, locale, language);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.closeApp();
    }

}
