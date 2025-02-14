package shahzoddev.mobile.pokemonbox.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shahzoddev.mobile.pokemonbox.api.PokemonRepository
import shahzoddev.mobile.pokemonbox.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokemon()
        }
    }

    private fun loadPokemon() {
        val pokemonApiResult = PokemonRepository().listPokemons()

        pokemonApiResult?.results?.let {

            pokemons.postValue(it.map { pokemonResult ->

                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val spokemonApiResults = PokemonRepository().getPokemon(number)


                spokemonApiResults?.let {
                    Pokemon(
                        spokemonApiResults.id,
                        spokemonApiResults.name,
                        spokemonApiResults.sprites,
                        spokemonApiResults.types.map { types -> types.type }

                    )
                }

            })

        }
    }
}