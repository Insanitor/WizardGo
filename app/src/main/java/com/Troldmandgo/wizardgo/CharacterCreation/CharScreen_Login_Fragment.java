package com.Troldmandgo.wizardgo.CharacterCreation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.Troldmandgo.wizardgo.Login.Login_Activity;
import com.Troldmandgo.wizardgo.R;


public class CharScreen_Login_Fragment extends Fragment {

    Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charscreen_select, container, false);

        btnLogout = (Button) view.findViewById(R.id.logoutButton);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Toast.makeText(getActivity(), "Login Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), Login_Activity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
