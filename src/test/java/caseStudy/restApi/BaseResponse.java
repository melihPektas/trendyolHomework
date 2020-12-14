package caseStudy.restApi;

import okhttp3.Headers;

public class BaseResponse {
    private String body;

    private int code;

    private long requestAtMillis;

    private long responseAtMillis;

    private String networkResponse;

    private Headers header;


    public BaseResponse(int code, long requestAtMillis, long responseAtMillis) {
        this.code = code;
        this.requestAtMillis = requestAtMillis;
        this.responseAtMillis = responseAtMillis;
    }

    public BaseResponse(String body, int code, long requestAtMillis, long responseAtMillis ) {
        this.body = body;
        this.code = code;
        this.requestAtMillis = requestAtMillis;
        this.responseAtMillis = responseAtMillis;
    }

    public BaseResponse(String body, int code, long requestAtMillis, long responseAtMillis,Headers header ) {
        this.body = body;
        this.code = code;
        this.requestAtMillis = requestAtMillis;
        this.responseAtMillis = responseAtMillis;
        this.header=header;
    }

    public Headers getHeader() {return header;}
    public void  setHeader(Headers header) {this.header=header;}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getRequestAtMillis() {
        return requestAtMillis;
    }

    public void setRequestAtMillis(long requestAtMillis) {
        this.requestAtMillis = requestAtMillis;
    }

    public long getResponseAtMillis() {
        return responseAtMillis;
    }

    public void setResponseAtMillis(long responseAtMillis) {
        this.responseAtMillis = responseAtMillis;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "body='" + body + '\'' +
                ", code=" + code +
                ", requestAtMillis=" + requestAtMillis +
                ", responseAtMillis=" + responseAtMillis +
                ", networkResponse='" + networkResponse + '\'' +
                '}';
    }
}
