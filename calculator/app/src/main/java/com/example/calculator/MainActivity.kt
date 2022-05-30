package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    var lastnumeric: Boolean = false
    var lastdot: Boolean = false
    var lastoperator: Boolean = false

    fun onenter(view: View){
//        Toast.makeText(this,"button works",Toast.LENGTH_SHORT).show()
        tvinput.append((view as Button).text)
        lastnumeric = true
    }

    fun onclear(view: View){
        tvinput.setText("")
        lastnumeric = false
        lastdot = false
        lastoperator = false
    }

    fun ondecimal(view: View) {
        if(lastnumeric && !lastdot){
            tvinput.append(".")
            lastdot = true
        }
    }

    fun operator(view: View) {
        if(!lastoperator){
            if((view as Button).text=="-"){
                if(!lastnumeric && !tvinput.text.contains("-")){
                    tvinput.append("-")
                }
                else if(lastnumeric){
                    tvinput.append("-")
                    lastoperator=true
                    lastnumeric=false
                    lastdot=false
                }
            }
            if((view as Button).text=="+" && lastnumeric){
                tvinput.append("+")
                lastoperator=true
                lastnumeric=false
                lastdot=false
            }
            if((view as Button).text=="*" && lastnumeric){
                tvinput.append("*")
                lastoperator=true
                lastnumeric=false
                lastdot=false
            }
            if((view as Button).text=="/" && lastnumeric){
                tvinput.append("/")
                lastoperator=false
                lastdot=false
            }
        }
    }

    fun onequal(view: View) {
        var str: String = tvinput.text.toString()
        var firstneg: Boolean = false
        var result: Double
        if(str[0]=='-'){
            firstneg = true
            str = str.substring(1,str.length)
        }
        if(str.contains("-")){
            val splitval = str.split("-")
            var one = splitval[0]
            val two = splitval[1]


            if(firstneg){
                one = "-"+one
                firstneg = false
            }
            var result = one.toDouble()-two.toDouble()
            tvinput.text= result.toString()
            lastoperator=false

        }

       else if(str.contains("+")){
            val splitval = str.split("+")
            var one = splitval[0]
            val two = splitval[1]

            if(firstneg){
                one = "-"+one
                firstneg = false
            }
            var result = one.toDouble()+two.toDouble()
            tvinput.text= result.toString()
            lastoperator=false

        }

        else if(str.contains("*")){
            val splitval = str.split("*")
            var one = splitval[0]
            val two = splitval[1]

            if(firstneg){
                one = "-"+one
                firstneg = false
            }
            var result = one.toDouble()*two.toDouble()
            tvinput.text= result.toString()
            lastoperator=false
        }

        else if(str.contains("/")){
            val splitval = str.split("/")
            var one = splitval[0]
            val two = splitval[1]

            if(firstneg){
                one = "-"+one
                firstneg = false
            }
            if(two.toDouble()==0.0){
                tvinput.text="infinity"
            }
            else {
                var result = one.toDouble() / two.toDouble()
                tvinput.text = result.toString()
                lastoperator=false
            }
        }
    }
}