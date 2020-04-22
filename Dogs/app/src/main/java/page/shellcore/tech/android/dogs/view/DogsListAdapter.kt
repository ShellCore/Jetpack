package page.shellcore.tech.android.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import page.shellcore.tech.android.dogs.R
import page.shellcore.tech.android.dogs.model.DogBreed

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
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dog, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dogs[position])

    override fun getItemCount() = dogs.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val imgItemDog = view.imgItemDog
        private val txtDogName = view.txtItemName
        private val txtDogLifespan = view.txtItemLifespan

        fun bind(dog: DogBreed) {
            txtDogName.text = dog.dogBreed
            txtDogLifespan.text = dog.lifeSpan
            view.setOnClickListener {
                val action = ListFragmentDirections.actionDetailFragment()
                Navigation.findNavController(it)
                    .navigate(action)
            }
        }
    }
}