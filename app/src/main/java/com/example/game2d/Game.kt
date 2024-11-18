package com.example.game2d

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class Game(val scope: CoroutineScope) {
    var counter = 0
    val state = MutableStateFlow(0)

    fun Play(){
        scope.launch {
            counter = 0
            while (counter<1000) {
                delay(40)
                counter++
                state.emit(counter)
            }
        }
    }
}
