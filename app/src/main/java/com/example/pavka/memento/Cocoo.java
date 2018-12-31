package com.example.pavka.memento;


import java.util.Date;

public class Cocoo implements LifeSpanCalculator {

    public final double AVG_MALE = 68.583;
    public final double AVG_FEMALE = 77.953;

    private User user;

    public Cocoo(User user) {
        this.user = user;
    }
    @Override
    public double getCurrentAge() {
        Date birthDate = user.getBirthDate();
        Date today = new Date();
        double currentAge = (today.getTime() - birthDate.getTime()) / (1000 * 3600 * 24 * 365.25);
        return currentAge;
    }

    @Override
    public double getCorrection() {
        double correction = 0;
        for (int i=0; i<Questions.getLength(); i++) {
            switch (user.getUserData()[i]) {
                case -1:
                    correction += Questions.getNegatives()[i];
                    break;
                case 1:
                    correction += Questions.getPositives()[i];
                    break;
                default:
                    continue;
            }
        }
        return correction;
    }

    @Override
    public double getLifeSpan() {
        int gender = user.getGender();
        double ageInYears = getCurrentAge();
        double defaultMaleLifeSpan = 0.00007 * Math.pow(ageInYears, 3) - 0.0037 * Math.pow(ageInYears, 2) + 0.1131 * ageInYears + AVG_MALE;
        double defaultFemaleLifeSpan = 0.0000624 * Math.pow(ageInYears, 3) - 0.00532 * Math.pow(ageInYears, 2) + 0.157 * ageInYears + AVG_FEMALE;

        switch (gender) {
            case AndroidUser.MALE: return defaultMaleLifeSpan;
            case AndroidUser.FEMALE: return defaultFemaleLifeSpan;
            default: return (defaultFemaleLifeSpan + defaultMaleLifeSpan) / 2;
        }
    }
}
