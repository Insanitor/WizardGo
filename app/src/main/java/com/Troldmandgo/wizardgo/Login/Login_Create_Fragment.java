package com.Troldmandgo.wizardgo.Login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Troldmandgo.wizardgo.MailValidator;
import com.Troldmandgo.wizardgo.R;

public class Login_Create_Fragment extends Fragment {
    private static final String TAG = "CreateUserPage";

    TextView btnCreateAccount;
    EditText emailInputText;
    EditText emailConfirmInputText;
    EditText passwordInputText;
    EditText passwordConfirmText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_create, container, false);

        btnCreateAccount = (TextView) view.findViewById(R.id.createAccountButton);
        emailInputText = (EditText) view.findViewById(R.id.emailCreateInputField);
        emailConfirmInputText = (EditText) view.findViewById(R.id.confirmEmailCreateInputField);
        passwordInputText = (EditText) view.findViewById(R.id.newPasswordInputField);
        passwordConfirmText = (EditText) view.findViewById(R.id.confirmPasswordInputField);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                if (MailValidator.isValidEmail(emailInputText.getText())) {
                    String email = emailInputText.getText().toString();
                    String confirmEmail = emailConfirmInputText.getText().toString();
                    if (email.equals(confirmEmail)) {
                        String pass = passwordInputText.getText().toString();
                        String conPass = passwordConfirmText.getText().toString();
                        if (pass.equals(conPass) && !pass.equals("")) {
                            Toast.makeText(getActivity(), "Created New User", Toast.LENGTH_SHORT).show();
                            ((Login_Activity) getActivity()).setViewPager(0);
                        } else {
                            Toast.makeText(getActivity(), passwordInputText.getText() + "Passwords doesn't match" + passwordConfirmText.getText(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Emails doesn't match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
