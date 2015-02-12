package dk.lnj.swagger4ee;

import java.lang.annotation.*;

/**
 * Describes an OAuth2 authorization scope.
 * <p/>
 * Used to declare an authorization scope that is used by a resource or an operation for
 * a defined authorization scheme.
 * <p/>
 * This annotation is not used directly and will not be parsed by Swagger. It should be used
 * within the {@link com.wordnik.swagger.annotations.Authorization}.
 *
 * @see com.wordnik.swagger.annotations.Authorization
 * @see com.wordnik.swagger.annotations.ApiOperation
 * @see com.wordnik.swagger.annotations.Api
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuthorizationScope {
    /**
     * The scope of the OAuth2 Authorization scheme to be used.
     * <p/>
     * The scope should be previously defined in the Resource Listing's authorization section.
     */
    String scope();

    /**
     * A brief description of the scope.
     */
    String description();
}
