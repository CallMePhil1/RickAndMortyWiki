package com.github.callmephil1.rickandmortywiki.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    abstract val title: String
    abstract val showBackArrow: Boolean

    @Serializable
    object Search : Destination() {
        override val title: String = "Rick And Morty Wiki"
        override val showBackArrow: Boolean = false
    }

    @Serializable
    class Details(
        val id: Int,
        override val title: String,
    ) : Destination() {
        override val showBackArrow: Boolean = true
    }
}