package org.glearn.jaxrs.testing.railway.service.impl;

import org.glearn.jaxrs.testing.exception.ResourceNotFoundException;
import org.glearn.jaxrs.testing.railway.api.Train;
import org.glearn.jaxrs.testing.railway.service.TrainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Controller
@Path(TrainController.TRAINS_URI)
public class TrainControllerImpl implements TrainController {

    @Autowired
    TrainService trainService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Train> getTrains(){
        return trainService.getTrains();
    }

    @GET
    @Path("{trainId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Train getTrain(@PathParam("trainId") String trainId) throws ResourceNotFoundException {
        return trainService.getTrain(trainId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Train addTrain(Train train) {
        return trainService.addTrain(train);
    }

    @DELETE
    @Path("{trainId}")
    public void deleteTrain(@PathParam("trainId")String trainId) {
        trainService.deleteTrain(trainId);
    }
}
