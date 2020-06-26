package com.borapocan.kotlinoop

//class Piano : HouseDecor{ yazmak için memberları implement etmen şart member= var roomName

class Piano : HouseDecor, Instrument{

    var brand: String? = null

    var digital: Boolean? = null

    override var roomName: String  //override ederek implement ettik
        get() = "Kitchen"          // initialize ederek kitchen değeri atadık
        set(value) {}       //böylece biri Piano objesi oluşturduğunda roomName'i alınca "kitchen" değerini görecek



    override fun information() {
        super.information() //instrument info
    }
}