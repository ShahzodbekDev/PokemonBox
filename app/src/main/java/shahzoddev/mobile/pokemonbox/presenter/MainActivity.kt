package shahzoddev.mobile.pokemonbox.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import shahzoddev.mobile.pokemonbox.api.PokemonRepository
import shahzoddev.mobile.pokemonbox.databinding.ActivityMainBinding
import shahzoddev.mobile.pokemonbox.domain.Pokemon

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//       val charmender = Pokemon(
//           "Charmender",
//            "",
//            "Wird gelernt, wenn ein Pokémon ein bestimmtes Level erreicht.",
//            listOf(
//                PokemonType("Fire")
//            )
//
//        )
//
//        val pikachu = Pokemon(
//            "Pikachu",
//            "",
//            "Wird gelernt, wenn ein Pokémon ein bestimmtes Level erreicht.",
//            listOf(
//                PokemonType("Fire")
//            )
//
//        )
//
//        val spokesmon = listOf(charmender,pikachu, charmender,charmender,pikachu, charmender,charmender,pikachu, charmender)

        recyclerView = binding.pokemonList

        Thread(Runnable {
            loadPokemon()
        }).start()


    }

    private fun loadPokemon() {
        val pokemonApiResult = PokemonRepository().listPokemons()
        pokemonApiResult?.results?.let {

            val pokemons: List<Pokemon?> = it.map { pokemonResult ->

                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val spokemonApiResults = PokemonRepository().getPokemon(number)


                spokemonApiResults?.let {
                    Pokemon(
                        spokemonApiResults.id,
                        spokemonApiResults.name,
                        "",
                        spokemonApiResults.types.map { types -> types.type }


                    )
                }

            }

            recyclerView.post {
                recyclerView.adapter = PokemonAdapter(pokemons)
            }


        }
    }
}

