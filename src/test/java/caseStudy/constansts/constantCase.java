package caseStudy.constansts;

import org.openqa.selenium.By;

public class constantCase {


    public static String userName = "melo.test42@gmail.com";
    public static String password = "A1234567890";

    public static final By popupCloseButton = By.cssSelector("body > div.fancybox-overlay.fancybox-overlay-fixed > div > div > a");
    public static final By accountButton = By.cssSelector("#accountBtn");
    public static final By inputEmail = By.cssSelector("#login-email");
    public static final By inputPassword = By.cssSelector("#login-password-input");
    public static final By loginSubmit =By.cssSelector("[type='submit']");
    public static final By footerContainer =By.cssSelector("#footer-container");
    public static final By getSmallCampaignList =By.cssSelector(".component-list.component-small-list");
    public static final By KADIN = By.cssSelector("li:nth-of-type(1) > .category-header");
    public static final By ERKEK = By.cssSelector("li:nth-of-type(2) > .category-header");
    public static final By ÇOCUK = By.cssSelector("li:nth-of-type(3) > .category-header");
    public static final By EVYAŞAM = By.cssSelector("li:nth-of-type(4) > .category-header");
    public static final By SÜPERMARKET = By.cssSelector("li:nth-of-type(5) > .category-header");
    public static final By KOZMETİK = By.cssSelector("li:nth-of-type(6) > .category-header");
    public static final By AYAKKABIÇANTA = By.cssSelector("li:nth-of-type(7) > .category-header");
    public static final By SAAT_AKSESUAR = By.cssSelector("li:nth-of-type(8) > .category-header");
    public static final By ELEKTRONİK = By.cssSelector("li:nth-of-type(9) > .category-header");
    public static final By imageContainer = By.cssSelector(".image-container > img");
    public static final By filter = By.cssSelector("#sticky > div");
    public static final By cardImageContainer = By.cssSelector(".p-card-img-wr > img");
    public static final By componentItem = By.cssSelector(".component-item > a");
    public static final By cardChildrenCenter = By.cssSelector(".p-card-chldrn-cntnr > a");
    public static final By boutiqueProduct = By.cssSelector(".boutique-product > a");
    public static final By description = By.cssSelector(".dscrptn");
    public static final By addToBasket = By.cssSelector(".add-to-bs.pr-in-btn");


}
