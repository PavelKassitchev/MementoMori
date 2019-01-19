package com.example.pavka.memento;


import java.util.Date;

//life span calculator for User user
public class CubicCuckoo implements Cuckoo {

    private User user;

    public CubicCuckoo(User user) {
        this.user = user;
    }
    @Override
    //returns the current user's age in years
    public double getCurrentAge() {
        Date birthDate = user.getBirthDate();
        Date today = new Date();
        return (today.getTime() - birthDate.getTime()) / MILLIS_IN_YEAR;
    }

    @Override
    //returns personal correction on INITIAL life span depending on the user's reply to questions
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
            }
        }
        return correction;
    }



    @Override
    //returns the expected REMAINING life span in years, depending on user's geneder and current age
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
