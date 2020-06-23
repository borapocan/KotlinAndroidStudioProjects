package com.packtpub.libgdx.canyonbunny.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.packtpub.libgdx.canyonbunny.Assets
import ktx.app.*

abstract class AbstractGameScreen(val game:  KtxGame<KtxScreen>) : KtxScreen {

    override abstract fun render(deltaTime : Float)
    override abstract fun resize(width : Int, height : Int)
    override abstract fun show()
    override abstract fun hide()
    override abstract fun pause()
    override fun resume() {
        Assets
    }

    override fun dispose() {
        Assets.dispose()
    }

}