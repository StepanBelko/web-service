package by.itstep.stpnbelko.webservice.rest.ws;


import by.itstep.stpnbelko.webservice.model.Car;
import by.itstep.stpnbelko.webservice.util.XMLCurrencyParser;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/exchange-rates")
public class CurrencyService {

    @GET
    @Path("/currency/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrencyByCode(@PathParam("code") int code) {
        String value = XMLCurrencyParser.getCurrency(code + "");
         double d = Double.parseDouble(value);
         d = d + d * 0.05;
        return Response.ok(d).build();
    }


    @GET
    @Path("/currency")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrencyByCode() {
        return Response.ok(XMLCurrencyParser.getAllCurrenciesList()).build();
    }
}
