package com.example.sslcommerzekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    lateinit var ed:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

     var pay:Button = findViewById(R.id.pay)
        ed = findViewById(R.id.edAmount)

        pay.setOnClickListener {
            if (ed.text.isEmpty()){
                Toast.makeText(this,"Enter Amount",Toast.LENGTH_SHORT).show()

            }else{
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("amount",ed.text.toString())
                startActivity(intent)
            }
        }

    }
}