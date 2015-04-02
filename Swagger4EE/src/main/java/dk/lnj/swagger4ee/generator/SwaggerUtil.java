/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.lnj.swagger4ee.generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author laj
 */
public class SwaggerUtil {
        public static boolean isClass(Class<?> response) {
        if ("Void".equalsIgnoreCase(response.getSimpleName()) || "String".equalsIgnoreCase(response.getSimpleName())
                || "int".equalsIgnoreCase(response.getSimpleName()) || "Class".equalsIgnoreCase(response.getSimpleName())
                || "byte[]".equalsIgnoreCase(response.getSimpleName()) || "long".equalsIgnoreCase(response.getSimpleName())
                || "float".equalsIgnoreCase(response.getSimpleName()) || "double".equalsIgnoreCase(response.getSimpleName())
                || "date".equalsIgnoreCase(response.getSimpleName())) {
            return false;
        }
        return true;
    }

    public static String calculateFormat(String type) {
        if (type.equalsIgnoreCase("long")) {
            return "int64";
        }
        if (type.equalsIgnoreCase("int")) {
            return "int32";
        }
        if (type.equalsIgnoreCase("float")) {
            return "float";
        }
        if (type.equalsIgnoreCase("double")) {
            return "double";
        }
        
        return null;
    }

    public static String calculateType(String type) {
        if (type.equalsIgnoreCase("String")) {
            return "string";
        }
        if (type.equalsIgnoreCase("long")) {
            return "integer";
        }
        if (type.equalsIgnoreCase("int")) {
            return "integer";
        }
        if (type.equalsIgnoreCase("float")) {
            return "number";
        }
        if (type.equalsIgnoreCase("double")) {
            return "number";
        }
        if (type.equalsIgnoreCase("date")) {
            return "string";
        }
        return null;
    }

    

    static <T extends Object> T getArgumentAnnotation(Method method, int argNumber, Class<T> clazz) {
        Annotation[] annotations = method.getParameterAnnotations()[argNumber];
        for (Annotation annotation : annotations) {
            if (clazz.isInstance(annotation)) {
                return clazz.cast(annotation);
            }
        }
        return null;

    }

    static String calculateBasePath(Class<?> clazz) {
        String path = ((Path) clazz.getAnnotation(Path.class
        )).value();
        if (!path.startsWith(
                "/")) {
            path = "/" + path;
        }
        return path;
    }

    static String[] getDefaultProduces(Class<?> clazz) {
        Annotation annotation = clazz.getAnnotation(Produces.class
        );
        if (annotation
                == null) {
            return new String[]{"application/xml", "application/json"};
        }
        Produces p = (Produces) annotation;

        return p.value();
    }

    static String calculateHttpMethod(Method m) {
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
