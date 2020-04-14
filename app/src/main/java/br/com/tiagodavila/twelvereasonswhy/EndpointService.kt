package br.com.tiagodavila.twelvereasonswhy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface EndpointService {

    @Headers(
        "Content-Type: application/json;charset=UTF-8",
        "Accept: application/json;charset=UTF-8",
        "x-rapidapi-key: 1ffb1aaa2bmsh0be82d0555f8ad3p1b7cdejsnb27961d61ebd"
    )

    @GET("stats")
    fun getCoronaStatusInCountry(@Query("country") country: String = "Brazil"): Call<CoronaResponse>

}
