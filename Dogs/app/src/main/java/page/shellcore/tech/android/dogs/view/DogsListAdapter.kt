package page.shellcore.tech.android.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import page.shellcore.tech.android.dogs.R
import page.shellcore.tech.android.dogs.databinding.ItemDogBinding
import page.shellcore.tech.android.dogs.model.DogBreed
import page.shellcore.tech.android.dogs.util.getProgressDrawable
import page.shellcore.tech.android.dogs.util.loadImage

class DogsListAdapter : RecyclerView.Adapter<DogsListAdapter.ViewHolder>() {

    private val dogs = arrayListOf<DogBreed>()

    fun setDogList(newDogList: List<DogBreed>) {
        dogs.apply {
            clear()
            addAll(newDogList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_dog,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dogs[position])

    override fun getItemCount() = dogs.size

    class ViewHolder(val view: ItemDogBinding) : RecyclerView.ViewHolder(view.root), DogClickListener {

        fun bind(dog: DogBreed) {
            view.dog = dog
            view.listener = this
        }

        override fun onDogClicked(v: View) {
            val uuid = v.txtDogId.text.toString().toInt()
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid = uuid
            Navigation.findNavController(v)
                .navigate(action)
        }
    }
}