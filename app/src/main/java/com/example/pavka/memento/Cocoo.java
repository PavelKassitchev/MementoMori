package com.example.pavka.memento;

import com.example.pavka.memento.LifeSpanCalculator;
import com.example.pavka.memento.Questions;
import com.example.pavka.memento.User;

import java.time.LocalDate;
import java.util.Date;

public class Cocoo implements LifeSpanCalculator {
    @Override
    public double getLifeSpan(User user) {
        int gender = user.getGender();
        Date birthDate = user.getBirthDate();
        Date today = new Date();
        double ageInYears = (today.getTime() - birthDate.getTime()) / (1000 * 3600 * 24 * 365.25);
        double defaultMaleLifeSpan = 0.00007 * Math.pow(ageInYears, 3) - 0.0037 * Math.pow(ageInYears, 2) + 0.1131 * ageInYears + 68.583;
        double defaultFemaleLifeSpan = 0.0000624 * Math.pow(ageInYears, 3) - 0.00532 * Math.pow(ageInYears, 2) + 0.157 * ageInYears + 77.953;

        switch (gender) {
            case User.MALE: return defaultMaleLifeSpan;
            case User.FEMALE: return defaultFemaleLifeSpan;
            default: return (defaultFemaleLifeSpan + defaultMaleLifeSpan) / 2;
        }
    }
}
