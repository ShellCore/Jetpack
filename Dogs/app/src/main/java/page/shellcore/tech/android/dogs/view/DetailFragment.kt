package page.shellcore.tech.android.dogs.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_detail.*
import page.shellcore.tech.android.dogs.R
import page.shellcore.tech.android.dogs.util.getProgressDrawable
import page.shellcore.tech.android.dogs.util.loadImage
import page.shellcore.tech.android.dogs.viewmodel.DetailViewModel

class   DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProvider(this)
            .get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let { dogData ->
                detailTxtName.text = dogData.dogBreed
                detailTxtPurpose.text = dogData.bredFor
                detailTxtTemperament.text = dogData.temperament
                detailTxtLifespan.text = dogData.lifeSpan
                context?.let {
                    detailImgDog.loadImage(dogData.imageUrl, getProgressDrawable(it))
                }
            }
        })
    }
}
