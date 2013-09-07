/*
 * Copyright (C) 2012 Slimroms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.cyanogenmod.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import com.android.settings.cyanogenmod.SeekBarPreference;

import com.android.settings.cyanogenmod.colorpicker.ColorPickerPreference;

public class TransparencySettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String TAG = "TransparencySettings";
    private static final String PREF_STATUS_BAR_ALPHA = "status_bar_alpha";
    private static final String PREF_STATUS_BAR_ALPHA_LOCKSCREEN = "status_bar_alpha_lockscreen";
    private static final String PREF_NAV_BAR_ALPHA = "nav_bar_alpha";
    private static final String PREF_NAV_BAR_ALPHA_LOCKSCREEN = "nav_bar_alpha_lockscreen";

    private SeekBarPreference mStatusbarTransparency;
    private SeekBarPreference mStatusbarLockTransparency;
    private SeekBarPreference mNavBarTransparency;
    private SeekBarPreference mNavBarLockTransparency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshSettings();
    }

    public void refreshSettings() {
        PreferenceScreen prefs = getPreferenceScreen();
        if (prefs != null) {
            prefs.removeAll();
        }

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.transparency_settings);

        prefs = getPreferenceScreen();

        float statBarTransparency = 0.0f;
        try{
            statBarTransparency = Settings.System.getFloat(getActivity()
                 .getContentResolver(), Settings.System.STATUS_BAR_ALPHA);
        } catch (Exception e) {
            statBarTransparency = 0.0f;
            Settings.System.putFloat(getActivity().getContentResolver(), Settings.System.STATUS_BAR_ALPHA, 0.0f);
        }
        mStatusbarTransparency = (SeekBarPreference) findPreference(PREF_STATUS_BAR_ALPHA);
        mStatusbarTransparency.setProperty(Settings.System.STATUS_BAR_ALPHA);
        mStatusbarTransparency.setInitValue((int) (statBarTransparency * 100));
        mStatusbarTransparency.setOnPreferenceChangeListener(this);

        float statBarLockTransparency = 0.0f;
        try{
            statBarLockTransparency = Settings.System.getFloat(getActivity()
                 .getContentResolver(), Settings.System.STATUS_BAR_ALPHA_LOCKSCREEN);
        } catch (Exception e) {
            statBarLockTransparency = 0.0f;
            Settings.System.putFloat(getActivity().getContentResolver(), Settings.System.STATUS_BAR_ALPHA_LOCKSCREEN, 0.0f);
        }
        mStatusbarLockTransparency = (SeekBarPreference) findPreference(PREF_STATUS_BAR_ALPHA_LOCKSCREEN);
        mStatusbarLockTransparency.setProperty(Settings.System.STATUS_BAR_ALPHA_LOCKSCREEN);
        mStatusbarLockTransparency.setInitValue((int) (statBarLockTransparency * 100));
        mStatusbarLockTransparency.setOnPreferenceChangeListener(this);

        float navBarTransparency = 0.0f;
        try{
            navBarTransparency = Settings.System.getFloat(getActivity()
                 .getContentResolver(), Settings.System.NAVIGATION_BAR_ALPHA);
        } catch (Exception e) {
            navBarTransparency = 0.0f;
            Settings.System.putFloat(getActivity().getContentResolver(), Settings.System.NAVIGATION_BAR_ALPHA, 0.0f);
        }
        mNavBarTransparency = (SeekBarPreference) findPreference(PREF_NAV_BAR_ALPHA);
        mNavBarTransparency.setProperty(Settings.System.NAVIGATION_BAR_ALPHA);
        mNavBarTransparency.setInitValue((int) (navBarTransparency * 100));
        mNavBarTransparency.setOnPreferenceChangeListener(this);

        float navBarLockTransparency = 0.0f;
        try{
            navBarLockTransparency = Settings.System.getFloat(getActivity()
                 .getContentResolver(), Settings.System.NAVIGATION_BAR_ALPHA_LOCKSCREEN);
        } catch (Exception e) {
            navBarTransparency = 0.0f;
            Settings.System.putFloat(getActivity().getContentResolver(), Settings.System.NAVIGATION_BAR_ALPHA_LOCKSCREEN, 0.0f);
        }
        mNavBarLockTransparency = (SeekBarPreference) findPreference(PREF_NAV_BAR_ALPHA_LOCKSCREEN);
        mNavBarLockTransparency.setProperty(Settings.System.NAVIGATION_BAR_ALPHA_LOCKSCREEN);
        mNavBarLockTransparency.setInitValue((int) (navBarLockTransparency * 100));
        mNavBarLockTransparency.setOnPreferenceChangeListener(this);

        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.transparency_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                Settings.System.putFloat(getActivity().getContentResolver(),
                       Settings.System.STATUS_BAR_ALPHA, 0.0f);
                Settings.System.putFloat(getActivity().getContentResolver(),
                       Settings.System.STATUS_BAR_ALPHA_LOCKSCREEN, 0.0f);
                Settings.System.putFloat(getActivity().getContentResolver(),
                       Settings.System.NAVIGATION_BAR_ALPHA, 0.0f);
                Settings.System.putFloat(getActivity().getContentResolver(),
                       Settings.System.NAVIGATION_BAR_ALPHA_LOCKSCREEN, 0.0f);
                refreshSettings();
                return true;
             default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mStatusbarTransparency) {
            float valStat = Float.parseFloat((String) newValue);
            Settings.System.putFloat(getActivity().getContentResolver(),
                    Settings.System.STATUS_BAR_ALPHA,
                    valStat / 100);
            return true;
        } else if (preference == mStatusbarLockTransparency) {
            float valStat = Float.parseFloat((String) newValue);
            Settings.System.putFloat(getActivity().getContentResolver(),
                    Settings.System.STATUS_BAR_ALPHA_LOCKSCREEN,
                    valStat / 100);
            return true;
        } else if (preference == mNavBarTransparency) {
            float valStat = Float.parseFloat((String) newValue);
            Settings.System.putFloat(getActivity().getContentResolver(),
                    Settings.System.NAVIGATION_BAR_ALPHA,
                    valStat / 100);
            return true;
        } else if (preference == mNavBarLockTransparency) {
            float valStat = Float.parseFloat((String) newValue);
            Settings.System.putFloat(getActivity().getContentResolver(),
                    Settings.System.NAVIGATION_BAR_ALPHA_LOCKSCREEN,
                    valStat / 100);
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
