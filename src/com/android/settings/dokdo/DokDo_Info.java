package com.android.settings.dokdo;

import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.android.settings.R;

public class DokDo_Info extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.dokdo_info);

	}
}
