package com.svvar.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class ActivityTwo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout2)

        val lay2Voltage: EditText = findViewById(R.id.lay2_voltage)
        val lay2Power: EditText = findViewById(R.id.lay2_power)
        val resultField: TextView = findViewById(R.id.lay2_result)
        val calculateButton: Button = findViewById(R.id.lay2_button)

        calculateButton.setOnClickListener {
            val voltage = lay2Voltage.text.toString().toDoubleOrNull() ?: 0.0
            val power = lay2Power.text.toString().toDoubleOrNull() ?: 0.0
            val result = performCalculation(voltage, power)
            resultField.text = this.getString(
                R.string.lay2_result,
                String.format("%.2f", result.first),
                String.format("%.2f", result.second)
            ).replace("\\n", "\n")
        }
    }

    private fun performCalculation(voltage: Double, power: Double): Pair<Double, Double> {
        val resistanceC = voltage.pow(2) / power
        val resistanceT = (voltage / 100) * (voltage.pow(2) / 6.3)

        val totalResistance = resistanceC + resistanceT

        val current = voltage /(sqrt(3.0) * totalResistance)

        return Pair(totalResistance, current)

    }
}
