package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.graphics.Color

enum class CharacterSkin(name:String, r:Float, g:Float, b:Float) {

    WHITE("White",1.0f,1.0f,1.0f),
    GRAY("Gray",0.7f,0.7f,0.7f),
    BROWN("Brown",0.7f,0.5f,0.3f);

    val color = Color()

    init {
        color.set(r,g,b,1.0f)
    }

    override fun toString(): String {
        return super.toString()
    }
//    fun getColor():Color {
//        return color
//    }
}