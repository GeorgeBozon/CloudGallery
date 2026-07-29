package ru.khubulty.navigationImpl

import androidx.navigation3.runtime.NavKey
import ru.khubulty.navigationApi.Navigator
import javax.inject.Inject

internal class DefaultNavigator @Inject constructor(@InitialDestination startDestination: NavKey) :
    Navigator {
    private val _backStack = mutableListOf(startDestination)
    override val backStack: List<NavKey> get() = _backStack
    override fun navigate(destination: NavKey) {
        _backStack.add(destination)
    }

    override fun replace(destination: NavKey) {
        _backStack.removeLastOrNull()
        _backStack.add(destination)
    }

    override fun goBack() {
        if (_backStack.size > 1) _backStack.removeLastOrNull()
    }

    override fun replaceAll(destination: NavKey) {
        _backStack.clear()
        _backStack.add(destination)
    }
}