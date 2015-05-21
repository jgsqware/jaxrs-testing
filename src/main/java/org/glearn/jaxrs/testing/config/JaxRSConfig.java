package org.glearn.jaxrs.testing.config;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Configuration
@PropertySource("classpath:configuration.properties")
public abstract class JaxRSConfig {

    @Value("${server.uri}")
    private String serverUri;

    @Value("${server.port}")
    private int serverPort;

    protected abstract List<Object> getServices();
    protected abstract List<Object> getProviders();



    @Bean
    public Server createServer(){
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        jaxrsServerFactoryBean.setAddress(getServerUri().toString());
        jaxrsServerFactoryBean.setProviders(getProviders());
        jaxrsServerFactoryBean.setServiceBeans(getServices());
        return jaxrsServerFactoryBean.create();
    }

    public URI getServerUri() {
        return UriBuilder.fromUri(serverUri).port(serverPort).build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
