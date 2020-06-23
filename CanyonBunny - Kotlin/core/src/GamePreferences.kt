package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.math.MathUtils

object GamePreferences {
    val TAG : String = "GamePreferences"
    private val prefs = Gdx.app.getPreferences(Constants.PREFERENCES)

    var sound : Boolean = false
    var music : Boolean = false
    var volSound : Float = 0f
    var volMusic : Float = 0f
    var charSkin : Int = 0
    var showFpsCounter : Boolean = false

    fun load() {
        sound = prefs.getBoolean("sound" , true)
        music = prefs.getBoolean("music" , true)
        volSound = MathUtils.clamp(prefs.getFloat("volSound" , 0.5f) , 0f , 0f)
        volMusic = MathUtils.clamp(prefs.getFloat("volMusic", 0.5f),
                0.0f, 1.0f);
        charSkin = MathUtils.clamp(prefs.getInteger("charSkin", 0),
                0, 2);
        showFpsCounter = prefs.getBoolean("showFpsCounter", false)

    }
    fun save() {
        prefs.putBoolean("sound", sound)
        prefs.putBoolean("music", music)
        prefs.putFloat("volSound", volSound)
        prefs.putFloat("volMusic", volMusic)
        prefs.putInteger("charSkin", charSkin)
        prefs.putBoolean("showFpsCounter", showFpsCounter)
        prefs.flush()

    }
}