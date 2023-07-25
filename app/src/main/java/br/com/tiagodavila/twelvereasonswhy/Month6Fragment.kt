package br.com.tiagodavila.twelvereasonswhy


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth6Binding


class Month6Fragment : BaseFragment() {

    private val rewardsSequence = mutableListOf<String>()
    private var _binding: FragmentMonth6Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonth6Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.navalBattleCommands.addTextChangedListener(object : TextWatcher {

            private val navalItems = arrayOf<View>(
                binding.navalItem1,
                binding.navalItem2,
                binding.navalItem3,
                binding.navalItem4,

                binding.navalItem5,
                binding.navalItem6,
                binding.navalItem7,
                binding.navalItem8,

                binding.navalItem9,
                binding.navalItem10,
                binding.navalItem11,
                binding.navalItem12,

                binding.navalItem13,
                binding.navalItem14,
                binding.navalItem15,
                binding.navalItem16
            )

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().length > 2) {
                    p0?.clear()
                }

                if (p0?.length == 2) {
                    if (isValidCommand(p0.toString())) {
                        val placeToHide = Pair(p0[0].toString(), p0[1].toString())
                        hideChosenViewByCommend(navalItems, placeToHide)
                        val imm =
                            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        var viewKey = activity?.currentFocus
                        imm.hideSoftInputFromWindow(viewKey?.windowToken, 0)
                    } else {
                        Toast.makeText(
                            context,
                            "ERRRROOOU!!! SABE JOGAR ISSO NÃO?!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    p0.clear()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    private fun isValidCommand(command: String): Boolean {
        val validRowEntries = arrayOf("a", "b", "c", "d")
        var isValidRowEntry = false

        val validColumnEntries = arrayOf("1", "2", "3", "4")
        var isValidColumnEntry = false

        validRowEntries.forEach {
            if (command[0].toString() == it) {
                isValidRowEntry = true
            }
        }

        validColumnEntries.forEach {
            if (command[1].toString() == it) {
                isValidColumnEntry = true
            }
        }

        return isValidRowEntry && isValidColumnEntry
    }

    private fun hideChosenViewByCommend(navalItems: Array<View>, commandPair: Pair<String, String>) {

        when (commandPair.first) {
            "a" -> when (commandPair.second) {
                "1" -> navalItems[0].visibility = View.INVISIBLE
                "2" -> navalItems[1].visibility = View.INVISIBLE
                "3" -> navalItems[2].visibility = View.INVISIBLE
                "4" -> navalItems[3].visibility = View.INVISIBLE
            }

            "b" -> when (commandPair.second) {
                "1" -> {
                    navalItems[4].visibility = View.INVISIBLE
                    warnAndInform("Achou a boca! Ganhou um beijo!", "Um beijo")
                } // Boca
                "2" -> {
                    navalItems[5].visibility = View.INVISIBLE
                    warnAndInform("Achou a boca USB! Ganhou um jogo de lol!", "Um jogo de LoL")
                } // Boca usb
                "3" -> {
                    navalItems[6].visibility = View.INVISIBLE
                    warnAndInform("Achou a rola! Mais tarde a gente vê ;)", "A rola")
                } // Rola
                "4" -> {
                    navalItems[7].visibility = View.INVISIBLE
                    warnAndInform("Achou a boca! Ganhou um beijo!", "Um beijo")
                } // Boca
            }

            "c" -> when (commandPair.second) {
                "1" -> navalItems[8].visibility = View.INVISIBLE
                "2" -> {
                    navalItems[9].visibility = View.INVISIBLE
                    warnAndInform("Achou o coração! Ganhou um abraço!", "Um abraço")
                } // Coração
                "3" -> {
                    navalItems[10].visibility = View.INVISIBLE
                    warnAndInform("Achou o eu te amo!", "Eu te amo!")
                } // Amo você
                "4" -> navalItems[11].visibility = View.INVISIBLE
            }

            "d" -> when (commandPair.second) {
                "1" -> {
                    navalItems[12].visibility = View.INVISIBLE
                    warnAndInform("Achou o coração! Ganhou um abraço!", "Um abraço")
                } // Coração
                "2" -> navalItems[13].visibility = View.INVISIBLE
                "3" -> navalItems[14].visibility = View.INVISIBLE
                "4" -> navalItems[15].visibility = View.INVISIBLE
            }

            else -> binding.navalItem1.visibility = View.INVISIBLE
        }

        if (!isSomeNavalItemVisible(navalItems)) {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("tdavilas@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sexto mês - Premiações")
            emailIntent.putExtra(Intent.EXTRA_TEXT, rewardsSequence.toString())
            activity?.startActivity(Intent.createChooser(emailIntent, "Vai mandar por onde, querida?"))
        }
    }

    private fun warnAndInform(warning: String, information: String) {
        Toast.makeText(context, warning, Toast.LENGTH_LONG).show()
        rewardsSequence.add(information)
    }

    private fun isSomeNavalItemVisible(navalItems: Array<View>): Boolean {
        var areAllNavalItemsGone = false
        navalItems.forEach {
            if (it.visibility == View.VISIBLE) {
                areAllNavalItemsGone = true
                return@forEach
            }
        }

        return areAllNavalItemsGone
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
