package caseStudy.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    protected static final Logger log = Logger.getLogger(Configuration.class);
    private static final String DEFAULT_ENVIRONMENT = "PROD";
    private static Configuration instance;
    private Properties configProps = new Properties( );
    private int implicitlyWait;
    private String browserName;
    private String browserVersion;
    private String excelName;
    private String baseUrl;
    private String msisdn;
    private String msisdnPass;

    // Report
    private String regressionId;
    private String imageResultPath;
    private String projectName;
    private String testPackage;


    private Configuration() {
        String env = System.getProperty("env");
        InputStream is = null;
        try {
            env = StringUtils.isNotBlank(env) ? env : DEFAULT_ENVIRONMENT;
            //String propertyPath=System.getProperty("propertyPath");
            configProps = getProperty("/properties/config/config_prod.properties");

            //Report
            this.regressionId = System.getProperty("regressionId");
            this.imageResultPath = configProps.getProperty("imageResultPath");
            this.browserName = StringUtils.isNotBlank(System.getProperty("browser")) ? System.getProperty("browser") : configProps.getProperty("browser.name");
            this.browserVersion = configProps.getProperty("browser.version");
            this.baseUrl = configProps.getProperty("base.url");
            this.msisdn = configProps.getProperty("msisdn");
            this.msisdnPass = configProps.getProperty("msisdnPass");
            this.projectName = configProps.getProperty("projectName");
            this.testPackage = configProps.getProperty("testPackage");
            log.info("setConfiguration");
        } catch (Exception e) {
            log.error(e);
        } finally {

            if (is != null) {
                try {
                    is.close( );
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
    }

    public static Properties getProperty(String path) {
        Properties properties = new Properties( );
        FileReader filereader = null;
        try {
            filereader = new FileReader(path);
            properties.load(filereader);
        } catch (IOException e) {
            System.out.println("Property read fail!");
        }
        return properties;
    }

    public static Configuration getInstance() {

        if (instance == null) {
            createInstance( );
            log.info("createInstance");
        }
        return instance;
    }

    private static synchronized void createInstance() {

        if (instance == null) {
            instance = new Configuration( );
        }
    }


    public String getServerUrl() {
        return baseUrl;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMsisdnPass() {
        return msisdnPass;
    }

    public int getImplicitlyWait() {
        return implicitlyWait;
    }


    public String getBrowserName() {
        return browserName;
    }


    public String getBrowserVersion() {
        return browserVersion;
    }


    public String getExcelName() {
        return excelName;
    }

    // public String[][] getUserNamePasswordDatas()
    // return stringTo2DArray(userName)

    public String getRegressionId() {
        return regressionId;
    }

    public void setRegressionId(String regressionId) {
        this.regressionId = regressionId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestPackage() {
        return testPackage;
    }

    public void setTestPackage(String testPackage) {
        this.testPackage = testPackage;
    }

    public String getImageResultPath() {
        return imageResultPath;
    }

    public void setImageResultPath(String imageResultPath) {
        this.imageResultPath = imageResultPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getResultPath() {
        return imageResultPath + File.separator + regressionId + File.separator + browserName + File.separator + projectName;
    }

}
