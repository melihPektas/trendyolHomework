package caseStudy.test;


import caseStudy.base.BaseTest;
import caseStudy.page.study;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class studyTest extends BaseTest {

    @Parameters(value = {"browsers"})
    @Test(description = "Sisteme kayıtlı bir kullanıcı ile login olunmalı")
    public void caseStudy1() {
        new study(driver).case1();
    }

    @Parameters(value = {"browsers"})
    @Test(description = "login olduktan sonra, aşağıda numaralandırılan tab’lere tıklanıp yüklenmeyen butik imajı varsa hata fırlatmadan log basılmalı")
    public void caseStudy2() {
        new study(driver).case1().case2();
    }

    @Parameters(value = {"browsers"})
    @Test(description = "Sonrasında rastgele bir butiğe giderek, ürün görsellerinin yüklenip yüklenmediğinin kontrolü sağlanmalı (hata fırlatmayıp yine log basılabilir")
    public void caseStudy3() {
        new study(driver).case1().case3();
    }

    @Parameters(value = {"browsers"})
    @Test(description = "Herhangi bir ürünün detayına gidilmeli")
    public void caseStudy4() {
        new study(driver).case1().case4();
    }

    @Parameters(value = {"browsers"})
    @Test(description = "Ürünü sepete ekleyip test tamamlanmalı")
    public void caseStudy5() {
        new study(driver).case1().case4().case5();
    }


}
