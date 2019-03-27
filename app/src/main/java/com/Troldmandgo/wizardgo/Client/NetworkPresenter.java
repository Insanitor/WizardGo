package com.Troldmandgo.wizardgo.Client;

import com.Troldmandgo.wizardgo.Helpers.LocationDataSet;

import java.util.ArrayList;

public interface NetworkPresenter {
    void onOwnCharacterChanged();
    void onCharacterChanged(long enjoyerId, long characterId);
    void onVisibilityChanged();
    void onSignin();
    void onLocationsReceived(ArrayList<LocationDataSet> locationData);
    void onPlayerJoin(long enjoyerId);
    void onPlayerLeave(long enjoyerId);
}
