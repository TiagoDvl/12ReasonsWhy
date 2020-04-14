package br.com.tiagodavila.twelvereasonswhy

data class CoronaResponse(val data: Data)

data class Data(val covid19Stats: Array<Stats>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (!covid19Stats.contentEquals(other.covid19Stats)) return false

        return true
    }

    override fun hashCode(): Int {
        return covid19Stats.contentHashCode()
    }
}

data class Stats(val confirmed: Int, val deaths: Int, val recovered: Int)