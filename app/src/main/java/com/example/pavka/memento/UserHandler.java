package com.example.pavka.memento;

import java.util.Date;

public interface UserHandler {
    public void saveUser(User user);
    public User obtainUser();
    public double calculateLifeSpan(User user);
    public Date getLastDate(User user);

}
