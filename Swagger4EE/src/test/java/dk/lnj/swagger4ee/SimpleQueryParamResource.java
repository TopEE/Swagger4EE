/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import javax.ws.rs.core.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;




/**
 *
 * @author laj
 */
@Path("/simplequeryparam")
public class SimpleQueryParamResource {
    @GET
    @Path("/astring")
    @Produces({MediaType.APPLICATION_JSON})
    @SwaggerOperation
    public Response simpleString(@QueryParam("value") String value){
      return Response.ok().build();
    }
}
