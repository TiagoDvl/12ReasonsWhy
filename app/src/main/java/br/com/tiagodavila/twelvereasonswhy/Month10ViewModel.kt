package br.com.tiagodavila.twelvereasonswhy

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.internal.LinkedTreeMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class Month10ViewModel : ViewModel() {


    fun callCoronaVirusApi(country: String, callBack: (coronaResponse: CoronaResponse) -> Unit) {
        getEndpointService().getCoronaStatusInCountry(country).enqueue(object : Callback<CoronaResponse>{
            override fun onFailure(call: Call<CoronaResponse>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<CoronaResponse>, response: Response<CoronaResponse>) {
                response.body()?.let(callBack)
            }

        })
    }

    fun callPoGoApi(callBack: (LinkedTreeMap<String, Pokemon>) -> Unit) {
        getPoGoEndpointService().getReleasedPokemon().enqueue(object : Callback<LinkedTreeMap<String, Pokemon>>{
            override fun onFailure(call: Call<LinkedTreeMap<String, Pokemon>>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<LinkedTreeMap<String, Pokemon>>, response: Response<LinkedTreeMap<String, Pokemon>>) {
                response.body()?.let(callBack)
            }

        })
    }

    private fun getEndpointService(): EndpointService {
        return Retrofit.Builder()
            .baseUrl("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                if (BuildConfig.DEBUG) {
                    client(
                        OkHttpClient
                            .Builder()
                            .addInterceptor(
                                HttpLoggingInterceptor()
                                    .apply { this.level = level })
                            .build()
                    )
                }
            }
            .build()
            .create(EndpointService::class.java)
    }

    private fun getPoGoEndpointService(): PoGoEndpointService {
        return Retrofit.Builder()
            .baseUrl("https://pokemon-go1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                if (BuildConfig.DEBUG) {
                    client(
                        OkHttpClient
                            .Builder()
                            .addInterceptor(
                                HttpLoggingInterceptor()
                                    .apply { this.level = level })
                            .build()
                    )
                }
            }
            .build()
            .create(PoGoEndpointService::class.java)
    }
}
