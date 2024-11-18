package com.example.game2d

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings.Global
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.game2d.ui.theme.Game2DTheme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Game2DTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val game = Game(GlobalScope)
                    Start(m = Modifier.padding(innerPadding),game
                    )
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

                }
            }
        }
    }
}

@Composable
fun Start(m:Modifier,game: Game ){
    val counter by game.state.collectAsState()
    var counter2 by remember { mutableStateOf(0) }

    Image(
        painter = painterResource(id = R.drawable.forest),
        contentDescription = "背景圖",
        contentScale = ContentScale.FillBounds,  //縮放符合螢幕寬度
        modifier = Modifier
            .offset { IntOffset(-counter, 0) }
    )


    Row {
        Button(
            onClick = {
                game.Play()
            },
            modifier = m
        ) {
            Text(text = "開始")
        }

        Text(text= counter.toString(), modifier = m)

        Button(
            onClick = {
                if(counter2<10){
                counter2++
                }
            },
            modifier = m
        ){
            Text(text = "加1")
        }

        Text(text = counter2.toString(), modifier = m)
    }

}
