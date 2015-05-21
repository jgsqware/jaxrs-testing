package org.glearn.jaxrs.testing.railway.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glearn.jaxrs.testing.railway.api.impl.TrainImpl;

import java.io.Serializable;
import java.util.Date;

@JsonDeserialize(as = TrainImpl.class)
public interface Train extends Serializable{
    String getId();
    TrainType getType();
    Date getFirstWay();
}
