package com.Troldmandgo.wizardgo.Client;

public interface NetworkModel {
    void changeCharacter(long characterId);
    void changeVisibility(boolean visibleToOthers);
    void signin(String email, String password);
}
