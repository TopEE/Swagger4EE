/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;


import dk.lnj.swagger4ee.generator.SwaggerGenerator;
import dk.lnj.swagger4ee.model.SWInfo;
import dk.lnj.swagger4ee.model.SWRoot;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lars
 */
@Path("")
public abstract class SwaggerResource {

    public SwaggerResource() {

    }

    public abstract SWInfo getSwaggerInfo();
    public abstract String getHost();
    public abstract String getRootPath();
    public abstract Class<?>[] getResourcesForSwagging();

    @GET
    @Path("/swagger")
    @Produces(MediaType.APPLICATION_JSON)
    public SWRoot createSwag() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	SWInfo info = getSwaggerInfo();
    	
        SWRoot root = new SWRoot(getHost(),getRootPath());
  

        root.setInfo(info);
        for (Class<?> api : getResourcesForSwagging()) {
            SwaggerGenerator.createPaths(api, root);
        }
        return root;
    }

    
}
