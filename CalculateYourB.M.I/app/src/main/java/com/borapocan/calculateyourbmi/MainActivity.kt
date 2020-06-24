package com.borapocan.calculateyourbmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun calculateBMI(view: View) {


        var weight = weightText.text.toString().toDoubleOrNull()

        if (weight == null) {

            weight = 0.0

            resultText2.text = "Please add your weight!"

        }

        var height = heightText.text.toString().toDoubleOrNull()

        if (height == null) {

            height= 0.0

            resultText2.text = "Please add your height!"
        }


        var result = (weight / (height*height))

        if (result < 18.5) {

            resultText2.text = "Overall: You are considered to be underweight. "

        } else if (result < 25) {

            resultText2.text = "Overall: You are considered to be within a healthy weight range."

        } else if (result < 30.0) {

            resultText2.text = "Overall: You are considered to be overweight."

        }else if (result >= 30.0) {

            resultText2.text = "Overall: You are considered to be obese."

        }

        resultText.text = "Weight: ${weight}, Height: ${height} Your Body-Mass Index: ${result}"



    }






}
