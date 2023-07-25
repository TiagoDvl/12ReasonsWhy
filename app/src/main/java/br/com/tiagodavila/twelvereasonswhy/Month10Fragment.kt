package br.com.tiagodavila.twelvereasonswhy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth10Binding


class Month10Fragment : BaseFragment() {

    companion object {
        fun newInstance() = Month10Fragment()
    }

    private lateinit var viewModel: Month10ViewModel

    private lateinit var brazilStats: Stats
    private lateinit var portugalStats: Stats

    private var _binding: FragmentMonth10Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonth10Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Month10ViewModel::class.java)
        viewModel.callCoronaVirusApi("Brazil") { coronaResponse ->
            val stats = coronaResponse.data.covid19Stats[0]
            brazilStats = stats
            binding.month10BrazilConfirmed.text = "Confirmados: ${stats.confirmed}"
            binding.month10BrazilDeaths.text =  "Mortes: ${stats.deaths}"
            binding.month10BrazilRecovered.text =  "Recuperados: ${stats.recovered}"
        }

        viewModel.callCoronaVirusApi("Portugal") { coronaResponse ->
            val stats = coronaResponse.data.covid19Stats[0]
            portugalStats = stats
            binding.month10PortugalConfirmed.text = "Confirmados: ${stats.confirmed}"
            binding.month10PortugalDeaths.text = "Mortes: ${stats.deaths}"
            binding.month10PortugalRecovered.text = "Recuperados: ${stats.recovered}"
        }

        binding.month10Calculate.setOnClickListener {
            viewModel.callPoGoApi { response ->
                val stringBuilder = StringBuilder("O pokemom: ")
                    .append(response[(brazilStats.confirmed/
                            (brazilStats.deaths + brazilStats.recovered)).toString()]?.name)
                    .append(" infectou o pokemão: ")
                    .append(response[(portugalStats.confirmed/
                            (portugalStats.deaths + portugalStats.recovered)).toString()]?.name).append("!")

                binding.month10Infected.text = stringBuilder
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
