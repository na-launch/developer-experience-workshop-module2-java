package com.redhat;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class TranslationResourceTest {

    @Test
    @DisplayName("GET / should always return EN translation in starter")
    void shouldReturnEnglishTranslation() {
        given()
          .when().get("/helloworld-by-language")
          .then()
             .statusCode(200)
             .body(containsString("hello world"));
    }
}