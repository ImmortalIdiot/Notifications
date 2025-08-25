package com.immortalidiot.notifications

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.immortalidiot.notifications.ui.MainScreen
import com.immortalidiot.notifications.ui.theme.NotificationsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var notificationService: NotificationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        notificationService = NotificationService(this)
        notificationService.createNotificationChannel()

        setContent {
            NotificationsTheme {
                MainScreen(
                    onFirstButtonClick = {
                        lifecycleScope.launch {
                            checkPermission()
                            sendNotification()
                        }
                    },
                    onSecondButtonClick = {
                        lifecycleScope.launch {
                            checkPermission()
                            goHome(this@MainActivity)
                            delay(2000L)
                            sendNotification()
                        }
                    }
                )
            }
        }
    }

    private fun sendNotification() {
        notificationService.sendNotification(
            title = Utils.TITLE,
            body = Utils.BODY,
            smallIconRes = R.drawable.icon_message,
            imageBitmap = getBitmap(R.drawable.image)
        )
    }

    fun goHome(context: Context) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }
    }

    private fun getBitmap(resId: Int): Bitmap = BitmapFactory.decodeResource(resources, resId)
}
