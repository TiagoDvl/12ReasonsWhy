package br.com.tiagodavila.twelvereasonswhy


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth5Binding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class Month5Fragment : BaseFragment() {

    var validationCode = 0

    private var _binding: FragmentMonth5Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonth5Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.month5ValidateGuesses.setOnClickListener {
            checkAllGuesses().let {
                binding.month5ValidatePhrase.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    sendSMS("918528079", generateValidationCode())
                    Snackbar.make(view, "ARRETOU, BB!!!", Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(view, "ERRROOOOOOOUUU!!!", Snackbar.LENGTH_LONG).show()
                }
            }

        }

        binding.month5ValidatePhrase.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (p0.toString().equals(validationCode.toString(), true)) {
                        val builder = AlertDialog.Builder(context!!)
                        builder.setTitle("Feliz 5 meses!")
                        builder.setMessage("Obrigado pelo companheirismo, paciência e amor. A vida tem sido incrível contigo!")
                        builder.setPositiveButton("Ok") { dialogInterface, i ->
                            dialogInterface.dismiss()
                            fragmentManager?.popBackStack()
                        }

                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                }
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun generateValidationCode(): String {
        validationCode = Random.nextInt(100, 999)
        Log.d("Tiago", "Oi viado. Eu te amo muito. Usa esse código aqui: $validationCode")
        return "Your validation code is: $validationCode"
    }

    private fun checkAllGuesses(): Boolean {
        with(binding) {
            val guess1 = month5Guess1.text.toString()
            val guess2 = month5Guess2.text.toString()
            val guess3 = month5Guess3.text.toString()
            val guess4 = month5Guess4.text.toString()
            val guess5 = month5Guess5.text.toString()

            val rightGuess1 = resources.getString(R.string.month_5_right_guess_1)
            val rightGuess2 = resources.getString(R.string.month_5_right_guess_2)
            val rightGuess3 = resources.getString(R.string.month_5_right_guess_3)
            val rightGuess4 = resources.getString(R.string.month_5_right_guess_4)
            val rightGuess5 = resources.getString(R.string.month_5_right_guess_5)

            return guess1.equals(rightGuess1, true) and
                    guess2.equals(rightGuess2, true) and
                    guess3.equals(rightGuess3, true) and
                    guess4.equals(rightGuess4, true) and
                    guess5.equals(rightGuess5, true)
        }
    }

    override fun onResume() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.SEND_SMS),
                0
            )
        }
        super.onResume()
    }

    private fun sendSMS(phoneNo: String, msg: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
