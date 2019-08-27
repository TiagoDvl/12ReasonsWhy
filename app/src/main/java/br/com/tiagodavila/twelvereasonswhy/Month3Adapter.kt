package br.com.tiagodavila.twelvereasonswhy

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.travel_memory_item.view.*


class Month3Adapter(var listOfTravelMemories: Array<TravelMemories>) :
    RecyclerView.Adapter<Month3Adapter.TravelMemoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelMemoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.travel_memory_item, parent, false)
        return TravelMemoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfTravelMemories.size
    }

    override fun onBindViewHolder(holder: TravelMemoriesViewHolder, position: Int) {
        val travelMemories = listOfTravelMemories[position]
        holder.title.text = travelMemories.title
        holder.image.setImageDrawable(travelMemories.image)
        holder.clickHolder.setOnClickListener {
            val gmmIntentUri = Uri.parse(
                "geo:${travelMemories.latitude},${travelMemories.longitude}?z=18")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(holder.image.context.packageManager) != null) {
                startActivity(holder.image.context, mapIntent, null)
            }
        }
    }

    inner class TravelMemoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.travel_memory_text
        val image: ImageView = itemView.travel_memory_image
        val clickHolder: ViewGroup = itemView.travel_memory_holder

    }
}