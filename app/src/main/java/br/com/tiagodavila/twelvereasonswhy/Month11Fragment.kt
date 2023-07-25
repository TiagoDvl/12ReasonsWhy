package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth11Binding


class Month11Fragment : BaseFragment() {

    var stateMachineStep = 0

    private var _binding: FragmentMonth11Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonth11Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.month11Yes.setOnClickListener {
            when (stateMachineStep) {
                0 -> showTask()
            }
        }

        binding.month11No.setOnClickListener {
            when (stateMachineStep) {
                0 -> showDeny()
            }
        }

        binding.month11TomNook.setOnLongClickListener {
            binding.month11DialogText.text = "SUCCESS"
            true
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showDeny() {
        Toast.makeText(context, "Dsclp não ser suficiente :(", Toast.LENGTH_SHORT).show()
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun showTask() {
        with(binding) {
            month11DialogText.text = getString(R.string.month_11_animal_task)
            month11Buttons.visibility = View.GONE
            month11ChoiceHolder.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
