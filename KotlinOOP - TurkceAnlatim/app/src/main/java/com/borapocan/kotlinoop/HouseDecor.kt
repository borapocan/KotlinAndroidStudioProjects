package com.borapocan.kotlinoop

interface HouseDecor {

    //100% abstract
    // kotlinde initialize etmene gerek yok (no initialize)
    // javada initialize edilmeli (must be initialized)

    //var roomName = "Kitchen"  // property initializers are not allowed in interfaces  //Javada da tam tersi initialize etmen şart

    var roomName: String
}