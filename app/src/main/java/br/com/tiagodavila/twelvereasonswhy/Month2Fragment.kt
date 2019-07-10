package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_month2.*


class Month2Fragment : BaseFragment() {

    private var numberOfVisitsBeforeIncrement: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        numberOfVisitsBeforeIncrement = getSharedPrefByKey(MONTH_2_NUMBER_OF_VISITS)
        return inflater.inflate(R.layout.fragment_month2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number_of_visits.text = getString(R.string.number_of_visits, incrementAndGetNumberOfVisits())
        number_of_visits_reaction.text = getReactionAfterVisits(numberOfVisitsBeforeIncrement)

        createAndPopulateAdapter()
    }

    private fun incrementAndGetNumberOfVisits(): String {
        val currentNumberOfVisits = numberOfVisitsBeforeIncrement.inc()
        setSharedPrefByKey(MONTH_2_NUMBER_OF_VISITS, currentNumberOfVisits)

        return getSharedPrefByKey(MONTH_2_NUMBER_OF_VISITS).toString()
    }

    private fun getReactionAfterVisits(numberOfVisits: Int): String {
        val reactionsStrings = resources.getStringArray(R.array.month_2_visits_reactions)
        return when (numberOfVisits) {
            in 0..4 -> reactionsStrings[0]
            in 5..9 -> reactionsStrings[1]
            in 10..18 -> reactionsStrings[2]
            19 -> reactionsStrings[3]
            20 -> reactionsStrings[4]
            else -> reactionsStrings[5]
        }
    }

    private fun createAndPopulateAdapter() {
        val adapter = Month2Adapter(
            mutableListOf(
                R.drawable.ai_kanji,
                R.drawable.family_lunch,
                R.drawable.ic_launcher_background)
        )
        view_pager.adapter = adapter
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private class Month2Adapter(private val myImages: MutableList<Int>): RecyclerView.Adapter<Month2Adapter.MonthHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.mont_2_item, parent, false)
            return MonthHolder(view)
        }

        override fun getItemCount(): Int {
            return myImages.size
        }

        override fun onBindViewHolder(holder: MonthHolder, position: Int) {
            val context = holder.image.context
            holder.image.background = context.getDrawable(myImages[position])
        }

        class MonthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var image: ImageView
            init {
                image = itemView.findViewById(R.id.month_2_vp_image)
            }

        }
    }
}
