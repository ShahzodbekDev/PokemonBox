package shahzoddev.mobile.pokemonbox.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shahzoddev.mobile.pokemonbox.api.model.PokemonApiResult
import shahzoddev.mobile.pokemonbox.api.model.PokemonService
import shahzoddev.mobile.pokemonbox.api.model.PokemonsApiResult

class PokemonRepository() {
    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 150): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()

    }

    fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)

        return call.execute().body()

    }


}

