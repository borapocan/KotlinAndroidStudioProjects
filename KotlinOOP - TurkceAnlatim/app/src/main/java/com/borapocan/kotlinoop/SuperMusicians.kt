package com.borapocan.kotlinoop


//Kalıtım yapmak için Musicians'ı open class yapman lazım

class SuperMusicians(name: String, instrument: String, age: Int) : Musicians(name, instrument, age) {


    fun sing() {

        println(" Nothing else matters... ")

    }


}