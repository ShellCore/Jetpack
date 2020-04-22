package page.shellcore.tech.android.dogs.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import page.shellcore.tech.android.dogs.R

fun ImageView.loadImage(uri: String?) {
    Glide.with(this)
        .load(uri)
        .error(R.mipmap.ic_launcher)
        .into(this)
}