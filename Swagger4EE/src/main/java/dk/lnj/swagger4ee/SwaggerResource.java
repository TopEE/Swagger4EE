/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonMappingException;

import dk.lnj.swagger4ee.SwaggerModel.ApiResume;
import dk.lnj.swagger4ee.SwaggerModel.Model;
import dk.lnj.swagger4ee.SwaggerModel.ModelProperty;
import dk.lnj.swagger4ee.SwaggerModel.Operation;
import dk.lnj.swagger4ee.SwaggerModel.Parameter;
import dk.lnj.swagger4ee.SwaggerModel.RESTResource;
import dk.lnj.swagger4ee.SwaggerModel.ResponseClass;
import dk.lnj.swagger4ee.SwaggerModel.Root;
import dk.lnj.swagger4ee.SwaggerModel.SApi;

/**
 * 
 * @author Lars
 */
@Path("")
public abstract class SwaggerResource {
	
	
	public SwaggerResource() {
		
	}
	
	public abstract String getSwaggerUrl();
	public abstract Class<?>[] getResourcesForSwagging();

	@GET
	@Path("/api-docs")
	@Produces(MediaType.APPLICATION_JSON)
	public Root getResume() {
		try {
			Root sr = new Root();
			for (Class<?> api : getResourcesForSwagging()) {

				sr.apis.add(new ApiResume(getBasePath(api)));
			}
			return sr;
		} catch (Exception e) {
			java.util.logging.Logger.getLogger("SWAGGER").throwing(this.getClass().getSimpleName(), "SwagResource", e);
			return null;
		}
	}

	@GET
	@Path("/api-docs/{api}")
	@Produces(MediaType.APPLICATION_JSON)
	public RESTResource getSWAG(@PathParam("api") String api) throws ClassNotFoundException, IllegalAccessException, InstantiationException, JsonMappingException {
		Class<?> findClassFromBasePath = findClassFromBasePath(api);

		RESTResource swagger = createPathIfNotExistAndAddOperations(findClassFromBasePath);
		return swagger;
	}

