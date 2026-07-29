package ru.khubulty.navigationApi

import androidx.navigation3.runtime.NavKey

interface Navigator {
    val backStack: List<NavKey>

    fun navigate(destination: NavKey)

    fun replace(destination: NavKey)

    fun goBack()

    fun replaceAll(destination: NavKey)
}