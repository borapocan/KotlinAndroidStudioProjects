package com.packtpub.libgdx.canyonbunny

import com.packtpub.libgdx.canyonbunny.screens.GameScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Game
import com.packtpub.libgdx.canyonbunny.screens.MenuScreen
import com.badlogic.gdx.assets.AssetManager
import com.packtpub.libgdx.canyonbunny.Assets
import com.badlogic.gdx.*
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import ktx.app.KtxApplicationAdapter
import ktx.app.KtxGame
import ktx.app.KtxScreen
import java.lang.reflect.Type
import kotlin.properties.Delegates


class CanyonBunnyMain() : KtxGame<KtxScreen>() {
    private val TAG = "CanyonBunnyMain"
    lateinit var worldController: WorldController
    private lateinit var worldRenderer: WorldRenderer

    override fun create() {

        //Set Lib gdx log level to DEBUG
        Gdx.app.logLevel = Application.LOG_DEBUG
        //Load Assets

        Assets



        //Start game at menu screen
        setScreen(MenuScreen(this))
        addScreen(MenuScreen(this))

        addScreen(GameScreen(this))
        setScreen<MenuScreen>()


        //Initialize controller and renderer
        //worldController = WorldController()

        //worldRenderer = WorldRenderer(worldController)
        //Game world is activate on start
        //val paused = false
    }

    private fun setScreen(menuScreen: MenuScreen) {

    }

//    override fun render() {
//        //Do not update game world when paused.
//        if (!paused) {
//            //Update game world by the time that has passed since last rendered frame.
//            worldController.update(Gdx.graphics.deltaTime)
//        }
//        //Sets the clear screen color to: Cornflower Blue
//        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f)
//        //Clears the screen
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//        //Render game world to screen
//        worldRenderer.render()
//
//    }

//    override fun resize(width : Int, height : Int) {
//        worldRenderer.resize(width, height)
//    }
/*
    override fun pause() {
        val paused = false

    }

    override fun resume() {
        val paused = false
    }
*/
    override fun dispose() {
        //worldRenderer.dispose()
        Assets.dispose()
    }
}


