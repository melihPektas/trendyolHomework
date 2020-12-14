package caseStudy.restApi;


import caseStudy.page.ApiPage;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;


public class baseHttpClientUtil extends baseHttpClient {
    public static JSONParser parser = new JSONParser();
    protected static final Logger log = Logger.getLogger(baseHttpClientUtil.class);
    private static String UrlProduct;
    private static String ProductType;
    private static String ProductModel;
    public static String Title;
    public static String Category;
    private static String StockCode;
    private static String id;
    public static String partName;
    private static java.lang.Object Object;
    private static String idJson;
    private static String titleJson;
    private static String firstName;
    private static String lastName;
    private static String idBook;


    public static void response(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() == 200 && response.getBody() != null) {
            reportRequestTime(response, message);
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }

    public static void responseCheck(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() == 200 && response.getBody() != null) {
            /*  reportRequestTime(response, message);*/
        } else {
            reportRequestTimeErrorP(response, errorMessage);
        }
    }

    public static void responseJsonData(BaseResponse response, String message, String errorMessage, JSONArray jsonArray) {
        if (response.getCode() == 200) {
            reportRequestTime(response, message);
            jsonArray = getJsonArray(response, jsonArray, 200);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject_0 = (JSONObject) jsonArray.get(i);
                    idJson = jsonObject_0.get("id").toString();
                    titleJson = jsonObject_0.get("title").toString();
                    ApiPage.testGetBookAuthors(idJson);
                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }


    public static JSONArray getJsonArray(BaseResponse response, JSONArray jsonArray, int i) throws ParseException {
        if (response.getCode() == i && response.getBody() != null) {
            try {
                jsonArray = (JSONArray) baseHttpClientUtil.parser.parse(response.getBody());
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        assert jsonArray != null;
        return jsonArray;
    }


    public static void responseJson(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() == 200) {
            reportRequestTime(response, message);
            JSONObject jsonObject = getJsonObject(response);
            if (jsonObject != null) {
                if (jsonObject.size() > 0) {
                    id = jsonObject.get("id").toString();
                    idBook = jsonObject.get("idBook").toString();
                    firstName = jsonObject.get("firstName").toString();
                    lastName = jsonObject.get("lastName").toString();
                    log.info(id + "----id-----" + idBook + "----idBook----" + firstName + "------Author First Name-------->" + lastName + "--------Author Last Name-------");
                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }

    public static void responseJsonZ(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() == 200) {
            reportRequestTime(response, message);
            JSONObject jsonObject = getJsonObject(response);
            if (jsonObject != null) {
                if (jsonObject.size() > 0) {
                    id = jsonObject.get("id").toString();
                    idBook = jsonObject.get("idBook").toString();
                    firstName = jsonObject.get("firstName").toString();
                    lastName = jsonObject.get("lastName").toString();
                    log.info(idBook + " " + firstName + "------Author First Name-------->" + lastName + "--------Author Last Name-------" + titleJson + "--------Book Title------>");
                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            //  Assert.fail(errorMessage + "is failed!");
        }
    }

    public static void reportRequestTime(BaseResponse response, String message) {
        log.info(message);
    }


    public static void reportRequestTimeError(BaseResponse response, String message) {
        log.error("ERROR" + "  " + message + "   Code  " + " " + response + " " + response.getBody());
    }

    public static void reportRequestTimeErrorP(BaseResponse response, String message) {
        log.error("ERROR" + "  " + message + "   Code  " + "   " + response.getCode());
    }


    private static JSONObject getJsonObject(BaseResponse response) throws ParseException {
        JSONObject json = null;
        json = getJsonObject(response, json, 200);
        assert json != null;
        return json;
    }

    private static JSONObject getJsonObject(BaseResponse response, JSONObject jsonObject, int i) throws ParseException {
        String responseBodyString = response.getBody();
        try {
            if (response.getCode() == i && responseBodyString != null) {
                jsonObject = (JSONObject) baseHttpClientUtil.parser.parse(responseBodyString);
            } else {
                log.error(response.getCode());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        assert jsonObject != null;
        return jsonObject;
    }


}




