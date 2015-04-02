package dk.lnj.swagger4ee;

import java.lang.annotation.*;

/**
 * Adds additional meta-data for operation parameters.
 * <p/>
 * This annotation can be used only in combination of JAX-RS 1.x/2.x annotations.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SwaggerParam {
   boolean required() default true;
}