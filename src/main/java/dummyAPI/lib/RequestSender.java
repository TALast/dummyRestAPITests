package dummyAPI.lib;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RequestSender {
    private String url;
    private StringEntity body;
    private ContentType contentType;
    private HttpResponse response;

    public RequestSender (String url) {
        this.url = url;
        contentType = ContentType.APPLICATION_FORM_URLENCODED;
    }

    public void setBody (String body) {
        this.body = new StringEntity(body, this.contentType);
    }

    public void makeHttpPostRequest() {
        HttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD).build()).build();

        HttpPost request = new HttpPost(url);
        request.setEntity(body);

        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeHttpGetRequest() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD).build()).build();

        HttpGet request = new HttpGet(url);
        response = httpClient.execute(request);
    }

    public void makeHttpPutRequest() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD).build()).build();

        HttpPut request = new HttpPut(url);
        response = httpClient.execute(request);
    }

    public String getResponseBody() throws IOException {
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        return responseString;
    }

    public int getStatus() {
        return response.getStatusLine().getStatusCode();
    }
}