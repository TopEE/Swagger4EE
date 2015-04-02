/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;




/**
 *
 * @author laj
 */
@Path("/simplepathparam")
public class SimplePathParamResource {    
    @GET
    @Path("/ainteger/{intValue}/{longValue}")
    @Produces({MediaType.APPLICATION_JSON})
    @SwaggerOperation
    public int simpleInteger(@PathParam("intValue") int intValue,@PathParam("longValue") long longValue){
      return 10;
    }
    
       @GET
    @Path("/anumber/{floatValue}/{doubleValue}")
    @Produces({MediaType.APPLICATION_JSON})
    @SwaggerOperation
    public List<Body1> simpleNumber(@PathParam("floatValue") float floatvalue,@PathParam("doubleValue") double doubleValue){
      return new ArrayList<Body1>();
    }
    
    @GET
    @Path("/astring/{value}")
    @Produces({MediaType.APPLICATION_JSON})
    @SwaggerOperation
    public Body1 simpleString(@PathParam("value") String value){
      return new Body1();
    }
    
      @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body1{
        private String felt1;
        private long felt2;
    }
      
}
