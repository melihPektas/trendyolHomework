package caseStudy.page;

import caseStudy.restApi.BaseResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static caseStudy.restApi.baseHttpClient.*;

public class Api {


    public static BaseResponse postAuthors(int idBook, String authorFirstName, String authorLastName) {
        MediaType mediaType = MediaType.parse("application/json; v=1.0");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"idBook\": \"" + idBook + "\",\n    \"firstName\": \"" + authorFirstName + "\",\n    \"lastName\": \"melo\"\n}");
        return sendPostRequest("http://fakerestapi.azurewebsites.net/api/v1/Authors", body);
    }

    public static BaseResponse putAuthors(int idBook, String authorFirstName, String authorLastName, int Id) {
        MediaType mediaType = MediaType.parse("application/json; v=1.0");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"idBook\": \"" + idBook + "\",\n    \"firstName\": \"" + authorFirstName + "\",\n    \"lastName\": \"" + authorLastName + "\"\n}");
        return sendPutRequest("http://fakerestapi.azurewebsites.net/api/v1/Authors/" + Id, body);
    }

    public static BaseResponse getBooks() {
        return HttpClient("http://fakerestapi.azurewebsites.net/api/v1/Books");
    }

    public static BaseResponse getBookAuthors(String bookId) {
        return HttpClient("http://fakerestapi.azurewebsites.net/api/v1/Authors/" + bookId);
    }


}
