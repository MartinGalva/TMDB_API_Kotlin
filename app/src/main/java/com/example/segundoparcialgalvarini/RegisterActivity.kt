package com.example.segundoparcialgalvarini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var btAct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUser = findViewById(R.id.et_user)
        etPass = findViewById(R.id.et_pass)
        btAct = findViewById(R.id.bt_save)

        btAct.setOnClickListener {
            val userName = etUser.text.toString()
            val password = etPass.text.toString()

            if (userName.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Ingrese un user y password", Toast.LENGTH_SHORT).show()
            } else {
                val preferences = getSharedPreferences("loginPref", MODE_PRIVATE)
                preferences.edit().apply {
                    putString("name", userName)
                    putString("pass", password)
                    apply()
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}