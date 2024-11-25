package com.example.game2d

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch




class Game(val scope: CoroutineScope, val screenH:Int, val screenW: Int,scale:Float, context: Context) {
    var counter = 0
    val state = MutableStateFlow(0)
    val background = Background(screenW)
    val boy =Boy(screenH,scale)
    val Virus = Virus(screenW, screenH, scale)
    val Virus2 = Virus(screenW, screenH, scale)

    var mper1 = MediaPlayer.create(context, R.raw.lastletter)
    var mper2 = MediaPlayer.create(context, R.raw.gameover)

    var isPlaying = true

    fun Play(){
        scope.launch {
          //  counter = 0
            isPlaying = true
            Virus2.y=0

            while (isPlaying) {
                mper1.start()  //播放背景音樂
                counter++
                delay(40)
                background.Roll()

                if (counter % 3 == 0){
                    boy.Walk()
                    Virus.Fly()
                    Virus2.Fly()
                    //判斷是否碰撞，結束遊戲
                    if(boy.getRect().intersect(Virus.getRect())) {
                        isPlaying = false
                        //遊戲結束音效
                        mper1.pause()
                        mper2.start()

                    }


                }




                state.emit(counter)
            }
        }
    }
    fun Restart(){
        Virus.Reset()
        counter = 0
        isPlaying = true
        Play()
    }

}
