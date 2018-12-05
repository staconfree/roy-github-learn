package roy.github.learn.springmvc.springmvcinterface;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * springmvc参数解析器，需要注册到 List<HandlerMethodArgumentResolver>
 * Created by roy on 2018/11/24 0024.
 */
public class TestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter methodParameter) {
        // 判断方法参数是否具有某些特点
        // 比如包含我们自定义的注解，可以 return methodParameter.hasParameterAnnotation(LoginUser.class);
        // 或者 return parameter.getParameterType().equals(User.class);
        return false;
    }

    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 参数解析成对象
//        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//        User user = (User) request.getAttribute("user");
//        return user;
        return null;
    }
}
