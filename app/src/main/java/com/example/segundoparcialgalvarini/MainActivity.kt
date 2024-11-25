package com.example.segundoparcialgalvarini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var btLog: Button
    private lateinit var btReg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUser = findViewById(R.id.et_user)
        etPass = findViewById(R.id.et_pass)
        btLog = findViewById(R.id.bt_login)
        btReg = findViewById(R.id.bt_register)

        val preferences = getSharedPreferences("loginPref", MODE_PRIVATE)
        val userNamePref = preferences.getString("name", "")
        val passwordPref = preferences.getString("pass", "")

        btLog.setOnClickListener {
            val name = etUser.text.toString()
            val pass = etPass.text.toString()

            if (name == userNamePref && pass == passwordPref) {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        btReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}