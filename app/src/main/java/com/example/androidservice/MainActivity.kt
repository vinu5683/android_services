package com.example.androidservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {


    val fileList: ArrayList<File> = ArrayList();
    val adapter = FilesAdapter(fileList)

    lateinit var readFiles: ReadFiles
    var isServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rvFiles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        startReadFileService()

        btnUpdateList.setOnClickListener {
            fileList.clear()
            fileList.addAll(readFiles.startFetchingFileNames())
            adapter.notifyDataSetChanged()
        }

    }

    private fun startReadFileService() {
        val intent = Intent(this, ReadFiles::class.java)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder: ReadFiles.FileBinder = service as ReadFiles.FileBinder
            readFiles = binder.getAllFiles()
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isServiceBound = false
        }
    }


}