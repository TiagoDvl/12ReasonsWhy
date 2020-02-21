package br.com.tiagodavila.twelvereasonswhy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Month9SequenceAdapter(private val sequenceItems: List<String>,
                            private val callback:(position: Int) -> Unit): RecyclerView.Adapter<SequenceAdapter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SequenceAdapter {
        return SequenceAdapter(LayoutInflater.from(parent.context).inflate(R.layout.month_9_item, parent, false))
    }

    override fun getItemCount(): Int {
        return sequenceItems.size
    }

    override fun onBindViewHolder(holder: SequenceAdapter, position: Int) {
        holder.sequenceText.text = sequenceItems[position]
        holder.holder.setOnClickListener{ view ->
            view.setBackgroundColor(view.context.resources.getColor(R.color.applicationBlue))
            callback.invoke(position)
        }
    }

}

class SequenceAdapter(view: View): RecyclerView.ViewHolder(view) {

    val holder: View = view.findViewById(R.id.month_9_item_holder)
    val sequenceText: TextView = view.findViewById(R.id.month_9_sequence_text)

}