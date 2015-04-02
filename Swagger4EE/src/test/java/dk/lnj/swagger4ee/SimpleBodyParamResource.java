/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import javax.ws.rs.core.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;




/**
 *
 * @author laj
 */
@Path("/simplebodyparam")
public class SimpleBodyParamResource {
    @POST
    @Path("/body1")
    @Produces({MediaType.APPLICATION_JSON})
    @SwaggerOperation
    public Response simpleBody1(Body1 value){
      return Response.ok().build();
    }
    
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body1{
        private String felt1;
        private long felt2;
    }
}
