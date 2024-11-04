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

        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("https://postman-echo.com/get");

        response.then().statusCode(200);

        response.then()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.3 (Java/17.0.12)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testPostRawText() {

        Response response = given()
                .header("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("https://postman-echo.com/post");

        response.then().statusCode(200);

        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.3 (Java/17.0.12)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testPutRequest() {

        Response response = given()
                .header("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put("https://postman-echo.com/put");

        response.then().statusCode(200);

        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.3 (Java/17.0.12)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testPatchRequest() {

        Response response = given()
                .header("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .patch("https://postman-echo.com/patch");

        response.then().statusCode(200);

        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.3 (Java/17.0.12)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testDeleteRequest() {

        Response response = given()
                .header("Content-Type","text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("https://postman-echo.com/delete");

        response.then().statusCode(200);

        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.3 (Java/17.0.12)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", containsString("gzip"));
    }
}
