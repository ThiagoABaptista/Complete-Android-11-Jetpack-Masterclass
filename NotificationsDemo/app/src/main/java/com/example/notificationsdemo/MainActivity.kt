package com.example.notificationsdemo

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val NOTICATION_ID = 888
    private lateinit var mNotificationManagerCompat: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)

        val btnInboxStyleNotification = findViewById<Button>(R.id.btn_inbox_styles)
        btnInboxStyleNotification.setOnClickListener(this@MainActivity)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_inbox_styles -> {
                generateInboxStyleNotification()
                return
            }
        }
    }
    private fun generateInboxStyleNotification(){

        val notificationChannelId: String =
            NotificationUtil().createInboxStyleNotificationChannel(this)
        val inboxStyle =
            NotificationCompat.InboxStyle()
                .setBigContentTitle(InboxStyleMockData.mBigContentTitle)
                .setSummaryText(InboxStyleMockData.mSummaryText)

        for (summary in InboxStyleMockData.mIndividualEmailSummary()) {
            inboxStyle.addLine(summary)
        }

        val mainIntent = Intent(this,MainActivity::class.java)

        val mainPendingIntent = PendingIntent.getActivity(
            this,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext,
            notificationChannelId
        )

        notificationCompatBuilder.setStyle(inboxStyle)
            .setContentTitle(InboxStyleMockData.mContentTitle)
            .setContentText(InboxStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ic_stat_notification)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_person
                )
            )
            .setContentIntent(mainPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.purple_500
                )
            )
            .setSubText(InboxStyleMockData.mNumberofEmails.toString())
            .setVisibility(InboxStyleMockData.mChannelLockscreenVisibility)
        for (name in InboxStyleMockData.mParticipants()){
            notificationCompatBuilder.addPerson(name)
        }

        val notification = notificationCompatBuilder.build()

        mNotificationManagerCompat.notify(NOTICATION_ID,notification)
    }
}