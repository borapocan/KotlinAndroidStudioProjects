package com.borapocan.kotlinoop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Constructor: Sınıfın objelerini inşa eder
        //Her bir obje oluşturulduğunda çağrılır


        //------------- Obje ve Sınıflar ------------

        // User sınıfından myUser isimli bir obje oluşturuldu
//        var myUser = User() //before constructor

//
//            myUser.name = "James"
//            myUser.age = 25
//
//            println(myUser.age.toString())
//
//            println(myUser.name)


        //after constructor

        // myUser objesi user sınıfından oluşturuldu
        var myUser = User("James",50)

        println(myUser.name) //James
        println(myUser.age.toString()) //50

        myUser.name = "Lars"

        myUser.age = 60

        println(myUser.name) //Lars
        println(myUser.age.toString()) //60



        //-----------ENCAPSULATION---------

        // Erişim -> Bir nesnenin verilerini ve özelliklerini dışarıdan erişime kapatmaya denir
        // En kolay encapsulation yöntemi değişkenleri private yapmak
        // Getter ve setter larını private yapıp değiştirebilirsin
        // İstediğin sınıfta tamamen private ve immutable(val) bir değişkeni bile manuel olarak diğer tarafa aktarabilirsin
        // Ex/ fun returnBandName(password: String) : String


        //Musicians 2 de değişkenlere ulaşabiliyodum ama 3 te ulaşamıyorum değerler sınıf içinde kaldı
        //Musicians 4 te private olduğu için ulaşılmıyor

        //Musicians sınıfından james objesi
        var james = Musicians("James","Saxophone", 18)

        //james.age = 23 //musiciansta değişkenleri tanımlamazsan ulaşamazsın age
        //set private olduğu için değiştirilemez

        println(james.age.toString()) //musiciansta değişkenleri tanımlamazsan ulaşamazsın age
        //get metodu private değil o yüzden okunabilir


        println(james.returnBandName("Password123")) //Metallica

        println(james.returnBandName("123123123")) //Wrong Password!

        //------------------INHERITANCE-----------------------
        // Bir sınıf oluşturulduğunda başka bir sınıfta bunun kalıtımını alabilir. Başka bir sınıfta bütün metodları,
        // bütün değişkenleri, bütün propertyleri kullanabilir hale gelebilirsin

        var jimmyHendrix = SuperMusicians("Jimmy Hendrix","Guitar",78)

        println(jimmyHendrix.name)
////        println(jimmmyHendrix.instrument) //İnstrument musiciansta private kullanamazsın
//        jimmyHendrix.name = "JIMMY HENDRIX" //Musicians ta name değişkeninin setter ı private

        jimmyHendrix.returnBandName("Password123") //Şifre doğru olmasına rağmen yanlış çünkü bandName private

        jimmyHendrix.sing()

       // println(jimmyHendrix.bandName) //Musicians'ta private

//        james.sing() //bu fun olmuyor çünkü Musiciansta olan bir fun değil (SuperMusician'a ait)


        //------------------------------Polymorphism--------------------------
        // Aynı ismi kullanarak farklı işlemler yapabilme özelliği
        // Ex/ class Mathematics  fun sum() - fun sum(x,y) - fun sum(x,y,z)


        //-Static Polymorphism-
        // Aynı sınıf içerisinde aynı ismi kullanarak farklı metodlarla farklı sonuçlar almak

        //Mathematics sınıfından mathematics objesi oluşturuldu
        var mathematics = Mathematics()

        println(mathematics.sum())

        println(mathematics.sum(1,2))

        println(mathematics.sum(3,4,5))


        //-Dynamic Polymorphism-
        //Farklı sınıflarda aynı isimle aynı metodu kullanıp farklı sonuçlar almak

        var drake = SuperMusicians("Drake","-",32)
        drake.sing() //Nothing else matters

        var animal = Animal()
        animal.sing() //Animal sound

        var max = Dog()
        max.test() //Animal sound
        max.sing() //Hav Hav

        //-------------- ABSTRACT & INTERFACE -------------------------
        //Abstract: bir obje(instance) oluşturamayacağın bir sınıf
        //Genelde inherit edilsin (kalıtıma maruz kalsın) diye oluşturulan sınıflarda kullanılır
        //Interface: abstract bir sınıf

        println(myUser.info()) // human race

//      var myPeople = People() //Abstract bir class'ın objesi oluşturulamaz

        // Inheritance işvelsel fakat bazı limitasyonları var
        // Inheritance'ı sadece tek bir sınıf için yapabiliyorsun
        // 2 sınıftan aynı anda kalıtım alamıyorsun

        // Interface kullanarak yüzde yüz abstract yapılar oluşturabilir
        // ve birden fazla kalıtım alabilirsin buna inheritance değil "Implementation" deniliyor
        // birden fazla interface'i bir sınıfa kalıtım aldırabiliyorsun


        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName) // roomName'e HouseDecor'dan alıp Piano'da override ettiğim değişken içerisinden ulaştım
        myPiano.information()     // info() ya Instrument içinden ulaştım


        //-----Lamda Expressions-----

        //Normalde
        fun printString(myString: String) {
            println(myString)
        }
        printString("My String")  // Unit

        fun printStringNoUnit(myNoUnit: String) : String {
            println(myNoUnit)
            return myNoUnit
        }
        printStringNoUnit("My String but not Unit") // String


        //with Lambda
        val testString = {myTestString : String -> println(myTestString)}

        testString("My test string")  // (String) -> Unit (döndürme yapmadık)

        //Another lambda example

        val sumLambda = {a: Int, b: Int -> a + b }  // (Int,Int) -> Int (döndürme yaptık)
        println(sumLambda(4,3))  //7

        val sumLambda2 : (Int,Int) -> Int = {a,b-> a + b } // (Int,Int) -> Int (döndürme yaptık) yukarıdakiyle aynı
        println(sumLambda2(4,2)) //6

        //Fakat
        val sumLambdaUnit = {a: Int, b: Int -> println(a + b) } // (Int,Int) -> Unit (döndürme yapmadık)
        sumLambdaUnit(4,1) //5


        //--------İleri seviye Lambda----------
        // Genelde internetten veri indirirken, internetteki işlemlerle çalışırken kullanılır

        //asynchronous - senkronize olmayan işlem
        //Genelde bir işlem uzun sürerken onu beklemeyip diğer işlemlerin devam ettirilmesi için kullanırız

        //callback function //bir işlem yaptın sonucunda bir şey geldi onunla ilgili ne yapılacağını yazdığımız fonksiyon
        //listener function //bir işlemin bittiğini dinleyen fonksiyon
        //completion function //tamamlanma fonksiyonu genelde bu kullanılır


        fun downloadMusicians(url: String, completion: () -> Unit) {

            //url -> download
            //data
            completion()
        }                                                                                     // completion kod bloğu
        downloadMusicians("metallica.com", { println("completed and ready")}) //url: String, completion: () -> Unit

        //ya da
                                                   //Musicians objesi istiyor
        fun downloadMusicians2(url: String, completion: (Musicians) -> Unit) {

            //url -> download
            //data
            val eminem = Musicians("Eminem","Rapper",40)
            completion(eminem)
        }
        //completion içerisinde yazdırılan her neyse, hangi müzisyense onun özelliklerini yazdırmanın 2 yolu var
        //1-
        downloadMusicians2("metallica.com", { println(it.name)}) //Eminem
        //2-
        downloadMusicians2("metallica.com", { musician -> println(musician.name)}) //Eminem








    }
}