package br.com.tiagodavila.twelvereasonswhy

import com.google.gson.internal.LinkedTreeMap

data class PoGoResponse(val releasedPokemons: LinkedTreeMap<String, Any>)

data class Pokemon(val id: Int, val name: String)