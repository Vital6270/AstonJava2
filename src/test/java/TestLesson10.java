/*
Перейдите в сервис Postman. Войдите в свой аккаунт и скачайте себе коллекцию Postman Echo.

Написать автотесты для каждого метода из папки Request Methods (проверка тела ответа (просто сравнить значения
всех полей) и кода ответа).
*/

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class TestLesson10 {

    @Test
    public void testGetRequest() {
        // Параметры для запроса
        String foo1 = "bar1";
        String foo2 = "bar2";

        // Выполнение GET запроса
        Response response = given()
                .queryParam("foo1", foo1)
                .queryParam("foo2", foo2)
                .when()
                .get("https://postman-echo.com/get")
                .then()
                .extract()
                .response();

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка тела ответа
        response.then()
                .body("args.foo1", equalTo(foo1))
                .body("args.foo2", equalTo(foo2))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.user-agent", containsString("PostmanRuntime"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.postman-token", startsWith("85aa3631"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testPostRawText() {
        // Данные для отправки в теле запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполнение POST запроса
        Response response = given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .extract()
                .response();

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка тела ответа
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo(requestBody))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", containsString("PostmanRuntime"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.postman-token", startsWith("4a108912"))
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.cookie", containsString("sails.sid"));
    }

    @Test
    public void testPostFormData() {
        // Данные формы для отправки в теле запроса
        String formData1 = "bar1";
        String formData2 = "bar2";

        // Выполнение POST-запроса
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("foo1", formData1)
                .formParam("foo2", formData2)
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .extract()
                .response();

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка тела ответа
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo(""))
                .body("files", is(emptyMap()))
                .body("form.foo1", equalTo(formData1))
                .body("form.foo2", equalTo(formData2))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("19"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.user-agent", containsString("PostmanRuntime"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.postman-token", startsWith("eea94633"))
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.cookie", containsString("sails.sid"));
    }

    @Test
    public void testPutRequest() {
        // Данные для отправки в теле запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполнение PUT запроса
        Response response = given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .put("https://postman-echo.com/put")
                .then()
                .extract()
                .response();

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка тела ответа
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo(requestBody))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", containsString("PostmanRuntime"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.postman-token", startsWith("5f6f932b"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testPatchRequest() {
        // Данные для отправки в теле запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполнение PATCH запроса
        Response response = given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .patch("https://postman-echo.com/patch")
                .then()
                .extract()
                .response();

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка тела ответа
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo(requestBody))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", containsString("PostmanRuntime"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.postman-token", startsWith("e0327d6d"))
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.cookie", containsString("sails.sid"));
    }

    @Test
    public void testDeleteRequest() {
        // Данные для отправки в теле запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполнение DELETE запроса
        Response response = given()
                .header("Content-Type","text/plain")
                .body(requestBody)
                .when()
                .delete("https://postman-echo.com/delete")
                .then()
                .extract()
                .response();

        // Проверка кода ответа
        response.then().statusCode(200);

        // Проверка тела ответа
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo(requestBody))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", containsString("PostmanRuntime"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", equalTo("no-cache"))
                .body("headers.postman-token", startsWith("ff833eb3"))
                .body("headers.accept-encoding", containsString("gzip"))
                .body("headers.cookie", containsString("sails.sid"));
    }
}
