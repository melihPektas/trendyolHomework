package caseStudy.base;

import caseStudy.util.Browser;
import com.google.j2objc.annotations.Property;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.qameta.allure.FileSystemResultsWriter;
import io.qameta.allure.util.PropertiesUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Properties;

import static org.apache.log4j.PropertyConfigurator.configure;
import static org.testng.FileAssert.fail;

public class BaseTest {
    private static final Logger logTest = Logger.getLogger(BaseTest.class);
    public final String BASE_URL = System.getProperty("http://www.trendyol.com", "http://www.trendyol.com");
    protected static WebDriver driver;
    public static ExtentTest test;
    private static Browser browser = new Browser();
    private static caseStudy.util.Configuration config = caseStudy.util.Configuration.getInstance();
    private static String testTrainingBasicName;
    private StringBuffer verificationErrors = new StringBuffer();
    protected static final File DEFAULT_RESULTS_DIRECTORY = new File("target/allure-results");

    private StopWatch pageLoad = new StopWatch();
    StopWatch stopwatch = new StopWatch();

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }

    private static long duration;

    private static FileSystemResultsWriter getDefaultWriter() {
        final Properties properties = PropertiesUtils.loadAllureProperties();
        final String path = properties.getProperty("allure.results.directory", "allure-results");
        return new FileSystemResultsWriter(Paths.get(path));
    }

    @Property("allure.results.directory")
    protected File resultsDirectory = DEFAULT_RESULTS_DIRECTORY;
 

    @BeforeMethod
    @Parameters(value = {"browsers"})
    public void setUp(Method method,String browsers) {
        try {
            stopwatch.reset();
            stopwatch.start();
            testTrainingBasicName = System.getProperty("testTrainingName", "");
            configure("properties/log4j.properties");
            browser.createLocalDriver(browsers);
            BaseTest.getDriver().navigate().to(BASE_URL);
            duration = System.currentTimeMillis();
        }catch (Exception e){
            logTest.info(e.getMessage());
            driver.quit();
        }

    }

    @AfterMethod
    public void tearDown() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        driver.quit();
        stopwatch.stop();
        logTest.info(stopwatch.toString());
    }

}
