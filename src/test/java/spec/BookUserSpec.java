package spec;

import demoqa.helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class BookUserSpec {

    public static RequestSpecification bookRequestSpec = with()
            .filters(CustomAllureListener.withCustomTemplates())
            .log().uri()
            .log().method()
            .contentType(JSON);

    public static ResponseSpecification bookAddResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification bookDeleteResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(204)
            .build();
}
