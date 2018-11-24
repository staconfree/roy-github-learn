package roy.github.learn.spring.springmvc.springmvcinterface;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * springmvc返回解析器
 * Created by roy on 2018/11/24 0024.
 */
public class TestHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    public boolean supportsReturnType(MethodParameter returnType) {
//        return returnType.getParameterType() == Person.class;
        return false;
    }

    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
//        Assert.isInstanceOf(Person.class, returnValue);
//        mavContainer.setRequestHandled(true);//其中mavContainer.setRequestHandled(true);标志着此次请求是否是由handler自己控制的，true表示本方法会响应请求。
//        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
//        response.setContentType("text/plain;charset=utf-8");
//        Person person = (Person) returnValue;
//        response.getWriter().write("经过HandlerMethodReturnValueHandler输出的person:" + person);
    }
}
