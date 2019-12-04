package com.example.mvvmrom

import android.app.Application
import com.example.mvvmrom.room.AppDatabase

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        AppDatabase.getInstanse().close()
    }
    ////https://www.youtube.com/watch?v=ARpn-1FPNE4
}
/*

fun main(){
 val cobalt=Cobalt()
 val malibu=Malibu()

    cobalt.maxSpeed()
    malibu.maxSpeed()

}
class Cobalt():Car{

    override fun way(): Int {
     return 100_000
    }

    override fun maxSpeed() {
        println("220 km/h")
    }

    override fun colors() {
      println("BLACK")
    }
}

class Malibu():Car{
    override fun maxSpeed() {

    }

    override fun colors() {

    }

    override fun way(): Int {
        return 10_000
    }
}

interface Car{
    fun maxSpeed()
    fun colors()
    fun way():Int
}

interface uchish{
 fun balandlig()
 fun tezlik()
}
class Tovuq:uchish{

    override fun tezlik() {

    }

    override fun balandlig() {

    }

}
abstract class Uchish(){

    fun main(){

    }


    abstract fun tezlik()

    fun balandlik(){

    }
}
class Samaloyot :Uchish(){

    override fun tezlik() {

    }

}*/
