package caseStudy.util;

import caseStudy.base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Browser {
    private static String url;
    private static caseStudy.util.Configuration config;
    private final Logger logger = Logger.getLogger(Browser.class);
    private DesiredCapabilities capabilities;


    public Browser() {
        config = caseStudy.util.Configuration.getInstance();
    }

    public void createLocalDriver(String browsers ) {
        if (browsers.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            BaseTest.setDriver(new ChromeDriver(OptionsManager.getChromeOptions()));

        }else {
            WebDriverManager.firefoxdriver().setup();
            BaseTest.setDriver(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
        }


       /* capabilities = new DesiredCapabilities().chrome();
        capabilities.setPlatform(Platform.getCurrent());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-sandbox");
        options.addArguments("headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-proxy-server");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        capable(options);
        BaseTest.setDriver(new ChromeDriver(options));*/
    }

    private void capable(ChromeOptions options) {
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability("chrome.switches", Collections.singletonList("--incognito"));
    }


}
