package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonthHolderBinding


class MonthHolderFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentMonthHolderBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonthHolderBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.month1.setOnClickListener(this)
        binding.month2.setOnClickListener(this)
        binding.month3.setOnClickListener(this)
        binding.month4.setOnClickListener(this)
        binding.month5.setOnClickListener(this)
        binding.month6.setOnClickListener(this)
        binding.month7.setOnClickListener(this)
        binding.month8.setOnClickListener(this)
        binding.month9.setOnClickListener(this)
        binding.month10.setOnClickListener(this)
        binding.month11.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onMonthChosen(
            when (v?.id) {
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
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
