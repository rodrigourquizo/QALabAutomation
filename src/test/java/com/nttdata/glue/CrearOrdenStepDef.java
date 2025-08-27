package com.nttdata.glue;

import com.nttdata.steps.CrearOrden;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CrearOrdenStepDef {

    @Steps
    CrearOrden orden;

    @Given("defino la URL {string}")
    public void definoLaURL(String url) {
        orden.setURL_BASE(url);
    }

    @When("registro la orden con ID {string}, petId {string}, cantidad {string}, status {string}, complete {string}")
    public void registroLaOrden(String arg0, String arg1, String arg2, String arg3, String arg4) {
        orden.registrar(arg0, arg1, arg2, arg3, arg4);

    }

    @And("valido el status code del response igual a {int}")
    public void validoElCodigoDeRespuestaSea(int statusCodeEsperado) {
        orden.validarStatusCode(statusCodeEsperado);

    }

    @And("valido el body de la orden sea {string}, {string}, {string}, {string}, {string}")
    public void validoElBodyDeLaOrdenSea(String arg0, String arg1, String arg2, String arg3, String arg4) {
        orden.validarBody(arg0, arg1, arg2, arg3, arg4);
    }

    @When("consulto la orden de ID {string}")
    public void consultoLaOrdenDeID(String id) {
        orden.consultoOrdenporID(id);

    }


}
