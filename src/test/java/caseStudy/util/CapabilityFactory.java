package caseStudy.util;

import org.openqa.selenium.Capabilities;

public class CapabilityFactory {
    public Capabilities capabilities;

    public Capabilities getCapabilities (String browsers) {
        if (browsers.equals("firefox"))
            capabilities = OptionsManager.getFirefoxOptions();
        else
            capabilities = OptionsManager.getChromeOptions();
        return capabilities;
    }
}
