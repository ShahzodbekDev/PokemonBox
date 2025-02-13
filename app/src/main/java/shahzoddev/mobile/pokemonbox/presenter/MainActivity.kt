package shahzoddev.mobile.pokemonbox.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import shahzoddev.mobile.pokemonbox.api.PokemonRepository
import shahzoddev.mobile.pokemonbox.databinding.ActivityMainBinding
import shahzoddev.mobile.pokemonbox.domain.Pokemon
import shahzoddev.mobile.pokemonbox.viewModel.PokemonViewModel
import shahzoddev.mobile.pokemonbox.viewModel.PokemonViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView : RecyclerView

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.pokemonList

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })

    }

    private fun loadRecyclerView(pokemons : List<Pokemon?>) {
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}

