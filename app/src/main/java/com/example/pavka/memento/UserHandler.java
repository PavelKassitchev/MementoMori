package com.example.pavka.memento;

import java.util.Date;

public interface UserHandler {
    void saveUser(User user) throws Exception;
    User obtainUser() throws Exception;
    User cleanUser();
    void saveUserAnswers(int[] answers) throws Exception;
    int[] obtainUserAnswers() throws  Exception;
    double calculateLifeSpan(User user);
    Date getLastDate(User user);

}
