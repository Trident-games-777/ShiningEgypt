package com.advasoft.touchretouc.ui.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.advasoft.touchretouc.R
import kotlin.random.Random

class PreGameShiningEgyptActivity : AppCompatActivity() {
    private val listImages =
        listOf(R.drawable.a, R.drawable.j, R.drawable.k, R.drawable.q, R.drawable.x)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_game_shining_egypt)

        val image = listImages.random(Random(System.currentTimeMillis()))
        findViewById<ImageView>(R.id.ivTarget).setImageResource(image)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            with(Intent(this@PreGameShiningEgyptActivity, GameShiningEgyptActivity::class.java)) {
                putExtra("img", image)
                startActivity(this)
                finish()
            }
        }
    }
}