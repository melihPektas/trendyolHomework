package caseStudy.util;


import caseStudy.restApi.baseHttpClient;
import caseStudy.restApi.baseHttpClientUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class BasePageUtil {
    private static int index = 0;

    protected static final Logger log = Logger.getLogger(BasePageUtil.class);
    protected WebDriver driver;
    protected caseStudy.util.WaitingActions waitingAction;
    private JavascriptExecutor jsExec;
    private String i;
    private String a;
    private static String element1;


    public BasePageUtil(WebDriver driver) {
        this.driver = driver;
        waitingAction = new caseStudy.util.WaitingActions(driver);
        jsExec = (JavascriptExecutor) driver;
    }


    protected void waitAllRequests() {
        try {
            waitingAction.pageLoadComplete();
            //waitingAction.jQueryComplete( );
            // waitingAction.waitForAngularLoad( );
            waitingAction.ajaxComplete();
        } catch (Exception e) {
            takesScreenshot();
            log.error(e.getMessage());
        }
    }

    protected void assertTrue(boolean condition, String message) {
        try {
            takesScreenshot();
            if (condition) {
                Assert.assertTrue(condition, message + "    is  found");
                log.info("is element present" + " " + message);
            } else {
                Assert.assertTrue(condition, message + "    is  found");
                log.error(condition + message + "    is not found" + getCurrentUrl());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    protected WebElement sendKeysBy(By by, String value) {
        WebElement element = getElementBy(by);
        if (element == null) {
            log.error(element + "Element is null.  do not send");
        } else {
            scrollToElement(element);
            element.clear();
            element.sendKeys(value);
            takesScreenshot();
        }
        return element;
    }


    public String getTextElement(By by) {
        return getElementBy(by).getText().trim();
    }


    protected void clickOBJECT(By by, String message) {
        try {
            takesScreenshot();
            if (isElementPresentControl(by)) {
                WebElement element = getElementBy(by);
                scrollElementIntoMiddles(by);
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", element);
                element.click();
                log.info(message + "  " + "is clicked");
            } else {
                log.error(message + "    is not click");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    protected void titleControl(String Text) {
        String URL = driver.getCurrentUrl().trim();
        if (!URL.contains(Text)) {
            Assert.fail("Sıkıntılı olabilirrrr");
        }
    }


    protected void await(By by) {
        try {
            Document document = Jsoup.parse(driver.getPageSource());
            Elements elements = document.select(by.toString().split(":")[1].trim());
            if (elements == null) {
                log.error(elements + "  " + " is null ");
            }
        } catch (Exception e) {
            log.error("AWAIT" + e.getMessage());
        }
    }


    protected WebElement getElementBy(By by) {
        controlOf404();
        WebElement element = null;
        takesScreenshot();
        await(by);
        try {
            element = driver.findElement(by);
            if (element == null) {
                log.error(element + "  " + by);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return element;
    }


    public void controlOf404() {
        takesScreenshot();
        if (getCurrentUrl().contains("?notfound=true")) {
            log.error(getCurrentUrl());
            Assert.fail("404");
        } else if (driver.getPageSource().contains("Üzgünüz. Aradığınız sayfa bulunamadı.")) {
            log.error(getCurrentUrl());
            Assert.fail("404");
        }
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl().trim();
    }


    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public void randomClick(By by, int i) {
        waitAllRequests();
        await(by);
        List<WebElement> elements2 = driver.findElements(by);
        if (elements2 != null) {
            waitAllRequests();
            int a = getRandomNumberUsingInts(1, i);
            WebElement element = elements2.get(a);
            element1 = elements2.get(a).getAttribute("href");
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
            element.click();
            log.info("is clicked" + "   " + a + " \n" + element1);
        } else {
            Assert.fail("Elemets is null");
        }
    }

    public void urlAssert() {
        Assert.assertEquals(driver.getCurrentUrl().trim(), element1);
    }

    public void imageUrlControl(By by) {
        waitAllRequests();
        await(by);
        List<WebElement> elements = driver.findElements(by);
        if (elements != null) {
            log.info("waiting");
            for (WebElement element : elements) {
                String URL = element.getAttribute("currentSrc");
                String NAME = element.getAttribute("alt");
                if (URL.length() > 0) {
                    baseHttpClientUtil.responseCheck(baseHttpClient.HttpClient(URL), "Test URL is image success " + " \n" + NAME, "Test URL " + " \n" + URL + "  " + NAME);
                } else {
                    log.error(URL + "    ");
                }
            }
            log.info("Test URL is image OK " + "-->" + elements.size());
        } else {
            Assert.fail("Elemets is null");
        }
    }

    public void linkUrlController(By by) {
        waitAllRequests();
        await(by);
        List<WebElement> elements = driver.findElements(by);
        if (elements != null) {
            log.info("waiting");
            for (WebElement element : elements) {
                //  scrollElementIntoMiddles(by);
                String URL = element.getAttribute("href");
                String INNER_TEXT = element.getAttribute("innerText");
                if (URL.length() > 0) {
                    baseHttpClientUtil.responseCheck(baseHttpClient.HttpClient(URL), "Test URL is link success" + " \n" + URL, "Test URL 2" + " \n" + URL + "  " + INNER_TEXT);
                } else {
                    log.error(URL + "    ");
                }
            }
            log.info("Test URL is link OK " + "-->" + elements.size());
        } else {
            Assert.fail("Elemets is null");
        }
    }


    protected void sleep(long time) {
        for (int i = 10; i < 13; i++) {
            try {
                long start = System.currentTimeMillis();
                Thread.sleep(time);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }


    private void scrollElementIntoMiddles(By by) {
        WebElement element = getElementBy(by);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }


    protected boolean isElementPresent(By by) {
        takesScreenshot();
        waitAllRequests();
        if (getElementBy(by) == null) {
            for (int i = 0; i < 50; i++) {
                waitAllRequests();
                log.info(i);
                if (getElementBy(by) != null) {
                    waitAllRequests();
                    getElementBy(by);
                    highlightElement2(by);
                    return true;
                }
            }
        } else {
            //      highlightElement2(by);
            return true;
        }
        return false;
    }

    protected boolean isPresent(By by) {
        takesScreenshot();
        waitAllRequests();
        if (getElementBy(by) != null) {
            return true;
        } else {
            return false;
        }

    }

    public void infinityScroll(By by) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        while (true) {
            await(by);
            List<WebElement> element = driver.findElements(by);
            for (WebElement element1 : element) {
                i = element1.getAttribute("childElementCount");
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            List<WebElement> elements = driver.findElements(by);
            for (WebElement element1 : elements) {
                a = element1.getAttribute("childElementCount");
            }
            log.info(i + " is it equal  " + a);
            if (i.equals(a)) {
                break;
            }
        }


    }


    protected boolean isElementPresentControl(By by) {
        takesScreenshot();
        waitAllRequests();
        if (getElementBy(by) == null) {
            for (int i = 0; i <= 10; i++) {
                if (getElementBy(by) != null) {
                    getElementBy(by);
                    return true;
                }
                log.info(i);
            }
            return false;
        } else {
            getElementBy(by);
            return true;
        }
    }


    private WebElement highlightElement2(By locator) {
        WebElement elem = driver.findElement(locator);
        takesScreenshot();
        waitAllRequests();
        try {
            if (elem != null) {
                scrollElementIntoMiddles(locator);
                if (driver instanceof JavascriptExecutor) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", elem);
                } else {
                    log.error("error highlightElement2 ");
                }
            } else {
                log.error("element null");
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
        }
        return elem;
    }


    protected void scrollToElement(WebElement element) {
        try {
            if (element != null) {
                waitAllRequests();
                takesScreenshot();
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                sleep(200);
                //  log.info(element);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    protected void takesScreenshot() {
        try {
            if (isCalledFromTestMethod()) {
                TestInfo testInfo = getTestScenarioName();
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, getPath(testInfo));
            }
        } catch (Exception ex) {
            log.error("takesScreenshot  Error", ex);
        }
    }

    private File getPath(TestInfo testInfo) throws IOException {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        // String browserName = cap.getBrowserName().toLowerCase();

        StringBuffer sb = new StringBuffer();
        sb.append(testInfo.getResultPath()).append(File.separator);
        sb.append("testScreenShots").append(File.separator);
        sb.append(testInfo.getScenarioName()).append(File.separator);
        sb.append(testInfo.getCaseName()).append(File.separator);

        File directory = new File(sb.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info(String.format("Directory [%s] created:", sb.toString()));
        } else if (getIndex() == 0) {
            FileUtils.cleanDirectory(directory);
        }
        sb.append(userIndex()).append("_").append(UUID.randomUUID().toString()).append(".jpg");
        log.info(String.format("New File is:[%s]", sb.toString()));
        return new File(sb.toString());

    }

    private TestInfo getTestScenarioName() {
        caseStudy.util.Configuration conf = caseStudy.util.Configuration.getInstance();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        TestInfo testInfo = new TestInfo();

        for (int i = stackTraceElements.length - 1; i > 0; i--) {

            if (stackTraceElements[i].getClassName().startsWith(conf.getTestPackage() + ".")) {

                testInfo.setScenarioName(stackTraceElements[i].getClassName().replace(conf.getTestPackage() + ".", ""));

                testInfo.setCaseName(stackTraceElements[i].getMethodName());
                break;

            }

        }
        testInfo.setProjectName(conf.getProjectName());
        testInfo.setRegressionId(caseStudy.util.Configuration.getInstance().getRegressionId());
        testInfo.setImageResultPath(caseStudy.util.Configuration.getInstance().getImageResultPath());
        testInfo.setResultPath(caseStudy.util.Configuration.getInstance().getResultPath());
        return testInfo;
    }

    private boolean isCalledFromTestMethod() throws Exception {
        caseStudy.util.Configuration conf = caseStudy.util.Configuration.getInstance();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        boolean result = false;
        for (int i = stackTraceElements.length - 1; i > 0; i--) {
            Class<?> aClass = Class.forName(stackTraceElements[i].getClassName());
            if (aClass.getPackage().getName().equals(conf.getTestPackage())) {
                if (isTestMethod(stackTraceElements[i])) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    private boolean isTestMethod(StackTraceElement stackTraceElement) throws ClassNotFoundException {
        caseStudy.util.Configuration conf = caseStudy.util.Configuration.getInstance();
        Class<?> aClass = Class.forName(stackTraceElement.getClassName());
        log.info(aClass);
        Method[] methods = aClass.getMethods();

        for (Method method : methods) {
            if (method.getDeclaringClass().getPackage().getName().equals(conf.getTestPackage())
                    && method.getName().equals(stackTraceElement.getMethodName())) {
                for (Annotation methodAnnotation : method.getDeclaredAnnotations()) {
                    if (methodAnnotation.annotationType().getName().equals(Test.class.getCanonicalName())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int getIndex() {
        return index;
    }

    private int userIndex() {
        return index++;
    }

    private static class TestInfo {
        private String scenarioName;
        private String caseName;
        private String projectName;
        private String regressionId;
        private String imageResultPath;
        private String resultPath;

        String getResultPath() {
            return resultPath;
        }

        void setResultPath(String resultPath) {
            this.resultPath = resultPath;
        }

        String getScenarioName() {
            return scenarioName;
        }

        void setScenarioName(String scenarioName) {
            this.scenarioName = scenarioName;
        }

        String getCaseName() {
            return caseName;
        }

        void setCaseName(String caseName) {
            this.caseName = caseName;
        }


        void setProjectName(String projectName) {
            this.projectName = projectName;
        }


        void setRegressionId(String regressionId) {
            this.regressionId = regressionId;
        }


        void setImageResultPath(String imageResultPath) {
            this.imageResultPath = imageResultPath;
        }
    }


}

