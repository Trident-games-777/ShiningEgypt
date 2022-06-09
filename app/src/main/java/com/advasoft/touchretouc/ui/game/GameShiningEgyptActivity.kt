package com.advasoft.touchretouc.ui.game

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.advasoft.touchretouc.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameShiningEgyptActivity : AppCompatActivity() {
    private lateinit var tvTimer: TextView
    private lateinit var tvCaught: TextView

    private var timer = 30
    private var caught = 0
    private val listImages =
        listOf(R.drawable.a, R.drawable.j, R.drawable.k, R.drawable.q, R.drawable.x)
    private var targetImg: Int? = null
    private var endAnim = 0

    private lateinit var btn1: ImageButton
    private lateinit var btn2: ImageButton
    private lateinit var btn3: ImageButton
    private lateinit var btn4: ImageButton
    private lateinit var btn5: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_shining_egypt)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)

        tvCaught = findViewById(R.id.tvCaught)
        tvCaught.text = caught.toString()
        tvTimer = findViewById(R.id.tvTime)
        tvTimer.text = timer.toString()

        targetImg = intent.getIntExtra("img", -1)

        val buttons = listOf(btn1, btn2, btn3, btn4, btn5)
        buttons.forEach { imageButton ->
            animateIcon(imageButton, randomDuration())
            imageButton.setOnClickListener { view ->
                if (view.tag == targetImg) tvCaught.text = (++caught).toString()
            }
        }

        lifecycleScope.launch {
            while (timer != 0) {
                delay(1000)
                timer--
                tvTimer.text = timer.toString()
            }
        }
    }

    private fun animateIcon(view: ImageButton, dur: Long) {
        setRandomImage(view)
        ObjectAnimator.ofFloat(view, "translationY", 0f, 2500f).apply {
            duration = dur
            start()
        }.doOnEnd {
            if (timer > 0) {
                animateIcon(view, randomDuration())
            } else {
                endAnim++
                if (endAnim == 5) {
                    with(
                        Intent(
                            this@GameShiningEgyptActivity,
                            PostGameShiningEgyptActivity::class.java
                        )
                    ) {
                        putExtra("score", caught.toString())
                        startActivity(this)
                        finish()
                    }
                }
            }
        }
    }

    private fun randomDuration() = (2000L..6000L).random(Random(System.currentTimeMillis()))

    private fun setRandomImage(view: ImageButton) {
        val img = listImages.random(Random(System.currentTimeMillis()))
        view.tag = img
        view.setImageResource(img)
    }
}