package org.glearn.jaxrs.testing.railway.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glearn.jaxrs.testing.config.JaxRSConfig;
import org.glearn.jaxrs.testing.railway.service.TrainController;
import org.glearn.jaxrs.testing.railway.service.impl.TrainControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = TrainControllerImpl.class)
public class RailwayJaxRSConfig extends JaxRSConfig {

    @Autowired
    private TrainControllerImpl trainController;

    @Override
    protected List<Object> getServices() {
        List<Object> services = new ArrayList<Object>();
        services.add(trainController);
        return services;
    }

    @Override
    protected List<Object> getProviders() {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());
        return providers;
    }

    public URI getTrainsUri() {
        return UriBuilder
                .fromUri(getServerUri())
                .path(TrainController.TRAINS_URI)
                .build();
    }

    public URI getTrainUri(String trainId) {
        return UriBuilder
                .fromUri(getServerUri())
                .path(TrainController.TRAINS_URI)
                .path(trainId)
                .build();
    }
}
