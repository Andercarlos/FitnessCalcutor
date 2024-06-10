package com.example.fitnesscalcutor

import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity : AppCompatActivity() {

    private lateinit var editPeso : EditText
    private lateinit var editAltura : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editPeso = findViewById(R.id.edit_imc_peso)
        editAltura = findViewById(R.id.edit_imc_altura)

        val btnSend : Button = findViewById(R.id.btn_calc)

        btnSend.setOnClickListener {
            if(!validation()){
                Toast.makeText(this, "Não deixe nenhum campo vazio ou iniciando com 0",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peso = editPeso.text.toString().toInt()
            val altura = editAltura.text.toString().toInt()

            val result = imcCalculate(peso,altura)

            Toast.makeText(this, "Seu imc é $result",Toast.LENGTH_SHORT).show()


        }


    }
        private fun imcCalculate(peso: Int, altura: Int): Double {
            return peso / ( (altura/100.0) * (altura/100.0) )

        }
    private fun validation(): Boolean {
        return (editPeso.text.toString().isNotEmpty() &&
            editAltura.text.toString().isNotEmpty() &&
            !editPeso.text.toString().startsWith("0") &&
            !editAltura.text.toString().startsWith("0"))


    }
}