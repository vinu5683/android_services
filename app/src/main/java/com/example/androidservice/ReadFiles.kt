package com.example.androidservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import android.util.Log
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class ReadFiles : Service() {

    override fun onBind(intent: Intent): IBinder {
        startFetchingFileNames()
        return FileBinder()
    }

    fun startFetchingFileNames(): ArrayList<File> {
        val files: ArrayList<File> = ArrayList()

        val filesPath: File = File(getExternalFilesDir(null)?.parent)

        Log.d("TAG", "startFetchingFileNames: " + filesPath.absoluteFile)

        val f = filesPath.list()
        for (a in f) {
            Log.d("TAG", "startFetchingFileNames: " + a)
        }

        val fileList = filesPath.listFiles()

        for (file in fileList) {
            files.add(file)
        }
        return files
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    public inner class FileBinder : Binder() {
        public fun getAllFiles(): ReadFiles {
            return this@ReadFiles
        }
    }
}