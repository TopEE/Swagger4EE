package dk.lnj.swagger4ee;

import java.lang.annotation.*;

/**
 * Declares an authorization scheme to be used on a resource or an operation.
 * <p/>
 * The authorization scheme used needs to be defined in the Resource Listing's authorization
 * section.
 * <p/>
 * This annotation is not used directly and will not be parsed by Swagger. It should be used
 * within either {@link com.wordnik.swagger.annotations.Api} or {@link com.wordnik.swagger.annotations.ApiOperation}.
 *
 * @see com.wordnik.swagger.annotations.ApiOperation
 * @see com.wordnik.swagger.annotations.Api
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Authorization {
    /**
     * The name of the authorization scheme to be used on this resource/operation.
     * <p/>
     * The name must be defined in the Resource Listing's authorization section,
     */
    String value();

    /**
     * The scopes to be used if the authorization scheme is OAuth2.
     *
     * @see com.wordnik.swagger.annotations.AuthorizationScope
     */
    AuthorizationScope[] scopes() default @AuthorizationScope(scope = "", description = "");
}