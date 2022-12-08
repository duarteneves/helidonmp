package pt.dneves.cloud.helidon.mp.endpoint;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.dneves.cloud.helidon.mp.manager.ProductManager;
import pt.dneves.cloud.helidon.mp.model.Product;

@Path("products")
@RequestScoped
public class ProductEndpoint {

    @Inject
    private ProductManager productManager;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product book = productManager.get(id);
        return Response.ok(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        return Response.ok(productManager.getAll()).build();
    }

}
