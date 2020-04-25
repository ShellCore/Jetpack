package page.shellcore.tech.android.dogs.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import page.shellcore.tech.android.dogs.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
