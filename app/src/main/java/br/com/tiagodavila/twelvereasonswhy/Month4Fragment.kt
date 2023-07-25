package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth4Binding
import swipeable.com.layoutmanager.OnItemSwiped
import swipeable.com.layoutmanager.SwipeableLayoutManager
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper
import kotlin.math.absoluteValue


class Month4Fragment : BaseFragment() {

    private lateinit var month4List: MutableList<TinderObject>
    private lateinit var rootView: View

    private var _binding: FragmentMonth4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonth4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        month4List = mutableListOf(
            TinderObject("Nuno", resources.getDrawable(R.drawable.nuno)),
            TinderObject("Helder", resources.getDrawable(R.drawable.helder)),
            TinderObject("Rita", resources.getDrawable(R.drawable.rita)),
            TinderObject("Tiago", resources.getDrawable(R.drawable.retiro_dont_touch_me)),
            TinderObject("Café - Noite de Loucura", resources.getDrawable(R.drawable.cafe1)),
            TinderObject("Café - Dormir até mais tarde", resources.getDrawable(R.drawable.cafe2)),
            TinderObject("Café - Um abraço", resources.getDrawable(R.drawable.cafe3)),
            TinderObject("Tiago", resources.getDrawable(R.drawable.retiro_dont_touch_me)),
            TinderObject("José", resources.getDrawable(R.drawable.jose))
        )

        val tinderObjectsCopy: MutableList<TinderObject> = ArrayList()
        tinderObjectsCopy.addAll(month4List)
        val tinderAdapter = Month4TinderAdapter(tinderObjectsCopy)
        val swipeTouchHelperCallback = SwipeableTouchHelperCallback(object : OnItemSwiped {
            override fun onItemSwiped() {
                tinderAdapter.removeTopItem()
                if (tinderAdapter.tinderObjects.size == 0) {
                    showLikes(month4List)
                    binding.presentAnimation.visibility = View.VISIBLE
                    binding.presentAnimation.playAnimation()
                }
            }

            override fun onItemSwipedLeft() {
                val currentPosition = (tinderAdapter.tinderObjects.size - 9).absoluteValue
                month4List[currentPosition].like = false
                showResponse(R.drawable.nope)
            }

            override fun onItemSwipedRight() {
                val currentPosition = (tinderAdapter.tinderObjects.size - 9).absoluteValue
                if (currentPosition == 3) {
                    month4List[currentPosition].like = false
                    showResponse(R.drawable.nope)
                } else {
                    month4List[currentPosition].like = true
                    showResponse(R.drawable.like)
                }
            }

            override fun onItemSwipedUp() {
                // Do nothing.
            }

            override fun onItemSwipedDown() {
                // Do nothing.
            }

        })
        val itemTouchHelper = ItemTouchHelper(swipeTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.month4TinderCards)
        binding.month4TinderCards.layoutManager = SwipeableLayoutManager()
        binding.month4TinderCards.adapter = tinderAdapter
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showLikes(month4List: MutableList<TinderObject>) {
        var reviewLinearLayout = rootView.findViewById<LinearLayout>(R.id.month_4_like_review)
        month4List.forEach {
            val textViewReview = TextView(context)
            val reviewText = if (it.like) "Like" else "Dislike"
            textViewReview.text = "Para ${it.name}, você deu: $reviewText"
            reviewLinearLayout.addView(textViewReview)
        }
    }

    private fun showResponse(image: Int) {
        binding.month4SwipeResponse.setImageDrawable(context?.let { ContextCompat.getDrawable(it, image) })
        binding.month4SwipeResponse.visibility = View.VISIBLE
        Handler().postDelayed({ binding.month4SwipeResponse.visibility = View.GONE }, 500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
