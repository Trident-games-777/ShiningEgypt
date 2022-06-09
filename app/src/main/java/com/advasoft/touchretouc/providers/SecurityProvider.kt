package com.advasoft.touchretouc.providers

import android.content.Context
import android.provider.Settings
import com.advasoft.touchretouc.data.repo.ShiningEgyptSecurityRepository
import java.io.File

class SecurityProvider(
    private val repository: ShiningEgyptSecurityRepository,
    private val context: Context
) {
    val playGame: Boolean get() = isRooted() || isADBEnabled()

    private fun isRooted(): Boolean {
        try {
            for (dir in repository.securePaths) {
                if (File(dir + "su").exists()) return true
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return false
    }

    private fun isADBEnabled(): Boolean {
        return Settings.Global.getString(
            context.contentResolver,
            Settings.Global.ADB_ENABLED
        ) == "1"
    }
}