package com.packtpub.libgdx.canyonbunny.screens.transitions

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface ScreenTransition {

    var getDuration : Float


    fun render(batch: SpriteBatch, currScreen: Texture, nextScreen: Texture, alpha: Float)

}