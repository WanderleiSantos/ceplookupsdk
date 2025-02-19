package com.manodev.ceplookupsdk

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CepLookup {

    private val api: ViaCepService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ViaCepService::class.java)
    }

    fun getAddress(cep: String, callback: (AddressResponse?) -> Unit) {
        api.getAddress(cep).enqueue(object : Callback<AddressResponse> {
            override fun onResponse(call: Call<AddressResponse>, response: Response<AddressResponse>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}