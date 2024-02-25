package com.ygolubev.pexels.ui.model

import androidx.lifecycle.ViewModel
import com.ygolubev.pexels.ui.navigation.Navigator

internal class AppViewModel(
    navigator: Navigator,
) : ViewModel() {

    val destination = navigator.destination

}
