package jdk.spring.app;

import javax.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class AppConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
            MessageDispatcherServlet servlet = new MessageDispatcherServlet();
            servlet.setApplicationContext(applicationContext);
            servlet.setTransformWsdlLocations(true);
            return new ServletRegistrationBean<>(servlet, "/spring/ws/*");
    }

    @Bean(name = "ServizioPrenotazione")
    public Wsdl11Definition defaultWsdl11Definition() {
            SimpleWsdl11Definition wsdl11Definition =
                    new SimpleWsdl11Definition();
            wsdl11Definition.setWsdl(new ClassPathResource("/ws.wsdl"));
            return wsdl11Definition;
    }

    @Bean
    public XsdSchema dependency() {
        return new SimpleXsdSchema(
                new ClassPathResource("/dependency.xsd"));
    }

    @Bean
    public XsdSchema reservation() {
        return new SimpleXsdSchema(
                new ClassPathResource("/reservation.xsd"));
    }

    @Bean
    public XsdSchema customer() {
        return new SimpleXsdSchema(
                new ClassPathResource("/customer.xsd"));
    }


    /* @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
        validatingInterceptor.setValidateRequest(true);
        validatingInterceptor.setValidateResponse(true);
        validatingInterceptor.setXsdSchema(yourSchema());
        interceptors.add(validatingInterceptor);
    }*/

    /*
    * @Bean
   public XsdSchemaCollection schemaCollection() {
    CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
            new ClassPathResource("xsd/po.xsd"),
            new ClassPathResource("xsd/orders.xsd"));
    commonsXsdSchemaCollection.setInline(true);
    return commonsXsdSchemaCollection;
}*/
}

