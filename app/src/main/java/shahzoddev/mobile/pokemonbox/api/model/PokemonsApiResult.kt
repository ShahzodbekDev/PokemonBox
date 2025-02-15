package shahzoddev.mobile.pokemonbox.api.model

import com.google.gson.annotations.SerializedName
import shahzoddev.mobile.pokemonbox.domain.PokemonType

data class PokemonsApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites,
    val types: List<PokemonTypeSlot>
)

data class PokemonSprites(
    val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("dream_world") val dreamWorld: DreamWorld
)

data class DreamWorld(
    @SerializedName("front_default") val frontDefault: String?
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)



