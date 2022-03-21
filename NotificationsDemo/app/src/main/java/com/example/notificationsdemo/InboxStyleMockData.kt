package com.example.notificationsdemo

import android.app.Notification
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

object InboxStyleMockData {

    const val mContentTitle = "5 new emails"
    const val mContentText = "From Jane, Jay, Alex +2 more"
    const val mNumberofEmails = 5
    const val mPriority = NotificationCompat.PRIORITY_DEFAULT

    const val mBigContentTitle = "5 new emails from Jane, Jay, Alex +2 more"
    const val mSummaryText = "New emails"

    fun mIndividualEmailSummary(): ArrayList<String> {
        val list = ArrayList<String>()

        list.add("Jane Faab - Launch Party is here...")
        list.add("Jay Walker - There's a turtle on the server!")
        list.add("Alex Chang - Check this out...")
        list.add("Jane Johns - Check this code?")
        list.add("John Smith - Movies later....")

        return list
    }
    fun mParticipants(): ArrayList<String>{
        //Se o telefone estiver no modo "Não pertubar"
        //o usuário ainda será notificado o usuário(s) estiver como favorito
        val list = ArrayList<String>()

        list.add("Jane Faab")
        list.add("Jay Walker")
        list.add("Alex Chang")
        list.add("Jane Johns")
        list.add("John Smith")

        return list
    }

    //Valores do Notification channel (Para dispositivos alvejando API level 26 ou acima):
    const val mChannelId = "channel_email_1"
    //O name do canal que o usuário possa ver
    const val mChannelName = "Sample Email"
    //A descrição do canal que o usuário possa ver
    const val mChannelDescription = "Sample Email Notifications"
    const val mChannelImportance = NotificationManager.IMPORTANCE_DEFAULT
    const val mChannelEnableVibrate = true
    const val mChannelLockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE

}