package com.example.pavka.memento;

import java.util.Date;

public interface LifeSpanCalculator {
    public final double MILLIS_IN_YEAR = 1000 * 3600 * 24 * 365.25;
    public double getLifeSpan();
    public double getCurrentAge();
    public double getCorrection();
}
