package caseStudy.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingActions {

    private static JavascriptExecutor jsDriver = null;
    private WebDriver waDriver;

    WaitingActions(WebDriver driver) {
        waDriver = driver;
    }

    void ajaxComplete() {
        jsDriver = (JavascriptExecutor) waDriver;
        jsDriver.executeScript("var callback = arguments[arguments.length - 1];" + "var xhr = new XMLHttpRequest();"
                + "xhr.open('GET', '/Ajax_call', true);" + "xhr.onreadystatechange = function() {"
                + "  if (xhr.readyState == 4) {" + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
       // System.out.println("ajax Complete" );
    }

    public void pageLoadComplete() {
        jsDriver = (JavascriptExecutor) waDriver;
        ExpectedCondition<Boolean> expectation = driver ->   ((JavascriptExecutor) waDriver).executeScript("return document.readyState", true).toString( ).equals("complete");
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(waDriver, 10);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(error.getMessage() );
        }
        //System.out.println("page Load Complete" );
    }

    void waitForAngularLoad() {
        long start = System.nanoTime( );
        jsDriver = (JavascriptExecutor) waDriver;
        ExpectedCondition<Boolean> expectation = driver ->   ((JavascriptExecutor) waDriver).executeScript(
                "return angular.element(document).injector().get('$http').pendingRequests.length === 0", true)
                .toString( ).equals("true");
            try {
                WebDriverWait wait = new WebDriverWait(waDriver, 600);
                wait.until(expectation);
            } catch (Throwable error) {
               System.out.println(error.getMessage( ));
            }
            long end = System.nanoTime( );
            System.out.println("wait For Angular Load Complete" );
            System.out.println("Angular wait " + (end - start) / 1000000000.0);
        }



    void jQueryComplete() {

        BasePageUtil bpu = new BasePageUtil(waDriver);
        jsDriver = (JavascriptExecutor) waDriver;
        int sleepIndex = 0;
        try {
            jsDriver.executeScript("window.jQuery");
            if (jsDriver.executeScript("return jQuery.active").toString( ).equals("0")) {
                System.out.println("Page Is loaded. ----> jQueryComplete");
                return;
            }

            for (int i = 0; i < 25; i++) {
                System.out.println("i " + i);
                if (jsDriver.executeScript("return jQuery.active").toString( ).equals("0")) {
                    System.out.println("for dongusune girdi ve bekliyor");
                    sleepIndex = i;
                    break;
                }
                bpu.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() );
        }
        System.out.println("jQuery Complete");
        System.out.println("jQuery wait "+sleepIndex+" sn.");
        System.out.println("jQueryComplete metodunun sonu");
    }

}

