/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.generator;

import dk.lnj.swagger4ee.SwaggerOperation;
import dk.lnj.swagger4ee.SwaggerParam;
import dk.lnj.swagger4ee.SwaggerResponse;
import dk.lnj.swagger4ee.SwaggerResponses;
import dk.lnj.swagger4ee.model.SWItem;
import dk.lnj.swagger4ee.model.SWModel;
import dk.lnj.swagger4ee.model.SWOperation;
import dk.lnj.swagger4ee.model.SWParameter;
import dk.lnj.swagger4ee.model.SWPath;
import dk.lnj.swagger4ee.model.SWResponse;
import dk.lnj.swagger4ee.model.SWRoot;
import dk.lnj.swagger4ee.model.SWSchemaRef;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 *
 * @author laj
 */
public class SwaggerGenerator {

    public static void createPaths(Class<?> clazz, SWRoot root) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            SwaggerOperation annotation = method.getAnnotation(SwaggerOperation.class);
            if (annotation == null) {
                continue;
            }

            Path pathAnnotation = method.getAnnotation(Path.class);
            String sPath = SwaggerUtil.calculateBasePath(clazz) + pathAnnotation.value();
            SWPath path = root.getPaths().get(sPath);
            if (path == null) {
                path = new SWPath();
                System.out.println(sPath);
                root.getPaths().put(sPath, path);
            }

            SWOperation op = createOperation(clazz, method, root.getModels(), SwaggerUtil.getDefaultProduces(clazz));
            if ("GET".equalsIgnoreCase(SwaggerUtil.calculateHttpMethod(method))) {
                path.setGet(op);
            }
            if ("POST".equalsIgnoreCase(SwaggerUtil.calculateHttpMethod(method))) {
                path.setPost(op);
            }
            if ("DELETE".equalsIgnoreCase(SwaggerUtil.calculateHttpMethod(method))) {
                path.setDelete(op);
            }
            if ("PUT".equalsIgnoreCase(SwaggerUtil.calculateHttpMethod(method))) {
                path.setPut(op);
            }
        }
    }

    public static SWOperation createOperation(Class<?> c, Method method, Map<String, SWModel> models, String[] defaultProducers) {
        SWOperation swagOperation = new SWOperation();

        swagOperation.getTags().add(c.getSimpleName());
        swagOperation.setParameters(createParameters(method, models));
        swagOperation.setOperationId(c.getSimpleName() + "." + method.getName());

        Produces prod = c.getAnnotation(Produces.class);
        if (prod != null) {
            swagOperation.setProduces(prod.value());
        }
        prod = method.getAnnotation(Produces.class);
        if (prod != null) {
            swagOperation.setProduces(prod.value());
        }

        swagOperation.getResponses().put("200", new SWResponse(createSchema(method.getReturnType(), method.getGenericReturnType(), models), "200"));

        return createOperationSWAG(c, method, models, defaultProducers, swagOperation);
    }

    public static SWOperation createOperationSWAG(Class<?> c, Method method, Map<String, SWModel> models, String[] defaultProducers, SWOperation swagOperation) {
        SwaggerOperation annotation = method.getAnnotation(SwaggerOperation.class);
        swagOperation.setSummary(annotation.summary());
        swagOperation.setDescription(annotation.description());

        SwaggerResponse response = method.getAnnotation(SwaggerResponse.class);
        if (response != null) {
            SWResponse createResponseSWAG = createResponseSWAG(response, models);
            swagOperation.getResponses().put("" + response.code(), createResponseSWAG);
        }
     
        SwaggerResponses responses = method.getAnnotation(SwaggerResponses.class);
        if (responses != null) {

            for (SwaggerResponse responsC : responses.value()) {
              
                SWResponse createResponseSWAG = createResponseSWAG(responsC, models);
                swagOperation.getResponses().put("" + responsC.code(), createResponseSWAG);
            }

        }

        return swagOperation;
    }

    public static SWResponse createResponseSWAG(SwaggerResponse response, Map<String, SWModel> models) {
        SWResponse res = new SWResponse();
        res.setDescription(response.description());
        if (List.class == response.collectionClazz()){
            res.setSchema(createSchema(response.clazz(), true, models));
        } else {
            res.setSchema(createSchema(response.clazz(), false, models));
        }
        return res;

    }

    static List<SWParameter> createParameters(Method method, Map<String, SWModel> models) {
        List<SWParameter> params = new ArrayList<SWParameter>();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            SWParameter parameter = new SWParameter();

            Class<?> p = parameterTypes[i];

            Context uriinfo = SwaggerUtil.getArgumentAnnotation(method, i, Context.class
            );
            if (uriinfo
                    != null) {
                continue;
            }

            //body
            if (SwaggerUtil.isClass(parameterTypes[i])) {
                parameter.setIn("body");
                parameter.setName(p.getSimpleName());
                parameter.setSchema(new SWSchemaRef(p.getSimpleName()));
                models.put(p.getSimpleName(), ModelGenerator.createModel(p, models));
            }

            //path
            PathParam pathp = SwaggerUtil.getArgumentAnnotation(method, i, PathParam.class);
            if (pathp
                    != null) {
                parameter.setIn("path");
                parameter.setName(pathp.value());
                parameter.setType(SwaggerUtil.calculateType(parameterTypes[i].getSimpleName().toLowerCase()));
                parameter.setFormat(SwaggerUtil.calculateFormat(parameterTypes[i].getSimpleName().toLowerCase()));
            }

            //query
            QueryParam query = SwaggerUtil.getArgumentAnnotation(method, i, QueryParam.class);
            if (query
                    != null) {
                parameter.setIn("query");
                parameter.setName(query.value());
                parameter.setType(SwaggerUtil.calculateType(p.getSimpleName().toLowerCase()));
                parameter.setFormat(SwaggerUtil.calculateFormat(p.getSimpleName().toLowerCase()));
            }

            params.add(createParameterSWAG(parameter, method, i));
        }
        return params;
    }

    public static SWParameter createParameterSWAG(SWParameter parameter, Method method, int i) {
        //extra annotation
        SwaggerParam api = SwaggerUtil.getArgumentAnnotation(method, i, SwaggerParam.class);
        if (api != null) {
            parameter.setRequired(api.required());

        }
        return parameter;
    }

    public static SWSchemaRef createSchema(Class<?> c, boolean array, Map<String, SWModel> models) {
        SWSchemaRef ref = null;
        if (c.getSimpleName().equalsIgnoreCase("Response") || c.getSimpleName().equalsIgnoreCase("void")) {
            return null;
        }
        if (SwaggerUtil.isClass(c)) {
            models.put(c.getSimpleName(), ModelGenerator.createModel(c, models));
            if (array) {
                return new SWSchemaRef(new SWItem(c.getSimpleName()));
            } else {
                return new SWSchemaRef(c.getSimpleName());
            }
        } else {
            if (array) {
                System.out.println("98769 NOT IMPMENTET");
                return null;
            } else {
                return new SWSchemaRef(SwaggerUtil.calculateType(c.getSimpleName()), SwaggerUtil.calculateFormat(c.getSimpleName()));
            }
        }
    }

    public static SWSchemaRef createSchema(Class<?> c, Type t, Map<String, SWModel> models) {
        if (c.getSimpleName().equalsIgnoreCase("List")) {
            ParameterizedType elementType = (ParameterizedType) t;
            Class<?> stringListClass = (Class<?>) elementType.getActualTypeArguments()[0];
            return createSchema(stringListClass, true, models);
        } else {
            return createSchema(c, false, models);
        }

    }
}
