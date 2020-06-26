package com.borapocan.kotlinoop


//1
//class Musicians {
////
////    var name: String? = null
////
////    var instrument: String? = null
////
////    var age: Int? = null
////
////    constructor(name: String, instrument: String, age: Int) {
////
////        this.name = name
////
////        this.instrument = instrument
////
////        this.age = age
////
////    }
////}
//2- Primary Constructor
//class Musicians(name: String, instrument: String, age: Int) {
//
//    var name: String? = name
//
//    var instrument: String? = instrument
//
//    var age: Int? = age
//
//
//}
//3  Değişkenlere ulaşılmasın istiyorsan (değerleri sınıf içinde tutmak)

//class Musicians(name: String, instrument: String, age: Int) {
//
//
//
//}
//4 //Encapsulation
//class Musicians(name: String,instrument: String,age: Int) {
//
//    //Encapsulation
//    //Propertyler değişkenler sadece burada kullanılabilir
//    //En kolay encapsulation yöntemi private yapmak
//
//    private var name: String? = name
//
//    private var instrument: String? = instrument
//
//    private var age: Int? = age
//}

//5 Değişkenler değiştirlmesin ama okunsun

//Kalıtım yapmak için Musicians'ı open class yapman lazım
open class Musicians(name: String, instrument: String, age: Int) {

    //Getter ve setter
    //Artık okunabilir ama değiştirilemez
    var name: String? = name
        private set
        get

    private var instrument: String? = instrument

    //james.age = 60 X    println(james.age.toString()) OK
    var age: Int? = age
        private set
        get


    //İstediğin sınıfta tamamen private ve tamamen val olan bir constant'ı manuel olarak diğer taraflara aktarabilirsin
    private val bandName: String = "Metallica"

    fun returnBandName(password: String) : String {

        if (password == "Password123") {

            return bandName

        }else {

            return "Wrong Password!"

        }
    }


}