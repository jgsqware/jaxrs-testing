package org.glearn.jaxrs.testing.railway.service.impl;

import org.glearn.jaxrs.testing.exception.ResourceNotFoundException;
import org.glearn.jaxrs.testing.railway.api.Train;
import org.glearn.jaxrs.testing.railway.api.impl.TrainImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class TrainServiceImpl implements TrainService {

    private Collection<Train> trains;

    public TrainServiceImpl() {
        this.trains = new ArrayList<Train>();
    }

    public Collection<Train> getTrains(){
        return trains;
    }

    public Train getTrain(String trainId) throws ResourceNotFoundException {
        for (Train train : trains) {
            if(train.getId().equals(trainId)){
                return train;
            }
        }

        throw new ResourceNotFoundException("The train "+trainId+" was not found.");
    }

    public Train addTrain(Train train){
        TrainImpl trainBusiness = TrainImpl.newBuilder()
                .id(UUID.randomUUID().toString())
                .type(train.getType())
                .firstWay(train.getFirstWay())
                .build();

        trains.add(trainBusiness);
        return trainBusiness;
    }

    public void deleteTrain(String trainId){
        for (Train train : trains) {
            if(train.getId().equals(trainId)){
                trains.remove(train);
                return;
            }
        }
        throw new ResourceNotFoundException("The train "+trainId+" was not found.");
    }
}