	RESTResource createPathIfNotExistAndAddOperations(Class<?> clazz) {
		RESTResource swagger = new RESTResource(getSwaggerUrl());
		swagger.resourcePath = getBasePath(clazz);

		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			Path pathAnnotation = method.getAnnotation(Path.class);
			if (pathAnnotation == null) {
				continue;
			}

			SApi swagPath = null;
			for (SApi swp : swagger.apis) {
				if (swp.path.equals(pathAnnotation.value())) {
					swagPath = swp;
				}
			}
			if (swagPath == null) {
				swagPath = new SApi(swagger.resourcePath + pathAnnotation.value());
				swagPath.operations.add(createOperation(method, swagger.models, getDefaultProduces(clazz)));

				swagger.apis.add(swagPath);
			}
		}
		return swagger;
	}

	public static Operation createOperation(Method method, Map<String, Model> models, String[] defaultProducers) {
		Operation swagOperation = new Operation();
		swagOperation.method = getHttpMethod(method);
		swagOperation.parameters = createParameters(method, models);

		
		swagOperation.nickname = method.getName();
		
		Produces prod = method.getAnnotation(Produces.class);
		if (prod != null) {
			swagOperation.produces = prod.value();
		} else {
			swagOperation.produces = defaultProducers;
		}
		
		ApiOperation annotation = method.getAnnotation(ApiOperation.class);
		if (annotation == null) {
			return swagOperation;
		}

		swagOperation.type = annotation.response().getSimpleName();
		if (null!=annotation.responseContainer()) {
			swagOperation.type = annotation.responseContainer();
			swagOperation.items = new ResponseClass(method.getAnnotation(ApiOperation.class).response().getSimpleName());
		}

		if (isClass(annotation.response())) {
			models.put(annotation.response().getSimpleName(), createModel(annotation.response(),models));
		}

		swagOperation.summary = annotation.value();
		if (swagOperation.nickname != null && !swagOperation.nickname.equals("")) {
			swagOperation.nickname = method.getName();
		}

		swagOperation.notes = annotation.notes();

		
		return swagOperation;
	}

	private static boolean isClass(Class<?> response) {
		if ("Void".equalsIgnoreCase(response.getSimpleName()) || "String".equalsIgnoreCase(response.getSimpleName())  || "int".equalsIgnoreCase(response.getSimpleName())|| "Class".equalsIgnoreCase(response.getSimpleName()) || "byte[]".equalsIgnoreCase(response.getSimpleName())) {
			return false;
		}
		return true;
	}

	static List<Parameter> createParameters(Method method, Map<String, Model> models) {
		List<Parameter> params = new ArrayList<Parameter>();
		Class<?>[] parameterTypes = method.getParameterTypes();
		for (int i = 0; i < parameterTypes.length; i++) {
			Parameter parameter = new Parameter();
			parameter.type = parameterTypes[i].getSimpleName();

			Context uriinfo = getAnnotation(method, i, Context.class);
			if (uriinfo != null) {
				continue;
			}
			parameter.paramType = "body";
			PathParam pathp = getAnnotation(method, i, PathParam.class);
			if (pathp != null) {
				parameter.paramType = "path";
				parameter.name = pathp.value();
			}
			QueryParam query = getAnnotation(method, i, QueryParam.class);
			if (query != null) {
				parameter.paramType = "query";
				parameter.name = query.value();
				parameter.required = false;
			}
			ApiParam api = getAnnotation(method, i, ApiParam.class);
			if (api != null) {
				parameter.required = api.required();
				if (api.value() != null && api.value().length() > 0) {
					parameter.name = api.value();
					parameter.description = api.value();
				}
				parameter.defaultValue = api.defaultValue();
				parameter.allowMultiple = api.allowMultiple();
			}
			params.add(parameter);
			if (isClass(parameterTypes[i])) {
				models.put(parameterTypes[i].getSimpleName(), createModel(parameterTypes[i],models));
			}
		}
		return params;
	}

	Class<?> findClassFromBasePath(String bp) throws ClassNotFoundException {
		for (Class<?> api : getResourcesForSwagging()) {
			if (("/" + bp).equals(getBasePath(api))) {
				return api;
			}
		}
		return null;
	}

	static Model createModel(Class<?> c, Map<String, Model> models) {
		
		Model model = new Model(c.getSimpleName());
		Field[] declaredFields = c.getDeclaredFields();
		
				
		for (Field field : declaredFields) {
			
			if (isClass(field.getType())) {
				if(field.getType().isEnum()){
					Field[] enums = field.getType().getDeclaredFields();
					List<String> names = new ArrayList<String>();
					for (Field field2 : enums) {
						if(!field2.getName().equalsIgnoreCase("ENUM$VALUES")){
						names.add(field2.getName());
						}
					}
					model.properties.put(field.getName(), new ModelProperty("String",null ,names));
				}
				else{
				model.properties.put(field.getName(), new ModelProperty(null, field.getType().getSimpleName(),null));
				models.put(field.getType().getSimpleName(), createModel(field.getType(), models));
				}
				
			} else {
				model.properties.put(field.getName(), new ModelProperty(field.getType().getSimpleName(), null,null));
			}
		}
		return model;
	}

	static <T extends Object> T getAnnotation(Method method, int argNumber, Class<T> clazz) {
		Annotation[] annotations = method.getParameterAnnotations()[argNumber];
		for (Annotation annotation : annotations) {
			if (clazz.isInstance(annotation)) {
				return clazz.cast(annotation);
			}
		}
		return null;
	}

	static String getBasePath(Class<?> clazz) {
		String path = ((Path) clazz.getAnnotation(Path.class)).value();
		if(!path.startsWith("/")){
			path = "/" + path;
		}
		return path;
	}

	static String[] getDefaultProduces(Class<?> clazz) {
		Annotation annotation = clazz.getAnnotation(Produces.class);
		if (annotation == null) {
			return new String[] { "application/xml", "application/json" };
		}
		Produces p = (Produces) annotation;
		return p.value();
	}

	static String getHttpMethod(Method m) {
		if (m.getAnnotation(GET.class) != null) {
			return "GET";
		}
		if (m.getAnnotation(POST.class) != null) {
			return "POST";
		}
		if (m.getAnnotation(PUT.class) != null) {
			return "PUT";
		}
		return "DELETE";
	}
}
