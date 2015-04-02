/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import dk.lnj.swagger4ee.model.SWInfo;
import dk.lnj.swagger4ee.model.SWRoot;
import org.junit.Test;

/**
 *
 * @author laj
 */
public class SimpleBodyParamTest extends AbstractJSonTest{

    @Test
    public void testClass() throws Exception {
        SwaggerResource sr = new SwaggerResource() {

           @Override
            public Class<?>[] getResourcesForSwagging() {
                return new Class<?>[]{SimpleBodyParamResource.class};
            }

            @Override
            public SWInfo getSwaggerInfo() {
               return new SWInfo("Swagger Simple Path Sample","1.0");
            }

            @Override
            public String getHost() {
                return "localhost:8182";
            }

            @Override
            public String getRootPath() {
                return "/rest";
            }
        };

        SWRoot swag = sr.createSwag();

        makeCompare(swag, "src/test/resources/SimpleBodyParam.json");
    }

  

}
