package com.svvar.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class ActivityThree : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout3)

        val Rcn: EditText = findViewById(R.id.lay3_Rcn)
        val Xcn: EditText = findViewById(R.id.lay3_Xcn)
        val Rcmin: EditText = findViewById(R.id.lay3_Rcmin)
        val Xcmin: EditText = findViewById(R.id.lay3_Xcmin)


        val resultField: TextView = findViewById(R.id.lay3_result)
        val calculateButton: Button = findViewById(R.id.lay3_button)

        calculateButton.setOnClickListener {
            val Rcn = Rcn.text.toString().toDoubleOrNull() ?: 0.0
            val Xcn = Xcn.text.toString().toDoubleOrNull() ?: 0.0
            val Rcmin = Rcmin.text.toString().toDoubleOrNull() ?: 0.0
            val Xcmin = Xcmin.text.toString().toDoubleOrNull() ?: 0.0
            val result = performCalculation(Rcn, Xcn, Rcmin, Xcmin)
            resultField.text = this.getString(
                R.string.lay3_result,
                String.format("%.0f", result[0]),
                String.format("%.0f", result[1]),
                String.format("%.0f", result[2]),
                String.format("%.0f", result[3])
            ).replace("\\n", "\n")
        }
    }

    private fun performCalculation(Rcn: Double, Xcn: Double, Rcmin: Double, Xcmin: Double): List<Double> {
        val Xt = 233
        val Rsh = Rcn
        val Xsh = Xcn + Xt
        val Zsh = sqrt(Rsh * Rsh + Xsh * Xsh)

        val Rshmin  = Rcmin
        val Xshmin = Xcmin + Xt
        val Zshmin = sqrt(Rshmin * Rshmin + Xshmin * Xshmin)

        val I3sh = (115 * 1000) / (sqrt(3.0) * Zsh)
        val I2sh = I3sh * (sqrt(3.0) / 2)
        val I3shmin = (115 * 1000) / (sqrt(3.0) * Zshmin)
        val I2shmin = I3shmin * (sqrt(3.0) / 2)

        val k = 0.009

        val Rshn = Rsh * k
        val Xshn = Xsh * k
        val Zshn = sqrt(Rshn * Rshn + Xshn * Xshn)

        val Rshnmin = Rshmin * k
        val Xshnmin = Xshmin * k
        val Zshnmin = sqrt(Rshnmin * Rshnmin + Xshnmin * Xshnmin)

        val I3shn = (11 * 1000) / (sqrt(3.0) * Zshn)
        val I2shn = I3shn * (sqrt(3.0) / 2)
        val I3shnmin = (11 * 1000) / (sqrt(3.0) * Zshnmin)
        val I2shnmin = I3shnmin * (sqrt(3.0) / 2)

        return listOf(I3shn, I2shn, I3shnmin, I2shnmin)

    }
}
