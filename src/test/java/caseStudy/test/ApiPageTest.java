package caseStudy.test;

import caseStudy.base.BaseTest;
import caseStudy.page.ApiPage;
import org.testng.annotations.Test;

public class ApiPageTest extends BaseTest {


    @Test(description = "db olmayan bir kitap sorgulama yapıldığında 404 alır")
    public void testGetBookAuthors() {
        ApiPage.testGetBookAuthors("9999");
    }

    /**
     * testPostAuthors
     *
     * This method is  created new record
     *
     * This method is call just url
     */



    @Test(description = "yazar bilgisini eklenir kitap id si yazar ismi ve soy isimi ile")
    public void testPostAuthors() {
        ApiPage.testPostAuthors(2, "melo", "melo");
    }





    @Test(description = "yazar bilgisini değiştirilmesi için kitap id si verilir")
    public void testPutAuthors() {
        ApiPage.testPutAuthors(2, "melo", "melo", 3);
    }


    @Test(description = "kitapları listeler")
    public void testGetBooks() {
        ApiPage.testGetBooks();
    }
}
