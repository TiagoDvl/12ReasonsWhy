package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth9Binding


class Month9Fragment : BaseFragment() {

    private var currentSequence: MutableList<Int> = mutableListOf()
    private var rightSequence: MutableList<Int> = mutableListOf(
        3, 7, 2, 13, 6, 1, 4, 9, 0, 12, 8, 5, 11, 10
    )
    private var numberOfTries = 0

    private var _binding: FragmentMonth9Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonth9Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSequenceAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupSequenceAdapter() {
        numberOfTries ++
        val month9SequenceAdapter = Month9SequenceAdapter(
            resources.getStringArray(R.array.month_9_sequence_items).toList()
        ) {
            if (!currentSequence.contains(it)) {
                currentSequence.add(it)

                currentSequence.forEachIndexed { index, chosenPosition ->
                    if (rightSequence[index] != chosenPosition) {
                        Toast.makeText(
                            context,
                            "ERRRROOOOOU! Vai até conseguir!",
                            Toast.LENGTH_SHORT
                        ).show()
                        currentSequence.clear()
                        setupSequenceAdapter()
                    }

                    if (rightSequence.size == currentSequence.size) {
                        gotAllRight()
                    }
                }
            }

        }
        binding.month9Recycler.apply {
            adapter = month9SequenceAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun gotAllRight() {
        with(binding) {
            month9Recycler.visibility = View.GONE

            month9KissQuantity.text = resources
                .getString(R.string.month_9_kiss_quantity, numberOfTries.toString())

            month9Prize.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
