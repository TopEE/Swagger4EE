package dk.lnj.swagger4ee;

import java.lang.annotation.*;

/**
 * Adds and manipulates data of a model property.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiModelProperty {
    /**
     * A brief description of this property.
     */
    String value() default "";

    /**
     * Limits the acceptable values for this property.
     * <p/>
     * There are three ways to describe the allowable values:
     * <ol>
     * <li>To set a list of values, provide a comma-separated list surrounded by square brackets.
     * For example: {@code [first, second, third]}.</li>
     * <li>To set a range of values, start the value with "range", and surrounding by square
     * brackets include the minimum and maximum values. For example: {@code range[1, 5]}.</li>
     * <li>To set a minimum/maximum value, use the same format for range but use "infinity"
     * or "-infinity" as the second value. For example, {@code range[1, infinity]} means the
     * minimum allowable value of this parameter is 1.</li>
     * </ol>
     */
    String allowableValues() default "";

    /**
     * Allows for filtering a property from the API documentation.
     *
     * @see com.wordnik.swagger.core.filter.SwaggerSpecFilter
     */
    String access() default "";

    /**
     * Currently not in use.
     */
    String notes() default "";

    /**
     * The data type of the parameter.
     * <p/>
     * This can be the class name or a primitive. The value will override the data type as read from the class
     * property.
     */
    String dataType() default "";

    /**
     * Specifies if the parameter is required or not.
     */
    boolean required() default false;

    /**
     * Allows explicitly ordering the property in the model.
     */
    int position() default 0;

    /**
     * Allows a model property to be hidden in the Swagger model definition.
     */
    boolean hidden() default false;
}
