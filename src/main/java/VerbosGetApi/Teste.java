package VerbosGetApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Teste {

    public static void main(String[] args) {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

        //Imprimindo o retorno da api - validação retorno api
        System.out.println(response.getBody().asString());
        System.out.println(response.getBody().asString().equals("Ola Mundo!"));

        //Valida statusCode - validação statusCode Api
        System.out.println(response.statusCode());
        System.out.println(response.statusCode() == 200);

        //Metodo de validação utilizando ValidatableResponse
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }
}
