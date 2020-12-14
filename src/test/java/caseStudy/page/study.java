package caseStudy.page;


import caseStudy.util.BasePageUtil;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static caseStudy.constansts.constantCase.*;


public class study extends BasePageUtil {
    public study(WebDriver driver) {
        super(driver);
    }

    @Description("Sisteme kayıtlı bir kullanıcı ile login olunmalı")
    public study case1() {
        assertTrue(isElementPresent(popupCloseButton), "popupCloseButton");
        clickOBJECT(popupCloseButton, "popupCloseButton");
        assertTrue(isElementPresent(accountButton), "accountButton");
        titleControl("trendyol");
        clickOBJECT(accountButton, "accountButton");
        titleControl("trendyol");
        assertTrue(isElementPresent(inputEmail), "inputEmail");
        sendKeysBy(inputEmail, userName);
        assertTrue(isElementPresent(inputPassword), "inputPassword");
        sendKeysBy(inputPassword, password);
        assertTrue(isElementPresent(loginSubmit), "loginSubmit");
        clickOBJECT(loginSubmit, "loginSubmit");
        return new study(driver);
    }

    @Description("Login olduktan sonra, aşağıda numaralandırılan tab’lere tıklanıp yüklenmeyen butik imajı varsa hata fırlatmadan log basılmalı")
    public study case2() {
        controlOfImgAndLink(KADIN, "kadin");
        controlOfImgAndLink(ERKEK, "erkek");
        controlOfImgAndLink(ÇOCUK, "cocuk");
        controlOfImgAndLink(EVYAŞAM, "ev");
        controlOfImgAndLink(SÜPERMARKET, "supermarket");
        controlOfImgAndLink(KOZMETİK, "kozmetik");
        controlOfImgAndLink(AYAKKABIÇANTA, "ayakkabi");
        controlOfImgAndLink(SAAT_AKSESUAR, "saat");
        controlOfImgAndLink(ELEKTRONİK, "elekronik");
        return new study(driver);
    }

    @Description("Sonrasında rastgele bir butiğe giderek, ürün görsellerinin yüklenip yüklenmediğinin kontrolü sağlanmalı (hata fırlatmayıp yine log basılabilir)")
    public study case3() {
        assertTrue(isElementPresent(ELEKTRONİK), getTextElement(ELEKTRONİK));
        clickOBJECT(ELEKTRONİK, getTextElement(ELEKTRONİK));
        titleControl("elektronik");
        randomClick(componentItem, 3);
        if (isPresent(filter)) {
            urlAssert();
            imageUrlControl(cardImageContainer);
            linkUrlController(cardChildrenCenter);
            log.info(getElementBy(description).getText().trim());
        } else {
            urlAssert();
            imageUrlControl(imageContainer);
            linkUrlController(boutiqueProduct);
        }

        return new study(driver);
    }

    @Description("Herhangi bir ürünün detayına gidilmeli")
    public study case4() {
        assertTrue(isElementPresent(ELEKTRONİK), getTextElement(ELEKTRONİK));
        clickOBJECT(ELEKTRONİK, getTextElement(ELEKTRONİK));
        log.info(driver.getTitle() + "  " + driver.getCurrentUrl());
        randomClick(componentItem, 3);
        urlAssert();
        if (isPresent(filter)) {
            imageUrlControl(cardImageContainer);
            linkUrlController(cardChildrenCenter);
            log.info(getElementBy(description).getText().trim());
            randomClick(cardChildrenCenter, 3);
        } else {
            imageUrlControl(imageContainer);
            linkUrlController(boutiqueProduct);
            randomClick(boutiqueProduct, 3);
        }
        return new study(driver);
    }

    public study case5() {
        urlAssert();
        assertTrue(isElementPresent(addToBasket), "addToBasket");
        clickOBJECT(addToBasket, "addToBasket");
        return new study(driver);
    }

    private void controlOfImgAndLink(By q, String text) {
        log.info(driver.getTitle() + "  " + driver.getCurrentUrl());
        titleControl("trendyol");
        assertTrue(isElementPresent(q), getTextElement(q));
        clickOBJECT(q, getTextElement(q));
        log.info(driver.getTitle() + "  " + driver.getCurrentUrl());
        assertTrue(isElementPresent(footerContainer), "footerContainer");
        infinityScroll(getSmallCampaignList);
        imageUrlControl(imageContainer);
        linkUrlController(componentItem);
    }


}
