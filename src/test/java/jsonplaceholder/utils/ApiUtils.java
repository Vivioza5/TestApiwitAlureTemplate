package jsonplaceholder.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiUtils {
    private static Response response;
    private final static String URL = "https://jsonplaceholder.typicode.com/";

    public static void initRestAsssured() {
        RestAssured.baseURI = URL;
    }

    public static Response getRequest(String query, int number) {
        LogUtil.setRequestType("Get");
        response = when().get(query + "/" + number);
        return response;
    }

    public static Response getRequest(String path) {
        LogUtil.setRequestType("Get");
        response = when().get(path);
        return response;
    }

    public static Response postRequest(Object obj, String path) {
        LogUtil.setRequestType("Post");
        return given().body(obj).contentType("application/json").when().post(path);
    }
}





