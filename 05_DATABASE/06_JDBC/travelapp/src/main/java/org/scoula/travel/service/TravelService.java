package org.scoula.travel.service;

import org.scoula.travel.domain.TravelVO;

import java.util.List;

public interface TravelService {

    void printTravels();

    void printTravelsByDistrict();

    void printTravelsByPage();

    void printTravel();

}
