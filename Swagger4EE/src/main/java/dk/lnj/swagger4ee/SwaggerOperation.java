package dk.lnj.swagger4ee;

import java.lang.annotation.*;

/**
 * Use it to get method on SWAGGER interface
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SwaggerOperation {
    String description() default "";
    String summary() default "";
}
