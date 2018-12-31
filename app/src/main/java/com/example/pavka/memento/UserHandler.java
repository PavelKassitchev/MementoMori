package com.example.pavka.memento;

public interface UserHandler {
    public void saveUser(User user);
    public User obtainUser();
    public double calculateLifeSpan(User user);

}
