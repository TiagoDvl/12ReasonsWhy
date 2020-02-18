package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import kotlinx.android.synthetic.main.fragment_month8.*
import java.util.*


class Month8Fragment : BaseFragment() {

    private var previousRotation = 0f
    private var currentRotation = 0f
    private val rotationFactor = -90f
    private var numberOfRotations = 0
    private val maximumNumberOfRotations = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clearPreviousMemories()
        eighth_month_rotation.setOnClickListener {
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

        first_turn_days_counter.text =
            resources.getString(R.string.month_8_days_counter, diffDays.toString())
        first_turn_days_counter.visibility = View.VISIBLE
    }

    private fun inflateNewYearsEve() {
        second_turn_family.visibility = View.VISIBLE
    }

    private fun inflateGoodMorning() {
        third_turn_good_morning.visibility = View.VISIBLE
    }

    private fun inflateTweet() {
        fourth_turn_twitter.visibility = View.VISIBLE
    }

    private fun clearPreviousMemories() {
        first_turn_days_counter.visibility = View.GONE
        second_turn_family.visibility = View.GONE
        third_turn_good_morning.visibility = View.GONE
        fourth_turn_twitter.visibility = View.GONE
    }

    override fun onResume() {
        single_tap_animation.playAnimation()
        super.onResume()
    }

    private fun rotateMonthNumber(it: View) {
        previousRotation = currentRotation
        currentRotation += rotationFactor
        val rotateAnimation = RotateAnimation(
            previousRotation, currentRotation, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = LinearInterpolator()

        it.startAnimation(rotateAnimation)
        numberOfRotations += 1
    }

}
