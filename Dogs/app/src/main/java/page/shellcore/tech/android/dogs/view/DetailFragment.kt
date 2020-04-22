package page.shellcore.tech.android.dogs.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail.*
import page.shellcore.tech.android.dogs.R

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var dogUuid = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }
    }
}
