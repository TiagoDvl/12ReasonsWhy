package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ravelin.card_encryption.RavelinEncrypt
import com.ravelin.card_encryption.callback.EncryptCallback
import com.ravelin.card_encryption.model.CardDetails
import com.ravelin.card_encryption.model.EncryptError
import com.ravelin.card_encryption.model.EncryptedCard
import com.ravelin.core.RavelinSDK
import com.ravelin.core.callback.RavelinCallback
import com.ravelin.core.callback.RavelinRequestCallback
import com.ravelin.core.model.RavelinError
import kotlinx.android.synthetic.main.fragment_month11.*


class Month11Fragment : BaseFragment() {

    var stateMachineStep = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_month11, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        month_11_yes.setOnClickListener {
            when (stateMachineStep) {
                0 -> showTask()
            }
        }

        month_11_no.setOnClickListener {
            when (stateMachineStep) {
                0 -> showDeny()
            }
        }

        month_11_tom_nook.setOnLongClickListener {
            month_11_dialog_text.text = "SUCCESS"
            true
        }

        val cardDetails = CardDetails("123451234512345", "11", "2020", "Mr Super User")

        val ravelinEncrypt = RavelinEncrypt()
        ravelinEncrypt.encryptCard(cardDetails, "YOUR RSA KEY",
            object : EncryptCallback<EncryptedCard>() {
                override fun success(result: EncryptedCard?) {
                    println("1 - Encrypt" + result?.cardCiphertext)
                    // Your new encrypted card.
                }

                override fun failure(error: EncryptError) {
                    println(error)
                    // Something went wrong.
                }
            })

        cardDetails.validate(object: EncryptCallback<CardDetails>() {
            override fun failure(error: EncryptError) {
                println(error)
            }

            override fun success(result: CardDetails?) {
                println("2 - Validate success")
            }
        })

        val ravelinSDK = RavelinSDK.createInstance(
            requireActivity().application,
            "publishable_key_live_QGSC7N3NPPtmVgvcagx0jQNW0gGwDb7a",
            object: RavelinCallback<RavelinSDK>() {
                override fun failure(error: RavelinError) {
                    println(error)
                }

                override fun success(result: RavelinSDK?) {
                    println("3 - SDK Instantiation")
                }

            })

        val page = "Page Test"
        val customer = "abc123"
        val payload = mapOf("Class" to "SDK")
        ravelinSDK?.apply {
            trackLogIn(customer, page, payload, getCallback(4))
            trackLogOut(page, payload, getCallback(5))
            trackFingerprint(getCallback(6))
            trackSelectOption(page, "option", "value", getCallback(7))
            trackAddToCart(page, "item", 4, getCallback(8))
            trackAddToWishlist(page, "item", getCallback(9))
            trackEvent("A1234", page, payload, getCallback(10))
            trackPage(page, payload, getCallback(11))
            trackPaste(page, "Misto", getCallback(12))
            trackRemoveFromCart(page, "item", 4, getCallback(13))
            trackRemoveFromWishlist(page, "item", getCallback(14))
            trackSearch(page, "search", getCallback(15))
            trackViewContent(page, "content", getCallback(16))
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun getCallback(number: Int): RavelinRequestCallback {
        return object: RavelinRequestCallback() {
            override fun failure(error: RavelinError) {
                println(error.message + " " + number.toString())
            }

            override fun success() {
                println(number.toString())
            }

        }
    }

    private fun showDeny() {
        Toast.makeText(context, "Dsclp não ser suficiente :(", Toast.LENGTH_SHORT).show()
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun showTask() {
        month_11_dialog_text.text = getString(R.string.month_11_animal_task)
        month_11_buttons.visibility = View.GONE
        month_11_choice_holder.visibility = View.VISIBLE
    }

}
