package org.glearn.jaxrs.testing.railway.service.impl;

import org.glearn.jaxrs.testing.exception.ResourceNotFoundException;
import org.glearn.jaxrs.testing.railway.api.Train;

import java.util.Collection;

public interface TrainService {
    Collection<Train> getTrains();

    Train getTrain(String trainId) throws ResourceNotFoundException;

    Train addTrain(Train train);

    void deleteTrain(String trainId);
}
