package com.example.segundoparcialgalvarini

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var btAct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUser = findViewById(R.id.et_user)
        etPass = findViewById(R.id.et_pass)
        btAct = findViewById(R.id.bt_save)

        val preferences = getSharedPreferences("loginPref", MODE_PRIVATE)
        val userNamePref = preferences.getString("name", "")
        val passwordPref = preferences.getString("pass", "")

        if (userNamePref != null && passwordPref != null) {
            if (userNamePref.isEmpty()) {
                btAct.text = "Registrar"
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        if (userNamePref != null && passwordPref != null) {
            Log.d(TAG, userNamePref)
            Log.d(TAG, passwordPref)
        }

        btAct.setOnClickListener {
            if (etUser.text.isNullOrEmpty() && etPass.text.isNullOrEmpty()) {
                Toast.makeText(this, "Ingrese user y password", Toast.LENGTH_SHORT).show()
            } else {
                val name = etUser.text.toString()
                val pass = etPass.text.toString()

                if (name == userNamePref && pass == passwordPref) {
                    val intent = Intent(this, RecyclerViewActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "usuario o contraseña no valida", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}