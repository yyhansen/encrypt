package aes.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created by chenhansen on 2018/5/11.
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }



    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //super.customizeRegistration(registration);
        //上传文件写入临时路径，上传文件最大容量（默认无限制），整个multipart请求的最大容量，为0代表所有上传文件写入到磁盘上
        registration.setMultipartConfig(
                new MultipartConfigElement("F:\\aes\\uploads",2097152,4194304,0)
        );
    }

}
