package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_month9.*


class Month9Fragment : BaseFragment() {

    private var currentSequence: MutableList<Int> = mutableListOf()
    private var rightSequence: MutableList<Int> =
        mutableListOf(3, 7, 2, 13, 6, 1, 4, 9, 0, 12, 8, 5, 11, 10)
    private var numberOfTries = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month9, container, false)
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
        month_9_recycler.apply {
            adapter = month9SequenceAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun gotAllRight() {
        month_9_recycler.visibility = View.GONE

        month_9_kiss_quantity.text = resources
            .getString(R.string.month_9_kiss_quantity, numberOfTries.toString())

        month_9_prize.visibility = View.VISIBLE
    }
}
