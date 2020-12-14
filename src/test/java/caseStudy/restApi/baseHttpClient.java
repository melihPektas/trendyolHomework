package caseStudy.restApi;

import caseStudy.constansts.ConstantsMainPage;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class baseHttpClient extends ConstantsMainPage {

    protected static final Logger log = Logger.getLogger(baseHttpClient.class);

    private static final OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(260, TimeUnit.SECONDS)
                .readTimeout(260, TimeUnit.SECONDS)
                .writeTimeout(260, TimeUnit.SECONDS)
                .build();
    }


    public static BaseResponse HttpClient(String URL) {
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .build();
        return getResponse(request, "is failed!");
    }

    public static BaseResponse HttpClientHeader(String URL,String value) {
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .addHeader("Region", value)
                .build();
        return getResponse(request, "is failed!");
    }

    public static BaseResponse sendPostRequest(String url, RequestBody body) {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("accept", "application/json, text/json, application/xml, text/xml")
                .addHeader("cache-control", "no-cache")
                .build();
        client.connectTimeoutMillis();
        return getResponse(request, "is failed!");
    }
    public static BaseResponse sendPutRequest(String url, RequestBody body) {
        Request request = new Request.Builder()
                .url(url)
                .method("PUT", body)
                .addHeader("accept", "application/json, text/json, application/xml, text/xml")
                .addHeader("cache-control", "no-cache")
                .addHeader("Content-Type", "application/json; v=1.0")
                .build();
        client.connectTimeoutMillis();
        return getResponse(request, "is failed!");
    }


    private static OkHttpClient getClient() {
        return client;
    }

    private static BaseResponse getResponse(Request request, String s) {
        return getBaseResponse(request, s, getClient(), log);
    }

    public static BaseResponse getBaseResponse(Request request, String s, OkHttpClient client, Logger logger) {
        Response response = null;
        BaseResponse baseResponse = null;
        try {
            response = client.newCall(request).execute();
            Headers resHeader = response.headers();
            if (response == null && !response.isSuccessful()) {
                System.out.println("response null");
            } else if (response.body() == null) {
                baseResponse = new BaseResponse(response.code(), response.sentRequestAtMillis(), response.receivedResponseAtMillis());
            } else {
                baseResponse = new BaseResponse(response.body().string(), response.code(), response.sentRequestAtMillis(), response.receivedResponseAtMillis(), resHeader);
            }
        } catch (IOException e) {
            logger.error(response);
            e.printStackTrace();
            Assert.fail(s, e);
        } finally {
            assert response != null;
            if (response.body() != null) {
                response.body().close();
            }
            response.close();
        }
        return baseResponse;
    }
}
