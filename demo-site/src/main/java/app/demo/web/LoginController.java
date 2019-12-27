package app.demo.web;

import app.demo.web.ajax.LoginRequest;
import app.demo.web.interceptor.LoginInterceptor;
import core.framework.util.Strings;
import core.framework.web.Request;
import core.framework.web.Response;

/**
 * @author kimi
 */
public class LoginController {
    public Response login(Request request) {
        LoginRequest loginRequest = request.bean(LoginRequest.class);
        request.session().set(LoginInterceptor.USER_NAME, loginRequest.userName);
        return Response.text(Strings.format("login success, hello {}", request.session().get(LoginInterceptor.USER_NAME)));
    }
}
