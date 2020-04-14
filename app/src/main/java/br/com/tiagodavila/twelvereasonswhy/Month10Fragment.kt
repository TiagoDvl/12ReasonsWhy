package br.com.tiagodavila.twelvereasonswhy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_month10.*


class Month10Fragment : BaseFragment() {

    companion object {
        fun newInstance() = Month10Fragment()
    }

    private lateinit var viewModel: Month10ViewModel

    private lateinit var brazilStats: Stats
    private lateinit var portugalStats: Stats

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month10, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Month10ViewModel::class.java)
        viewModel.callCoronaVirusApi("Brazil") { coronaResponse ->
            val stats = coronaResponse.data.covid19Stats[0]
            brazilStats = stats
            month_10_brazil_confirmed.text = "Confirmados: ${stats.confirmed}"
            month_10_brazil_deaths.text =  "Mortes: ${stats.deaths}"
            month_10_brazil_recovered.text =  "Recuperados: ${stats.recovered}"
        }

        viewModel.callCoronaVirusApi("Portugal") { coronaResponse ->
            val stats = coronaResponse.data.covid19Stats[0]
            portugalStats = stats
            month_10_portugal_confirmed.text = "Confirmados: ${stats.confirmed}"
            month_10_portugal_deaths.text = "Mortes: ${stats.deaths}"
            month_10_portugal_recovered.text = "Recuperados: ${stats.recovered}"
        }

        month_10_calculate.setOnClickListener {
            viewModel.callPoGoApi { response ->
                val stringBuilder = StringBuilder("O pokemom: ")
                    .append(response[(brazilStats.confirmed/
                            (brazilStats.deaths + brazilStats.recovered)).toString()]?.name)
                    .append(" infectou o pokemão: ")
                    .append(response[(portugalStats.confirmed/
                            (portugalStats.deaths + portugalStats.recovered)).toString()]?.name).append("!")

                month_10_infected.text = stringBuilder
            }
        }
    }

}
