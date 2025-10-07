package com.android.whiteboardassignment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.whiteboardassignment.views.WhiteboardView

class MainActivity : AppCompatActivity() {

    private lateinit var whiteboardView: WhiteboardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        whiteboardView = findViewById(R.id.whiteboardView)

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            whiteboardView.clear()
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            // TODO: Save as JSON
        }

        findViewById<Button>(R.id.btnLoad).setOnClickListener {
            // TODO: Load JSON
        }
    }
}