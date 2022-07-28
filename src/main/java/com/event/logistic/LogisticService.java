package com.event.logistic;

import com.event.logistic.dao.LogisticModel;
import com.event.logistic.dao.LogisticRepository;
import org.springframework.stereotype.Service;

@Service
public class LogisticService {

    private final LogisticRepository logisticRepository;

    public LogisticService(LogisticRepository logisticRepository) {
        this.logisticRepository = logisticRepository;
    }

    public Logistic getLogistic(int logisticId) {
        LogisticModel logisticModel = logisticRepository.findById(logisticId).get();
        return createLogistic(logisticModel);
    }

    public Logistic addLogistic(Logistic logistic) {
        LogisticModel logisticModel = new LogisticModel(logistic.getId(), logistic.getDistance(), logistic.getNotes(),
                logistic.getRequiredArea(), logistic.getRequiredPower(), logistic.getPlaceType().toString(),
                logistic.getPowerType().toString());
        logisticRepository.save(logisticModel);
        logistic.setId(logisticModel.getId());
        return logistic;
    }

    public Logistic updateLogistic(int logisticId, Logistic newLogistic) {
        LogisticModel logisticModel = logisticRepository.findById(logisticId).get();
        logisticModel.setDistance(newLogistic.getDistance());
        logisticModel.setNotes(newLogistic.getNotes());
        logisticModel.setRequiredArea(newLogistic.getRequiredArea());
        logisticModel.setRequiredPower(newLogistic.getRequiredPower());
        logisticModel.setPlaceType(newLogistic.getPlaceType().toString());
        logisticModel.setPowerType(newLogistic.getPowerType().toString());
        logisticRepository.save(logisticModel);
        return newLogistic;
    }

    public String deleteLogistic(int logisticId) {
        logisticRepository.deleteById(logisticId);
        return "DELETED";
    }

    private Logistic createLogistic(LogisticModel model){
        return new Logistic(model.getId(), model.getDistance(), model.getNotes(), model.getRequiredArea(),
                model.getRequiredPower(), PlaceType.valueOf(model.getPlaceType()),
                PowerType.valueOf(model.getPowerType()));
    }
}
