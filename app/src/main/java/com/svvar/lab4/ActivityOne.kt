package com.svvar.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class ActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout1)

        val load: EditText = findViewById(R.id.lay1_load)
        val voltage: EditText = findViewById(R.id.lay1_voltage)
        val strumKz: EditText = findViewById(R.id.lay1_strumKZ)
        val timeOff: EditText = findViewById(R.id.lay1_off_time)
        val resultField: TextView = findViewById(R.id.lay1_result)
        val calculateButton: Button = findViewById(R.id.lay1_button)

        calculateButton.setOnClickListener {
            val loadV = load.text.toString().toDoubleOrNull() ?: 0.0
            val voltageV = voltage.text.toString().toDoubleOrNull() ?: 0.0
            val strumKzV = strumKz.text.toString().toDoubleOrNull() ?: 0.0
            val timeOffV = timeOff.text.toString().toDoubleOrNull() ?: 0.0
            val result = performCalculation(loadV, voltageV, strumKzV, timeOffV)
            resultField.text = this.getString(
                R.string.lay1_result,
                String.format("%.2f", result.first),
                String.format("%.2f", result.second)
            ).replace("\\n", "\n")
        }
    }

    private fun performCalculation(load: Double, voltage: Double, strumKZ: Double, timeOff: Double): Pair<Double, Double> {
        val Im = (load / 2) / (sqrt(3.0) * voltage)

        val Sec = Im / 1.4
        val s = (strumKZ*1000 * sqrt(timeOff)) / 92

        return Pair(Sec, s)

    }
}
