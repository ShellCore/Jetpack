package page.shellcore.tech.android.dogs.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_detail.*
import page.shellcore.tech.android.dogs.R
import page.shellcore.tech.android.dogs.viewmodel.DetailViewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(DetailViewModel::class.java)
        viewModel.fetch()

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                detailTxtName.text = it.dogBreed
                detailTxtPurpose.text = it.bredFor
                detailTxtTemperament.text = it.temperament
                detailTxtLifespan.text = it.lifeSpan

            }
        })
    }
}
