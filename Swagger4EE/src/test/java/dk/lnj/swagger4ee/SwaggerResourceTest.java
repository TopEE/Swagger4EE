/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;
import com.sun.xml.internal.ws.api.message.saaj.SaajStaxWriter;
import dk.lnj.swagger4ee.SwaggerModel.Model;
import dk.lnj.swagger4ee.SwaggerModel.Operation;
import dk.lnj.swagger4ee.SwaggerModel.RESTResource;
import java.util.HashMap;

import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Test;




public class SwaggerResourceTest {
	@Test
	public void testCreateMethodWithPathParam() throws Exception{
		Operation o = SwaggerResource.createOperation(Resource4Test.class.getMethod("methodWithPathParam", String.class), new HashMap<String, Model>(),new String[]{"application/json"} );
		Assert.assertEquals("GET", o.method);
		Assert.assertEquals("methodWithPathParam", o.nickname);
		Assert.assertEquals("", o.notes);
		Assert.assertEquals("test", o.parameters.get(0).name);
	}
	
	@Test
	public void testCreateMethodNoAPiOperationAnnotation() throws Exception{
		Operation o = SwaggerResource.createOperation(Resource4Test.class.getMethod("methodThatReturnListOfJAXBNoAPi"), new HashMap<String, Model>(),new String[]{"application/json"} );
		Assert.assertEquals("methodThatReturnListOfJAXBNoAPi", o.nickname);
		Assert.assertEquals("application/json", o.produces[0]);
	}
	
	@Test
	public void testCreateMethodWithQueryParam() throws Exception{
		Operation o = SwaggerResource.createOperation(Resource4Test.class.getMethod("methodWithQueryParam",int.class), new HashMap<String, Model>(),new String[]{"application/json"} );
		Assert.assertEquals("query", o.parameters.get(0).paramType);
		Assert.assertEquals("int", o.parameters.get(0).type);
		Assert.assertEquals("size", o.parameters.get(0).name);
	}
	
	@Test
	public void testReturnList() throws Exception{
		Operation o = SwaggerResource.createOperation(Resource4Test.class.getMethod("methodThatReturnListOfJAXB"), new HashMap<String, Model>(),new String[]{"application/json"} );
		Assert.assertEquals("methodThatReturnListOfJAXB", o.nickname);
		Assert.assertEquals("application/json", o.produces[0]);
		Assert.assertEquals("List", o.type);
		Assert.assertEquals("SampleObject", o.items.ref);
	}
	
	@Test
	public void createPathIfNotExistAndAddOperations() throws Exception{
		RESTResource rr = new SwaggerResource() {

                    @Override
                    public String getSwaggerUrl() {
                       return "http://testurl";
                    }

                    @Override
                    public Class<?>[] getResourcesForSwagging() {
                       return  new Class[]{Resource4Test.class};
                    }
                }.createPathIfNotExistAndAddOperations(Resource4Test.class);
		Assert.assertEquals("/testres", rr.resourcePath);
	}
	
	@Test
	public void testCreateModel() throws Exception{
		Model m = SwaggerResource.createModel(Resource4Test.SampleObject.class, new HashMap<String, SwaggerModel.Model>());
		Assert.assertEquals("SampleObject", m.id);
		Assert.assertEquals("String", m.properties.get("test1").type);
	}
	
	@Test
	public void testCreateModelForEnumInTree() throws Exception{
		Model m = SwaggerResource.createModel(Resource4Test.SampleObject.class, new HashMap<String, SwaggerModel.Model>());
		Assert.assertEquals("SampleObject", m.id);
	//	System.out.println(m.properties.containsKey(key));
		Assert.assertTrue(2 < m.properties.get("testEnum").enums.size());
	}
}

