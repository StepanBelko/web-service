package by.itstep.stpnbelko.webservice.rest.ws;


import by.itstep.stpnbelko.webservice.model.Car;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/exchange-rates")
public class CurrencyService {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {

        return null;
    }
}
