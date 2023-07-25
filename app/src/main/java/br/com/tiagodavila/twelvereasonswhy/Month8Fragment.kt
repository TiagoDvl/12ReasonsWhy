package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth8Binding
import java.util.Calendar


class Month8Fragment : BaseFragment() {

    private var previousRotation = 0f
    private var currentRotation = 0f
    private val rotationFactor = -90f
    private var numberOfRotations = 0
    private val maximumNumberOfRotations = 4

    private var _binding: FragmentMonth8Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonth8Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clearPreviousMemories()
        binding.eighthMonthRotation.setOnClickListener {
            if (numberOfRotations < maximumNumberOfRotations) {
                clearPreviousMemories()
                when (numberOfRotations) {
                    0 -> inflateDaysTogether()
                    1 -> inflateNewYearsEve()
                    2 -> inflateGoodMorning()
                    3 -> inflateTweet()
                }

                rotateMonthNumber(it)

                if (numberOfRotations == 4) {
                    numberOfRotations = 0
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun inflateDaysTogether() {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()

        cal1.set(2019, 5, 28)

        val millis1 = cal1.timeInMillis
        val millis2 = cal2.timeInMillis

        val diff = millis2 - millis1
        val diffDays = diff / (24 * 60 * 60 * 1000)

        binding.firstTurnDaysCounter.text =
            resources.getString(R.string.month_8_days_counter, diffDays.toString())
        binding.firstTurnDaysCounter.visibility = View.VISIBLE
    }

    private fun inflateNewYearsEve() {
        binding.secondTurnFamily.visibility = View.VISIBLE
    }

    private fun inflateGoodMorning() {
        binding.thirdTurnGoodMorning.visibility = View.VISIBLE
    }

    private fun inflateTweet() {
        binding.fourthTurnTwitter.visibility = View.VISIBLE
    }

    private fun clearPreviousMemories() {
        with(binding) {
            firstTurnDaysCounter.visibility = View.GONE
            secondTurnFamily.visibility = View.GONE
            thirdTurnGoodMorning.visibility = View.GONE
            fourthTurnTwitter.visibility = View.GONE
        }
    }

    override fun onResume() {
        binding.singleTapAnimation.playAnimation()
        super.onResume()
    }

    private fun rotateMonthNumber(it: View) {
        previousRotation = currentRotation
        currentRotation += rotationFactor
        val rotateAnimation = RotateAnimation(
            previousRotation,
            currentRotation,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 1000
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = LinearInterpolator()

        it.startAnimation(rotateAnimation)
        numberOfRotations += 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
