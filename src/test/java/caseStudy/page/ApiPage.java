package caseStudy.page;

import caseStudy.base.BaseTest;
import caseStudy.restApi.baseHttpClientUtil;
import org.json.simple.JSONArray;

import static caseStudy.page.Api.*;

public class ApiPage extends BaseTest {
    private static JSONArray jsonArray;


    public static void testGetBooks() {
        baseHttpClientUtil.responseJsonData(getBooks(), "Test Get Books", "Test Get Books", jsonArray);
    }


    public static void testGetBookAuthors(String bookId) {
        baseHttpClientUtil.responseJsonZ(getBookAuthors(bookId), "Test getBookAuthors", "Test getBookAuthors");
    }


    public static void testPostAuthors(int idBook,String authorFirstName, String authorLastName) {
        baseHttpClientUtil.responseJson(postAuthors(idBook,authorFirstName,authorLastName), "Test Post Authors", "Test Post Authors");
    }

    public static void testPutAuthors(int idBook,String authorFirstName, String authorLastName,int Id) {
        baseHttpClientUtil.responseJson(putAuthors(idBook,authorFirstName,authorLastName,Id), "Test put Authors", "Test Put Authors");
    }




}
