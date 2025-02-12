package shahzoddev.mobile.pokemonbox.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shahzoddev.mobile.pokemonbox.api.model.PokemonApiResult
import shahzoddev.mobile.pokemonbox.api.model.PokemonService
import shahzoddev.mobile.pokemonbox.api.model.PokemonsApiResult
import kotlin.math.log

class PokemonRepository() {
    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()

    }
    fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)

        return call.execute().body()

    }

}