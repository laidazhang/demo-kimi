package app.demo.web;

import core.framework.web.Request;
import core.framework.web.Response;

/**
 * @author kimi
 */
public class HomeController {
    public Response sayHello(Request request) {
        return Response.text("hello");
    }
}
