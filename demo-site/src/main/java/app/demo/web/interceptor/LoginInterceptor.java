package app.demo.web.interceptor;

import core.framework.api.http.HTTPStatus;
import core.framework.web.Interceptor;
import core.framework.web.Invocation;
import core.framework.web.Response;

import java.util.Optional;

/**
 * @author kimi
 */
public class LoginInterceptor implements Interceptor {
    public static final String USER_NAME = "USER_NAME";

    @Override
    public Response intercept(Invocation invocation) throws Exception {
        Protected annotation = invocation.annotation(Protected.class);
        if (annotation == null) return invocation.proceed();

        Optional<String> userName = invocation.context().request().session().get(USER_NAME);
        if (userName.isEmpty()) {
            return invocation.proceed().status(HTTPStatus.UNAUTHORIZED);
        }
        return invocation.proceed();
    }
}
