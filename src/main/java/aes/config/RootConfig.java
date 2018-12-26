package aes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Created by chenhansen on 2018/5/11.
 */
@Configuration
@ComponentScan(basePackages = {"aes"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)

})

public class RootConfig {

}
