package org.glearn.jaxrs.testing.railway.service;

import org.glearn.jaxrs.testing.exception.ResourceNotFoundException;
import org.glearn.jaxrs.testing.railway.api.Train;

import java.util.Collection;

public interface TrainController {
    String TRAINS_URI = "trains";

    Collection<Train> getTrains();
    Train getTrain(String trainId) throws ResourceNotFoundException;
    Train addTrain(Train train);
    void deleteTrain(String trainId);
}
