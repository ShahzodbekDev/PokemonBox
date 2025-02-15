package shahzoddev.mobile.pokemonbox.domain

import shahzoddev.mobile.pokemonbox.api.model.PokemonSprites
import java.util.Locale

data class Pokemon(
    val number: Int,
    val name: String,
    val sprites: PokemonSprites,
    val types: List<PokemonType>,
) {
    val formattedName =
        name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

}

