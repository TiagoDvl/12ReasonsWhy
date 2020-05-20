package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_month_holder.*


class MonthHolderFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month_holder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        month_1.setOnClickListener(this)
        month_2.setOnClickListener(this)
        month_3.setOnClickListener(this)
        month_4.setOnClickListener(this)
        month_5.setOnClickListener(this)
        month_6.setOnClickListener(this)
        month_7.setOnClickListener(this)
        month_8.setOnClickListener(this)
        month_9.setOnClickListener(this)
        month_10.setOnClickListener(this)
        month_11.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onMonthChosen(when (v?.id) {
            R.id.month_1 -> Month1Fragment()
            R.id.month_2 -> Month2Fragment()
            R.id.month_3 -> Month3Fragment()
            R.id.month_4 -> Month4Fragment()
            R.id.month_5 -> Month5Fragment()
            R.id.month_6 -> Month6Fragment()
            R.id.month_7 -> Month7Fragment()
            R.id.month_8 -> Month8Fragment()
            R.id.month_9 -> Month9Fragment()
            R.id.month_10 -> Month10Fragment()
            R.id.month_11 -> Month11Fragment()
            else -> Month1Fragment()
        })
    }
}
