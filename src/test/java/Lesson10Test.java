/*
Перейдите в сервис Postman. Войдите в свой аккаунт и скачайте себе коллекцию Postman Echo.

Написать автотесты для каждого метода из папки Request Methods (проверка тела ответа (просто сравнить значения
всех полей) и кода ответа).
*/

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class Lesson10Test {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeMethod
    public void setup() {

        RestAssured.baseURI = "https://postman-echo.com";

        requestSpec = RestAssured.given()
                .log().all()
                .header("Content-Type", "text/plain");

        responseSpec = RestAssured.expect()
                .statusCode(200)
                .body("headers.x-forwarded-proto", Matchers.equalTo("https"))
                .body("headers.x-forwarded-port", Matchers.equalTo("443"))
                .body("headers.host", Matchers.equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.user-agent", Matchers.equalTo("Apache-HttpClient/4.5.3 (Java/17.0.12)"))
                .body("headers.accept", Matchers.equalTo("*/*"))
                .body("headers.accept-encoding", containsString("gzip"));
    }

    @Test
    public void testGetRequest() {

        Response response = given()
                .spec(requestSpec)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get");

        response.then().spec(responseSpec);
        response.then()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .log().all();
    }

    @Test
    public void testPostRawText() {

        Response response = given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("/post");

        response.then().spec(responseSpec);
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .log().all();
    }

    @Test
    public void testPutRequest() {

        Response response = given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put("/put");

        response.then().spec(responseSpec);
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .log().all();
    }

    @Test
    public void testPatchRequest() {

        Response response = given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .patch("/patch");

        response.then().spec(responseSpec);
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .log().all();
    }

    @Test
    public void testDeleteRequest() {

        Response response = given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("/delete");

        response.then().spec(responseSpec);
        response.then()
                .body("args", is(emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", is(emptyMap()))
                .body("form", is(emptyMap()))
                .body("headers.content-length", equalTo("58"))
                .body("headers.content-type", containsString("text/plain"))
                .log().all();
    }
}
