package com.borapocan.kotlinsimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var firstNumber : Int? = null

    private var secondNumber : Int? = null

    private var result : Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mySum(view: View) {

        firstNumber = editText.text.toString().toIntOrNull()

        secondNumber = editText2.text.toString().toIntOrNull()

        if (firstNumber == null || secondNumber == null) {

            resultText.text = "Error: Please write down a number."

        } else {

            result = firstNumber!! + secondNumber!!

            resultText.text = "Result : $result"

        }

    }


    
    fun mySub(view: View) {

        firstNumber = editText.text.toString().toIntOrNull()

        secondNumber = editText2.text.toString().toIntOrNull()

        if (firstNumber == null || secondNumber == null) {

            resultText.text = "Error: Please write down a number."

        } else {

            result = firstNumber!! - secondNumber!!

            resultText.text = "Result : $result"

        }




    }

    fun myMultiply(view: View) {

        firstNumber = editText.text.toString().toIntOrNull()

        secondNumber = editText2.text.toString().toIntOrNull()

        if (firstNumber == null || secondNumber == null) {

            resultText.text = "Error: Please write down a number."

        } else {

            result = firstNumber!! * secondNumber!!

            resultText.text = "Result : $result"

        }


    }

    fun myDiv(view: View) {

        firstNumber = editText.text.toString().toIntOrNull()

        secondNumber = editText2.text.toString().toIntOrNull()

        if (firstNumber == null || secondNumber == null) {

            resultText.text = "Error: Please write down a number."

        } else {

            result = firstNumber!! / secondNumber!!

            resultText.text = "Result : $result"

        }


    }

    fun clear(view: View) {

        editText.text = null

        editText2.text = null

        resultText.text = "Result : 0"


    }


}