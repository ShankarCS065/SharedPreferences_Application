package com.example.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cbFirst=findViewById<CheckBox>(R.id.cbAdult)
        val sharedPref=getSharedPreferences("my pref", Context.MODE_PRIVATE)
        val editor=sharedPref.edit()
        findViewById<Button>(R.id.btnSave).setOnClickListener{
            val name=findViewById<EditText>(R.id.etName).text.toString()
            val age=findViewById<EditText>(R.id.etAge).text.toString().toInt()
            val isAdult=cbFirst.isChecked

            editor.apply {
                putString("name",name)
                putInt("age",age)
                putBoolean("isAdult",isAdult)
                apply()
            }
        }
        findViewById<Button>(R.id.btnLoad).setOnClickListener{
            val name=sharedPref.getString("name",null)
            val age=sharedPref.getInt("age",0)
            val adult=sharedPref.getBoolean("isAdult",false)

            findViewById<EditText>(R.id.etName).setText(name)
            findViewById<EditText>(R.id.etAge).setText(age.toString())
            cbFirst.isChecked=adult
        }

    }
}