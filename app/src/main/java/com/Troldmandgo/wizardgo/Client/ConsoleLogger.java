package com.Troldmandgo.wizardgo.Client;

import android.util.Log;

public class ConsoleLogger implements Logger {
    @Override
    public void printMessage(String message) {
        Log.i("TAG",message);
    }

    @Override
    public void printError(Exception e) {
        Log.e("TAG",e.getMessage() + e.getStackTrace().toString());
    }
}
