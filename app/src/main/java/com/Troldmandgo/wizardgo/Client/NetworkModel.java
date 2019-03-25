package com.Troldmandgo.wizardgo.Client;

public interface NetworkModel {
    void changeCharacter(long characterId);
    void changeVisibility(boolean visibleToOthers);
    void signIn(String email, String password);
    void setLocation(float longitude, float latitude);
}
