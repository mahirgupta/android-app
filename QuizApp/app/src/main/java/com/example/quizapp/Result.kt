package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quizapp.Constants
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {

    var musername:String="usrname"
    var score:String="0"
    var total:String="0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        musername = intent.getStringExtra(Constants.username).toString()
        score=intent.getStringExtra(Constants.score).toString()
        total=intent.getStringExtra(Constants.total).toString()
        tv_username.text=musername
        tv_score.text="Your Score is ${score} out of ${total}"
        btn_final.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}