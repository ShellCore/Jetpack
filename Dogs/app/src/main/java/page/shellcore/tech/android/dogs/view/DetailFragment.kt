package page.shellcore.tech.android.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_detail.*
import page.shellcore.tech.android.dogs.R
import page.shellcore.tech.android.dogs.databinding.FragmentDetailBinding
import page.shellcore.tech.android.dogs.util.getProgressDrawable
import page.shellcore.tech.android.dogs.util.loadImage
import page.shellcore.tech.android.dogs.viewmodel.DetailViewModel

class   DetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailBinding

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

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
                dataBinding.dog = dogData
            }
        })
    }
}
