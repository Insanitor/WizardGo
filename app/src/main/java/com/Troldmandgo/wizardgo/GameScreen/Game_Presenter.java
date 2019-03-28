package com.Troldmandgo.wizardgo.GameScreen;

import android.util.Log;

import com.Troldmandgo.wizardgo.Client.NetworkPresenter;
import com.Troldmandgo.wizardgo.Helpers.LocationDataSet;

import java.util.ArrayList;

public class Game_Presenter implements NetworkPresenter {

    View view;

    public Game_Presenter(View view) {
        this.view = view;
    }

    @Override
    public void onOwnCharacterChanged() {
        view.onOwnCharacterChanged();
    }

    @Override
    public void onCharacterChanged(long enjoyerId, long characterId) {
        view.onCharacterChanged(enjoyerId,characterId);
    }

    @Override
    public void onVisibilityChanged() {
        view.onVisibilityChanged();
    }

    @Override
    public void onSignin() {
        view.onSignin();
    }

    @Override
    public void onLocationsReceived(ArrayList<LocationDataSet> locationData) {
        Log.e("TAG2", "Inside Presenter");
        view.onLocationsReceived(locationData);
    }

    @Override
    public void onPlayerJoin(LocationDataSet enjoyer) {
        view.onPlayerJoin(enjoyer);
    }

    @Override
    public void onPlayerLeave(long enjoyerId) {
        view.onPlayerLeave(enjoyerId);
    }

    public interface View {
        public void onOwnCharacterChanged();

        public void onCharacterChanged(long enjoyerId, long characterId);

        public void onVisibilityChanged();

        public void onSignin();

        public void onLocationsReceived(ArrayList<LocationDataSet> locationData);

        public void onPlayerJoin(LocationDataSet enjoyer);

        public void onPlayerLeave(long enjoyerId);
    }
}
