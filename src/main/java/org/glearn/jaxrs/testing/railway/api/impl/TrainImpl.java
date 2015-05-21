package org.glearn.jaxrs.testing.railway.api.impl;

import org.glearn.jaxrs.testing.railway.api.Train;
import org.glearn.jaxrs.testing.railway.api.TrainType;

import java.util.Date;

public class TrainImpl implements Train {

    private String id;
    private TrainType type;
    private Date firstWay;

    private TrainImpl() {
    }

    private TrainImpl(Builder builder) {
        id = builder.id;
        type = builder.type;
        firstWay = builder.firstWay;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public TrainType getType() {
        return type;
    }

    public Date getFirstWay() {
        return firstWay;
    }

    public static final class Builder {
        private String id;
        private TrainType type;
        private Date firstWay;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder type(TrainType type) {
            this.type = type;
            return this;
        }

        public Builder firstWay(Date firstWay) {
            this.firstWay = (Date)firstWay.clone();
            return this;
        }

        public TrainImpl build() {
            return new TrainImpl(this);
        }
    }
}
