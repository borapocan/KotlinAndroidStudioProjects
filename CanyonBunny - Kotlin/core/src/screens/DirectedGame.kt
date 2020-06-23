package com.packtpub.libgdx.canyonbunny.screens


import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.packtpub.libgdx.canyonbunny.screens.transitions.ScreenTransition

abstract class DirectedGame : ApplicationListener {

    private var init : Boolean? = null
    private lateinit var currScreen: AbstractGameScreen
    private lateinit var nextScreen: AbstractGameScreen
    private lateinit var currFbo: FrameBuffer
    private lateinit var nextFbo: FrameBuffer
    private lateinit var batch: SpriteBatch
    private var t: Float? = null
    private lateinit var screenTransition: ScreenTransition


    fun setScreen(screen: AbstractGameScreen, screenTransition: ScreenTransition) {


        var w: Int = Gdx.graphics.width

        var h: Int = Gdx.graphics.height

        if (!init!!) {

            currFbo = FrameBuffer(Format.RGB888, w, h, false)

            nextFbo = FrameBuffer(Format.RGB888, w, h, false)

            batch = SpriteBatch()

            init = true
        }

        // start new transition
        nextScreen = screen

        nextScreen.show() //activate next screen

        nextScreen.resize(w, h)

        nextScreen.render(0f) //let screen update() once

        if (currScreen != null) currScreen.pause()

        nextScreen.pause()

        Gdx.input.inputProcessor = null //disable input

        this.

        t = 0f



    }

    override fun render() {
        //get delta time and ensure an upper limit of one 60th second
        val deltaTime: Float = Math.min(Gdx.graphics.deltaTime, 1.0f / 60.0f)

        if (nextScreen == null) {
            //no ongoing transition
            if (currScreen != null) currScreen.render(deltaTime)
        } else {
            //ongoing transition
            var duration: Float = 0f

            if (screenTransition != null)

                duration = screenTransition.getDuration
            //update progress of ongoing transition
            t = Math.min(t!! + deltaTime, duration)

            if (screenTransition == null || t!! >= duration) {
                //no transition effect set or transition has just finished
                if (currScreen != null) currScreen.hide()

                nextScreen.resume()
                //enable input for next screen
                Gdx.input.inputProcessor

                //switch screens
                currScreen = nextScreen
                nextScreen ?: null
                screenTransition?: null
            }
            else {
                //render screen to FBOs
                currFbo.begin()

                if (currScreen != null) currScreen.render(deltaTime)

                currFbo.end()

                nextFbo.begin()

                nextScreen.render(deltaTime)

                nextFbo.end()
                //render transition effect to screen
                var alpha: Float = t!! / duration

                screenTransition.render(batch, currFbo.colorBufferTexture, nextFbo.colorBufferTexture, alpha)

            }


        }


    }


}