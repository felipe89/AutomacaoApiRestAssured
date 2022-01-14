package VerbosGetApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UsuarioJsonTest {

    @Test
    public void deveVerificarPrimeiroNivel(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/users/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.is(1))
                .body("name", Matchers.containsString("Silva"))
                .body("age", Matchers.greaterThan(18));
    }

    @Test
    public void deveVerificarPrimeiroNivelOutrasFormas(){
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");

        //Utilizando o path
        Assert.assertEquals(Integer.valueOf(1), response.path("id"));
        Assert.assertEquals(Integer.valueOf(1), response.path("%s","id"));

        //Utilizando o JsonPath
        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals(1, jsonPath.getInt("id"));

        //Utilizando o From
        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1, id);
    }
}
