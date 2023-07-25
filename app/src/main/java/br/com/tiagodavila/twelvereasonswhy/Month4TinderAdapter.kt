package br.com.tiagodavila.twelvereasonswhy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Month4TinderAdapter(var tinderObjects: MutableList<TinderObject>) :
    RecyclerView.Adapter<Month4TinderAdapter.TinderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tinder_object_item, parent, false)
        return TinderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tinderObjects.size
    }

    override fun onBindViewHolder(holder: TinderViewHolder, position: Int) {
        val tinderObject = tinderObjects[position]
        holder.image.setImageDrawable(tinderObject.image)
        holder.name.text = tinderObject.name

    }

    fun removeTopItem() {
        tinderObjects.removeAt(0)
        notifyDataSetChanged()
    }

    inner class TinderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.tinder_image)
        val name: TextView = itemView.findViewById(R.id.tinder_text)
    }
}