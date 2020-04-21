package page.shellcore.tech.android.dogs.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_list.*
import page.shellcore.tech.android.dogs.R

class ListFragment : Fragment(R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDetails.setOnClickListener {
            val action = ListFragmentDirections.actionDetailFragment()
            Navigation.findNavController(it)
                .navigate(action)
        }
    }

}
