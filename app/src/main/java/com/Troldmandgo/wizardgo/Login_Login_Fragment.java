package com.Troldmandgo.wizardgo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment {

    TextView btnCreateUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_login, container, false);

        btnCreateUser = (TextView) view.findViewById(R.id.registerText);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Toast.makeText(getActivity(), "Create User Page", Toast.LENGTH_SHORT).show();
                ((LoginActivity) getActivity()).setViewPager(1);
            }
        });

        return view;
    }
}
