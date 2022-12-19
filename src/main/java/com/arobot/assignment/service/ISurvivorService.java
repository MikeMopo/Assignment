package com.arobot.assignment.service;

import com.arobot.assignment.entity.Location;
import com.arobot.assignment.entity.Robot;
import com.arobot.assignment.entity.Survivor;

import java.util.List;
import java.util.Map;

public interface ISurvivorService {

    Survivor registerSurvivor(Survivor survivor);
    List<Robot> findAllRobotsAndDetails();
    List<Survivor> getAllSurvivor();
    Survivor getSurvivor(Long id);
    Survivor reportSurvivorAsNotInfected(Long id);
    void updateSurvivorLocation(Long id, Location location);
    Map<String, String> findInfectionRatio();
    Survivor reportSurvivorAsInfected(Long id);
    List<Survivor> negativeInfectionTestResult();
    List<Survivor> positiveInfectionTestResult();
}
