package shahzoddev.mobile.pokemonbox.presenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import shahzoddev.mobile.pokemonbox.databinding.ActivityMainBinding
import shahzoddev.mobile.pokemonbox.domain.Pokemon
import shahzoddev.mobile.pokemonbox.viewModel.PokemonViewModel
import shahzoddev.mobile.pokemonbox.viewModel.PokemonViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView

    private lateinit var notFoundView: TextView

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.pokemonList


        notFoundView = binding.notFound
        notFoundView.isVisible = false


        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterPokemonList(s.toString())
            }
        })



        viewModel.pokemons.observe(this, Observer { pokemons ->
            val isLoading = pokemons.isEmpty()

            binding.progressBar.isVisible = isLoading
            binding.waitingText.isVisible = isLoading
            recyclerView.isVisible = !isLoading
            notFoundView.isVisible = false


            if (!isLoading) {
                loadRecyclerView(pokemons)
            }
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.adapter = PokemonAdapter(pokemons)
    }

    private fun filterPokemonList(query: String) {
        val allPokemons = viewModel.pokemons.value ?: emptyList()
        val filteredList = allPokemons.filter { pokemon ->
            pokemon?.let {
                val nameMatches = it.formattedName.contains(query, ignoreCase = true)
                val typeMatches = it.types.any { type ->
                    type.name.contains(query, ignoreCase = true)
                }
                nameMatches || typeMatches // Agar nomi yoki turi mos kelsa, natija sifatida qaytariladi
            } ?: false
        }

        val isListEmpty = filteredList.isEmpty()

        binding.notFound.isVisible = isListEmpty
        binding.pokemonList.isVisible = !isListEmpty


        (recyclerView.adapter as? PokemonAdapter)?.setFilteredList(filteredList)


    }
}

