package com.example.quizapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.quizapp.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_question.*


class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener {
    var current_que:Int =1
    var choose_opt:Int? = 0
    var correctopt:Int?=null
    var opt1: TextView?=null
    var opt2: TextView?=null
    var opt3: TextView?=null
    var opt4: TextView?=null
    var btn:Button?=null
    var already_answered:Boolean=false
    var scor:Int=0
    var musername:String="username"

     val questionlist = Constants.getQuestions()



    override fun onCreate(savedInstanceState: Bundle?) {
        musername=intent.getStringExtra(Constants.username).toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        set_que()
        opt1=findViewById(R.id.option_one)
        opt1?.setOnClickListener(this)
        opt2=findViewById(R.id.option_two)
        opt2?.setOnClickListener(this)
        opt3=findViewById(R.id.option_three)
        opt3?.setOnClickListener(this)
        opt4=findViewById(R.id.option_four)
        opt4?.setOnClickListener(this)
        btn=findViewById(R.id.btn_submit)
        btn?.setOnClickListener(this)

      //  change_que()




        Log.i("Question size", "${questionlist.size}")
    }

    private fun set_que() {
        default()
        tv_question.text = questionlist[current_que -1].question
        image.setImageResource(questionlist[current_que-1].image)
        pv.progress= current_que
        pv_text.text = "${current_que}/${pv.max}"
        btn?.text="Submit"
        option_one.text=questionlist[current_que-1].optionOne
        option_two.text=questionlist[current_que-1].optionTwo
        option_three.text=questionlist[current_que-1].optionThree
        option_four.text=questionlist[current_que-1].optionFour
    }

    private fun default() {
        option_one.background=ContextCompat.getDrawable(this,R.drawable.default_border)
        option_two.background=ContextCompat.getDrawable(this,R.drawable.default_border)
        option_three.background=ContextCompat.getDrawable(this,R.drawable.default_border)
        option_four.background=ContextCompat.getDrawable(this,R.drawable.default_border)
    }

    override fun onClick(v: View?) {
       //  Toast.makeText(this, "onclick", Toast.LENGTH_SHORT).show()
         if(!already_answered){
            when(v?.id){
             R.id.option_one->{
                 selectedopt(option_one,1)
             }
             R.id.option_two->{
                 selectedopt(option_two,2)
             }
             R.id.option_three->{
                 selectedopt(option_three,3)
             }
             R.id.option_four->{
                 selectedopt(option_four,4)
             }
             R.id.btn_submit->{
                 //Toast.makeText(this, "submit", Toast.LENGTH_SHORT).show()
                if(choose_opt!=0){
                    if(questionlist[current_que-1].correctopt!=choose_opt){
                        wrong()
                    }
                    else{
                        scor++
                    }
                    correct(questionlist[current_que-1].correctopt)
                    if(current_que<questionlist.size) {
                        btn?.text = "Go to next que"
                        current_que++

                    }
                    else{
                        btn?.text="finish"
                        current_que++

                    }
                    already_answered=true


                }
                 else{
                    Toast.makeText(this, "choose option", Toast.LENGTH_SHORT).show()
                }
             }

         }
         }
         else{
             when(v?.id){
             R.id.btn_submit->{
                 if(current_que<=questionlist.size){
                set_que()
                 already_answered=false
                 choose_opt=0
             }
                 else{
                     val intent=Intent(this,Result::class.java)
                     intent.putExtra(Constants.username,musername)
                     intent.putExtra(Constants.total,questionlist.size.toString())
                     intent.putExtra(Constants.score,scor.toString())
                     startActivity(intent)
                     finish()
                    // Toast.makeText(this, "quiz finished", Toast.LENGTH_SHORT).show()
                 }
         }
             }
         }
    }













    private fun correct(correctopt: Int) {
         when(correctopt){
              1->{
            opt1?.background=ContextCompat.getDrawable(this,R.drawable.right)
             }
             2->{
                 opt2?.background=ContextCompat.getDrawable(this,R.drawable.right)
             }
             3->{
                 opt3?.background=ContextCompat.getDrawable(this,R.drawable.right)
             }
             4->{
                 opt4?.background=ContextCompat.getDrawable(this,R.drawable.right)
             }
         }
    }

    private fun wrong() {
        when(choose_opt){
            1->{
                opt1?.background=ContextCompat.getDrawable(this,R.drawable.wrong)
            }
            2->{
                opt2?.background=ContextCompat.getDrawable(this,R.drawable.wrong)
            }
            3->{
                opt3?.background=ContextCompat.getDrawable(this,R.drawable.wrong)
            }
            4->{
                opt4?.background=ContextCompat.getDrawable(this,R.drawable.wrong)
            }
        }

    }

    private fun selectedopt(optionOne: TextView?, i: Int) {
        default()
        optionOne?.background = ContextCompat.getDrawable(this,R.drawable.selected_border)
        choose_opt=i
    }
}