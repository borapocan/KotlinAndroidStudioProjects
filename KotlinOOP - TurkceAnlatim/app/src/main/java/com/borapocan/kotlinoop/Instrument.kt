package com.borapocan.kotlinoop

interface Instrument {


    //interface'lerde fonksiyonlar için kod bloğu açmama gerek yok
    //fun information()    diye bırakabiliyorum ama Piano içinde (implement ettiğimiz için) override edip
    // kod bloğunda yazmam gerekiyor yoksa class Piano : HouseDecor, Instruments yazamam

    //Bunu yapmamak için burada kod bloğunda yazmak daha mantıklı
    // bu şekildede implement(fazladan inheritance) yapabilirim çünkü burada fun 'ın tamamı burada tanımlı

    //javada body olamıyor override zorunlu
    //kotlinde ister body olur istemezsen olmaz

    //fun information() //böyle bırakacaksan Piano'da override edip tanımlaman şart



    //ya da burada tanımlayıp Piano'da override edip super kullanarak fun'ı kalıtım alacaksın
    fun information() {

        println("instrument info")
    }
}