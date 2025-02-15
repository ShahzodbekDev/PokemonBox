package shahzoddev.mobile.pokemonbox.presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.PictureDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.module.AppGlideModule
import com.caverock.androidsvg.SVG
import shahzoddev.mobile.pokemonbox.R
import shahzoddev.mobile.pokemonbox.databinding.PokemonListItemBinding
import shahzoddev.mobile.pokemonbox.domain.Pokemon
import shahzoddev.mobile.pokemonbox.utils.GlideApp
import shahzoddev.mobile.pokemonbox.utils.SvgDecoder
import java.util.Locale

class PokemonAdapter(
    private var items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    class ViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon?) = with(binding) {


            val pokeName = nameTv
            val pokeType1 = pokeTypeTxt
            val pokeType2 = pokeTypeTxt2
            val pokeAbout = pokeAbout



            pokemon?.let {

//               Glide.with(root).load(pokemon.sprites.other.dreamWorld.frontDefault).into(pokeIv)

                Glide.with(root)
                    .`as`(PictureDrawable::class.java)
                    .load(pokemon.sprites.other.dreamWorld.frontDefault)
                    .placeholder(R.drawable.bulbasaur)
                    .error(R.drawable.pikachu)
                    .into(pokeIv)


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
                pokeAbout.text =
                    "It uses the nutrients that are packed on its back in order to grow."

            }
        }

    }


    fun setFilteredList(items: List<Pokemon?>) {
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