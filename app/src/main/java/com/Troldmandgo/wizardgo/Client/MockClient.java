package com.Troldmandgo.wizardgo.Client;

import com.Troldmandgo.wizardgo.Helpers.LocationDataSet;

import java.util.ArrayList;
import java.util.Random;

public class MockClient extends Thread implements NetworkModel {
    NetworkPresenter presenter;
    Logger logger;

    ArrayList<LocationDataSet> clients = new ArrayList<>();
    int clientCount = 0;
    float[] bounds;

    boolean broadcast = true;
    final int broadcastInterval = 500;

    public MockClient(NetworkPresenter presenter, Logger logger, int clientCount, float[] clientBounds){
        //set implementations
        this.presenter = presenter;
        this.logger = logger;

        //create other 'clients'
        if(clientBounds.length >= 4){
            //assign bounds
            this.bounds = clientBounds;
            //create a random for generating a random starting point for our clients
            Random rnd = new Random();
            for(int i = 0; i < clientCount; i++){
                //get a starting position
                float lng = rnd.nextFloat() * (clientBounds[0] - clientBounds[1]) + clientBounds[0];
                float lat = rnd.nextFloat() * (clientBounds[2] - clientBounds[3]) + clientBounds[2];

                //make a location set for each client, representing a physical user
                LocationDataSet client = new LocationDataSet(i, lng, lat);
                //add clients to a list to loop through
                clients.add(client);
            }
        }
    }

    public LocationDataSet createClient(){
        Random rnd = new Random();

        //get a starting position
        float lng = rnd.nextFloat() * ( bounds[0] - bounds[1]) + bounds[0];
        float lat = rnd.nextFloat() * (bounds[2] - bounds[3]) + bounds[2];

        LocationDataSet c = new LocationDataSet(++clientCount, lng, lat);

        return c;
    }


    @Override
    public void run(){
        Random rnd = new Random();

        try{
            while(!this.isInterrupted()){
                //make sure the player has set broadcasting to true to see/send data from/to other players
                if(broadcast){
                    //change 'client' locations to simulate movement
                    for(int i = 0; i < clients.size(); i++){
                        //set new location
                        clients.get(i).setLocation(
                                clients.get(i).getLongitude() + (rnd.nextFloat() / 5),
                                clients.get(i).getLatitude() + (rnd.nextFloat() / 5));
                    }

                    //give location data to presenter to present stuff with
                    presenter.onLocationsReceived(clients);
                    //sleep to avoid constant spam
                    this.sleep(broadcastInterval);

                    //add and remove 'clients' to simulate active players joining and leaving the game
                    float chance = rnd.nextFloat();
                    if(chance > 0.97){
                        if(chance > 0.99){
                            int index = rnd.nextInt(clients.size()-1);
                            presenter.onPlayerLeave(clients.get(index).getEnjoyerId());
                            clients.remove(index);
                        }
                        else{
                            clients.add(createClient());
                            presenter.onPlayerJoin(clients.get(clients.size() - 1).getEnjoyerId());
                        }
                    }
                }
            }
        }
        catch(InterruptedException e){
            logger.printError(e);
        }
    }

    @Override
    public void changeCharacter(long characterId) {
        //only applies to actual multiplayer setups
    }

    @Override
    public void changeVisibility(boolean visibleToOthers) {
        this.broadcast = visibleToOthers;
    }

    @Override
    public void signIn(String email, String password) {
        //only applies to actual multiplayer setups
    }

    @Override
    public void setLocation(float longitude, float latitude) {
        //only applies to actual multiplayer setups
    }
}
