package com.example.pavka.memento;

import java.util.Date;

public interface UserHandler {
    public void saveUser(User user) throws Exception;
    public User obtainUser() throws Exception;
    public double calculateLifeSpan(User user);
    public Date getLastDate(User user);
    public User cleanUser();

}
