package VerbosGetApi;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TesteJunit {

    @Test
    public void testeOlaMundo(){
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        //Validação de retornos api "nome" e "statusCode" - imprimindo o retorno
        System.out.println(response.getBody().asString().equals("Ola Mundo!"));
        System.out.println(response.statusCode() == 200);

        //Validação statusCode via ValidateTableResponse
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

        //Outro modo de validação utilizando o AssertTrue
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue("Status code da api deve ser 200", response.statusCode() == 200);

        //Validação utilizando o AssertEquals
        Assert.assertEquals( response.statusCode(), 200);
        //Validação de retorno com melhor compreensão do desejado mesmo dando erro!
        Assert.assertEquals(201, response.statusCode());
    }
    @Test
    public void devoConhecerOutrasFormasRestAssured(){
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

        //Formar de validação com rest utilizando o then na chamada da api de forma encadeada
        get("https://restapi.wcaquino.me/ola").then().statusCode(200);

        //Forma de validação utilizando "Given" "When" e "Then" - (DADO/QUANDO/ENTÃO)
        given() //Pré-Condição
        .when() //Ação
           .get("https://restapi.wcaquino.me/ola")
        .then() //Assertivas ou Validações
            .statusCode(200);
    }

    @Test
    public void devoConhecerMatchersHamCrest(){
        List<Integer> impares = Arrays.asList(1,2,3,4,5);

        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1,2,3,4,5));
        assertThat(impares, containsInAnyOrder(1,2,5,4,3));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(1, 4));

        assertThat("maria", is(not("joao")));
        assertThat("maria", not("joao"));
        assertThat("maria", anyOf(is("joao"), is("felipe")));
        assertThat("maria", allOf(startsWith("m"), endsWith("i"), containsString("a")));
    }

    @Test
    public void devoValidarBody(){
        given()
                .when().get("https://restapi.wcaquino.me/ola")
                .then()
                .statusCode(200)
                .body(is("Ola Mundo!")) //Validando o retorno do body da api
                .body(containsString("Mundo")) //Validando se contém uma determinada informação no body
                .body(is(not(nullValue()))); //Verificar se o body não retorna vazio
    }


}
