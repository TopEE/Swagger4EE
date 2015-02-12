/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/testres")
public class Resource4Test {

    @GET
    @Path("/query")
    @ApiOperation("methodWithQueryParam")
    public String methodWithQueryParam(@QueryParam("size") int sInt) {
        return "hej" + sInt;
    }
    
        @GET
    @Path("/methodWithPathParam/{test}")
    @ApiOperation("methodWithPathParam")
    public String methodWithPathParam(@PathParam("test") String sInt) {
        return "hej" + sInt;
    }

    @GET
    @Path("/query")
    @ApiOperation(value="list",response = SampleObject.class,responseContainer = "List")
    public List<SampleObject> methodThatReturnListOfJAXB() {
        return new ArrayList<SampleObject>();
    }

    @GET
    @Path("/query")
    public List<SampleObject> methodThatReturnListOfJAXBNoAPi() {
        return new ArrayList<SampleObject>();
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SampleObject {

        String test1;
        int test2;
        
       TestEnum testEnum;
    }
    
     enum TestEnum{
            enum1,enum2,enum3;
    }
}
