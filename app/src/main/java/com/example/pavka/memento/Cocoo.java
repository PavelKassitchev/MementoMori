package com.example.pavka.memento;


//Life span calculator interface
public interface Cocoo {
    //default just-born male life span
    double AVG_MALE = 68.583;
    //default just-borne female life span
    double AVG_FEMALE = 77.953;
    //milliseconds in 1 year
    double MILLIS_IN_YEAR = 1000 * 3600 * 24 * 365.25;
    double getLifeSpan();
    double getCurrentAge();
    double getCorrection();
}
