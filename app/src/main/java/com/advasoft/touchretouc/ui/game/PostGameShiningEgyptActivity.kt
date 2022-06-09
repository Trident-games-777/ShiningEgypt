package com.advasoft.touchretouc.ui.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.advasoft.touchretouc.R

class PostGameShiningEgyptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_game_shining_egypt)

        findViewById<TextView>(R.id.tvScore).text = intent.getStringExtra("score")
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            startActivity(
                Intent(
                    this@PostGameShiningEgyptActivity,
                    PreGameShiningEgyptActivity::class.java
                )
            )
            finish()
        }
    }
}