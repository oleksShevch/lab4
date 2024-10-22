package com.svvar.lab4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.layout_spinner)
        val layouts = arrayOf("Переріз кабелів", "Струми КЗ 10кв КПП", "Струми КЗ Хмельницький")

//        val container: Spinner = findViewById(R.id.layout_container)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, layouts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> startActivity(Intent(this@MainActivity, ActivityOne::class.java))
                    1 -> startActivity(Intent(this@MainActivity, ActivityTwo::class.java))
                    2 -> startActivity(Intent(this@MainActivity, ActivityThree::class.java))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
}
