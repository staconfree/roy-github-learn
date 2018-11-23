package roy.github.learn.cat.config;

import com.dianping.cat.servlet.CatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2018/11/20.
 */
@Configuration
public class MyCatFilter {

    @Bean
    public FilterRegistrationBean defaultCatFilter() {
        Filter catFilter = new CatFilter();
        FilterRegistrationBean mappingDruid = new FilterRegistrationBean(catFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        mappingDruid.setUrlPatterns(urlPatterns);
        mappingDruid.setOrder(4);
        return mappingDruid;
    }

}
