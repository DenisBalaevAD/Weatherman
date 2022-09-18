package com.example.mobilepractica

import android.app.AlertDialog
import android.content.Context

class Dialog {

    fun dialogInformation(context:Context,title:String,message:String){
        val builder= AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("ОК"){ _, _ -> }
        builder.create().show()
    }

}