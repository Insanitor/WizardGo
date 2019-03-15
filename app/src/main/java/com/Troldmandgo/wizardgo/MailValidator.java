package com.Troldmandgo.wizardgo;

import android.text.TextUtils;

public class MailValidator {

    //Used to Validate that given CharSequence is a valid Email
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
