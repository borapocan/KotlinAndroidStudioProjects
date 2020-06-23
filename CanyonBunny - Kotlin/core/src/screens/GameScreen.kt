package com.packtpub.libgdx.canyonbunny.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.packtpub.libgdx.canyonbunny.WorldController
import com.packtpub.libgdx.canyonbunny.WorldRenderer
import ktx.app.*
import ktx.app.KtxGame
import ktx.app.KtxScreen
import com.packtpub.libgdx.canyonbunny.GamePreferences


class GameScreen(game: KtxGame<KtxScreen>) : AbstractGameScreen(game){

    private val TAG = "GameScreen"

    lateinit var worldRenderer : WorldRenderer

    lateinit var worldController: WorldController

    private var paused : Boolean = false

    override fun render(deltaTime: Float) {
        // Do not update game world when paused
        if (!paused) {
            //Update game world by the time that has passed since last rendered time
            worldController.update(deltaTime)
        }
        //Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f,0xed / 255.0f, 0xff / 255.0f)
        //Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        //Render game world to screen
        worldRenderer.render()
    }

    override fun resize(width: Int, height: Int) {
        worldRenderer.resize(width,height)

    }

    override fun show() {
        worldController = WorldController(game)
        GamePreferences.load()
        worldRenderer = WorldRenderer(worldController)
        Gdx.input.setCatchKey(Input.Keys.BACK,true)
    }

    override fun hide() {
        worldRenderer.dispose()
        Gdx.input.setCatchKey(Input.Keys.BACK,false)
    }

    override fun pause() {
        paused = true
    }

    override fun resume() {
        super.resume()
        //Only called on Android
        paused = false
    }



}