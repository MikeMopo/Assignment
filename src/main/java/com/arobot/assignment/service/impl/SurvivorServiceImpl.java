package com.arobot.assignment.service.impl;


import com.arobot.assignment.entity.Location;
import com.arobot.assignment.entity.Robot;
import com.arobot.assignment.entity.Survivor;
import com.arobot.assignment.repository.LocationRepository;
import com.arobot.assignment.repository.SurvivorRepository;
import com.arobot.assignment.service.ISurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurvivorServiceImpl implements ISurvivorService {

    @Autowired
    private SurvivorRepository survivorRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private LocationRepository locationRepository;
//    private ApocalypseSurvivorDao apocalypseSurvivorDao;


    @Override
    public Survivor registerSurvivor(Survivor survivor) {
        return survivorRepository.save(survivor);
    }

    @Override
    public List<Survivor> getAllSurvivor() {
        return survivorRepository.findAll();
    }


    @Override
    public List<Robot> findAllRobotsAndDetails() {
        ResponseEntity<List<Robot>> rateResponse = restTemplate.exchange(
                "https://robotstakeover20210903110417.azurewebsites.net/robotcpu", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Robot>>() {
                });
        List<Robot> robotList = rateResponse.getBody().stream()
                .sorted(Comparator.comparing(Robot::getCategory)
                        .thenComparing(Robot::getModel))
                .collect(Collectors.toList());

        return robotList != null ? robotList : new ArrayList<>();
    }

    @Override
    public Survivor getSurvivor(Long id) {
        Optional<Survivor> survivorNow = survivorRepository.findById(id);
        Survivor alive = null;
        if(survivorNow.isPresent()){
            alive = survivorNow.get();
        }
        return alive;
    }

    @Override
    public Survivor reportSurvivorAsInfected(Long id) {
        Optional<Survivor> survivorNow  = survivorRepository.findById(id);
        Survivor infected = null;
        if(survivorNow.isPresent()){
            infected = survivorNow.get();
            infected.setInfected(true);
            return survivorRepository.save(infected);}
        return infected;
    }

    @Override
    public Survivor reportSurvivorAsNotInfected(Long id) {
        Optional<Survivor> survivorNow  = survivorRepository.findById(id);
        Survivor infected = null;
        if(survivorNow.isPresent()){
            infected = survivorNow.get();
            infected.setInfected(false);
            return survivorRepository.save(infected);}
        return infected;
    }

    @Override
    public void updateSurvivorLocation(Long id, Location location) {
        Optional <Survivor> survivor = survivorRepository.findById(id);
        Location  survivorLocation = survivor.get().getLocation();
        survivor.get().setLocation(location);
        locationRepository.save(survivorLocation);
    }


    @Override
    public Map<String, String> findInfectionRatio() {
        Long positiveInfectionTest;
        Long negativeInfectionTest;
        Long population;

        Map<String,String> ratioTest = new HashMap<>();
        List<Survivor> allSurvivors = survivorRepository.findAll();

        if(allSurvivors.isEmpty()==false){
            population = allSurvivors.stream().count();
            positiveInfectionTest = allSurvivors.stream().filter(survivor -> survivor.isInfected()).count();
            negativeInfectionTest = population - positiveInfectionTest;

            ratioTest.put("Positive Test Cases For Survivors",
                    String.valueOf((positiveInfectionTest/population)*100)+"%");
            ratioTest.put("Negative Test Cases For Survivors",
                    String.valueOf((negativeInfectionTest/population)*100)+"%");

        }

        return ratioTest;
    }

    @Override
    public List<Survivor> negativeInfectionTestResult() {
        List<Survivor> survivors = survivorRepository.findAll();
        List<Survivor> notInfected = null;
        if (survivors.isEmpty()==false){
            notInfected = survivors
                    .stream()
                    .filter(survivor -> !survivor.isInfected())
                    .collect(Collectors.toList());
        }
        return notInfected;
    }
    @Override
    public List<Survivor> positiveInfectionTestResult() {
        List<Survivor> survivors = survivorRepository.findAll();
        List<Survivor> Infected = null;
        if (survivors.isEmpty()==false){
            Infected = survivors
                    .stream()
                    .filter(survivor -> survivor.isInfected())
                    .collect(Collectors.toList());
        }
        return Infected;
    }





}
