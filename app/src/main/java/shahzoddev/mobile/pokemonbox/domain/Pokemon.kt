package shahzoddev.mobile.pokemonbox.domain

import java.util.Locale

data class Pokemon (
    val number: Int,
    val name: String,
    val about: String,
    val types: List<PokemonType>,
){
    val formattedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
}

