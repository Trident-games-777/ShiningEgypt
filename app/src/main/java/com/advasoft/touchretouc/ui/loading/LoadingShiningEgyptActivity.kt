package com.advasoft.touchretouc.ui.loading

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.advasoft.touchretouc.R
import com.advasoft.touchretouc.providers.SecurityProvider
import com.advasoft.touchretouc.ui.game.PreGameShiningEgyptActivity
import com.advasoft.touchretouc.ui.web.WebViewShiningEgyptActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoadingShiningEgyptActivity : AppCompatActivity() {

    @Inject
    lateinit var securityProvider: SecurityProvider
    private val loadingShiningEgyptViewModel: LoadingShiningEgyptViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_shining_egypt)

        if (securityProvider.playGame) {
            startActivity(Intent(this, PreGameShiningEgyptActivity::class.java))
            finish()
        } else {
            loadingShiningEgyptViewModel.isFirst.observe(this) { isFirst ->
                lifecycleScope.launch {
                    val currentUrl = if (isFirst) {
                        loadingShiningEgyptViewModel.createUrl()
                    } else {
                        loadingShiningEgyptViewModel.getCurrentUrl()
                    }

                    with(
                        Intent(
                            this@LoadingShiningEgyptActivity,
                            WebViewShiningEgyptActivity::class.java
                        )
                    ) {
                        putExtra("link_extra", currentUrl)
                        startActivity(this)
                        finish()
                    }
                }
            }
        }
    }
}