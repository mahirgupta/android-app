package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_VISIBLE
import android.widget.Button
import android.widget.Toast
import com.quizapp.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_VISIBLE

       val button= findViewById<Button>(R.id.btn_start)
        button.setOnClickListener{
            if(edit_text.text.toString().isEmpty()){
                Toast.makeText(this, "Enter the Name", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,QuizQuestionActivity::class.java)
                intent.putExtra(Constants.username,edit_text.text.toString())
                startActivity(intent)
                finish()

            }
        }
    }
}