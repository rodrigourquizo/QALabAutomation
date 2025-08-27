package com.nttdata.steps;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.Assert;

public class CrearOrden {

    private String URL_BASE;
    private Response response;
    private static long idgenerado;

    public void setURL_BASE(String url) {this.URL_BASE = url;}

    public void registrar(String orderId, String petId, String quantity, String status, String complete) {
        String body = "{\n" +
                "  \"id\": " + orderId + ",\n" +
                "  \"petId\": " + petId + ",\n" +
                "  \"quantity\": " + quantity + ",\n" +
                // "  \"shipDate\": \"2025-08-27T16:17:09.638Z\",\n" + //este valor de la fecha tiene un valor por defecto
                "  \"status\": \"" + status + "\",\n" +
                "  \"complete\": " + complete + "\n" +
                "}";

        response = RestAssured.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .body(body)
                .log().all()
                .when()
                .post("/store/order")
                .then()
                .log().all()
                .extract().response();
        idgenerado = response.jsonPath().getLong("id"); //almacenamos el id generado ya que el servicio crea uno diferente al que definimos en el body
    }

    public void validarStatusCode(int statusCodeEsperado) {
        Assert.assertEquals("Status Code", statusCodeEsperado, response.statusCode());
    }


    public void validarBody(String arg0, String arg1, String arg2, String arg3, String arg4) {
        //validamos el body del response
        Assert.assertEquals("Validar ID", Integer.parseInt(arg0), (int)response.jsonPath().getInt("id"));
        Assert.assertEquals("Validar petId", Integer.parseInt(arg1), (int)response.jsonPath().getInt("petId"));
        Assert.assertEquals("Validar quantity", Integer.parseInt(arg2), (int)response.jsonPath().getInt("quantity"));
        Assert.assertEquals("Validar status", arg3, response.jsonPath().getString("status"));
        Assert.assertEquals("Validar complete", Boolean.parseBoolean(arg4), response.jsonPath().getBoolean("complete"));
    }


    public void consultoOrdenporID(String id) {
        response = RestAssured
                .given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .baseUri(this.URL_BASE)
                .log().all()
                .when()
                .get("/store/order/" + id)
                .then()
                .log().all()
                .extract().response();
    }


}
