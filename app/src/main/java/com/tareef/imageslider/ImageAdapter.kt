package com.tareef.imageslider

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tareef.imageslider.data.Image
import com.tareef.imageslider.databinding.LayoutItemImageBinding

class ImageAdapter(
    val images: List<Image>
): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemImageBinding.inflate(layoutInflater, parent , false
        )

        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.ivImage.setImageResource(image.id)
    }

    class ImageViewHolder(val binding: LayoutItemImageBinding): RecyclerView.ViewHolder(binding.root)
}