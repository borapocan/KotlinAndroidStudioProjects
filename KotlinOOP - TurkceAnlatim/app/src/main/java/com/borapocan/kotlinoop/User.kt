package com.borapocan.kotlinoop


class User : People { //() kullanmama gerek yok çünkü People abstract

    //Property
    lateinit var name : String
    var age : Int? = null

    //Constructor vs init
    constructor(nameInput: String, ageInput: Int) {

        this.name = nameInput
        this.age = ageInput
        println("User created")
    }


    //constructor'ı kotlinde bu şekilde kulanmayız primary constructor olarak kullanırız
    //Bir şey yazdırıcaksak veya çok ayrı bir işlem yapılacaksa init kullanırız
    //Eşitlemeleri constructorda yapabiliriz (primary constructor daha verimli)

    init {

        println("init") //init ilk çağırılan fonksiyon olacak

    }
}