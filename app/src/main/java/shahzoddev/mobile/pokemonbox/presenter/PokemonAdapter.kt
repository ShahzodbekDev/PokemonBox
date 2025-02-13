package shahzoddev.mobile.pokemonbox.presenter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shahzoddev.mobile.pokemonbox.databinding.PokemonListItemBinding
import shahzoddev.mobile.pokemonbox.domain.Pokemon
import java.util.Locale

class PokemonAdapter(
    private var items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    class ViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon?) = with(binding) {


            val pokeName = binding.nameTv
            val pokeType1 = binding.pokeTypeTxt
            val pokeType2 = binding.pokeTypeTxt2
            val pokeAbout = binding.pokeAbout

            pokemon?.let {

                Glide.with(root).load(pokemon.sprites.frontDefault).into(pokeIv)

                pokeName.text = pokemon.formattedName

                pokeType1.text = pokemon.types[0].name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                if (pokemon.types.size > 1) {
                    pokeType2.isVisible = true
                    pokeType2.text = pokemon.types[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }

                } else {
                    pokeType2.isVisible = false
                }
                pokeAbout.text = "It uses the nutrients that are packed on its back in order to grow."

            }
        }
    }

    fun setFilteredList(items: List<Pokemon?>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PokemonListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }


}