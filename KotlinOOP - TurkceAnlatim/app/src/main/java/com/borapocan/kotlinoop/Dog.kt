package com.borapocan.kotlinoop

class Dog : Animal() {

    fun test() {

        //super keyword'uyle
        // super inheritance yaptığımız (kalıtım) sınıfa referans verir
        // Burada sing'i çağırabiliriz
        super.sing()   // Animal sound

    }

    //Sing hata verdi supertype Animal'a ait kullanmak için override edilmeli
//    fun sing() {
//    }

    // override edebilmen için Animal class'ında fun sing()' in open fun olması lazım
    override fun sing() {

        println("Hav Hav")
    }
}