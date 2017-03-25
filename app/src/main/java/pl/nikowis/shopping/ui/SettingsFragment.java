package pl.nikowis.shopping.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import pl.nikowis.shopping.R;

/**
 * Created by Nikodem on 3/24/2017.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREF_FONT_SIZE = "pref_font_size";
    public static final String KEY_PREF_FONT_COLOR = "pref_font_color";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_FONT_SIZE)) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String newFontSize = sharedPref.getString(KEY_PREF_FONT_SIZE, "15");
            ShoppingAdapter.cardsFontSize = Integer.parseInt(newFontSize);
        } else if(key.equals(KEY_PREF_FONT_COLOR)) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String newFontColor = sharedPref.getString(KEY_PREF_FONT_COLOR, "#212121");
            ShoppingAdapter.cardsFontColor = newFontColor;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
