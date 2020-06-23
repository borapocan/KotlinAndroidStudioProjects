package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.Input.Keys.V
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils


class WorldRenderer(val worldController: WorldController) : Disposable {


    private var cameraGUI = OrthographicCamera()
    private var camera: OrthographicCamera = OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT)
    private var batch: SpriteBatch = SpriteBatch()

    init {
        cameraGUI.position.set(0f,0f,0f)
        cameraGUI.setToOrtho(true) // flip y-axis
        cameraGUI.update()
    }

    //private var worldController: WorldController = WorldController()

    fun worldRenderer(worldController: WorldController) {}


    private fun renderWorld(batch: SpriteBatch) {

        worldController.cameraHelper.applyTo(camera)
        batch.projectionMatrix = camera.combined
        batch.begin()
        worldController.level.render(batch)
        batch.end()
    }

    fun render() {

        renderWorld(batch)

        renderGui(batch)
    }

//    private fun renderTestObjects(batch: SpriteBatch) {
//        worldController.cameraHelper.applyTo(camera)
//        batch.projectionMatrix = camera.combined
//        batch.begin()
//        batch.end()
//    }

    fun renderGuiScore(batch: SpriteBatch) {
        val x : Float = -15f
        val y : Float = -15f
        var offsetX = 50f
        var offsetY = 50f
        if (worldController.scoreVisual<worldController.score) {
            val shakeAlpha : Long = System.currentTimeMillis() % 360
            val shakeDist : Float = 1.5f
            offsetX += (MathUtils.sinDeg(shakeAlpha * 2.2f) * shakeDist)
            offsetY += MathUtils.sinDeg(shakeAlpha * 2.9f) * shakeDist
        }

        batch.draw(Assets.goldCoin.goldCoin,x,y,50f,50f,100f,100f,0.35f,-0.35f,0f)

        Assets.fonts.defaultBig.draw(batch,"" + worldController.score, x + 75f, y + 37f)

    }

    fun renderGuiExtraLive(batch: SpriteBatch) {
        val x : Float = cameraGUI.viewportWidth - 50 - Constants.LIVES_START * 50
        val y : Float = -15f

        for (i in 0 until Constants.LIVES_START) {
            if ( worldController.lives > i ) {
                batch.setColor(0.5f,0.5f,0.5f,0.5f)
            }
            batch.draw(Assets.bunny.head, x + i * 50f, y , 50f, 50f,120f,100f, 0.35f, -0.35f, 0f)
            batch.setColor(1f,1f,1f,1f)
        }
        if (worldController.lives >= 0 && worldController.livesVisual > worldController.lives) {

            var i : Int = worldController.lives

            var alphaColor = Math.max(0f, worldController.livesVisual - worldController.lives - 0.5f)

            var alphaScale = 0.35f * (2 + worldController.lives - worldController.livesVisual) * 2

            var alphaRotate = -45 * alphaColor

            batch.setColor(1.0f, 0.7f, 0.7f, alphaColor)

            batch.draw(Assets.bunny.head, x + i * 50, y,50f,50f,120f,100f, alphaScale, -alphaScale, alphaRotate)

            batch.setColor(1f,1f,1f,1f)
        }
    }

    fun renderGuiFpsCounter(batch: SpriteBatch) {
        val x : Float = cameraGUI.viewportWidth - 55f
        val y : Float = cameraGUI.viewportHeight - 15f
        val fps : Int = Gdx.graphics.framesPerSecond
        val fpsFont : BitmapFont = Assets.fonts.defaultNormal
        if (fps >= 45) {
            //45 or more FPS show up in green
            fpsFont.setColor(0f, 1f, 0f, 1f)
        }else if (fps >= 30) {
            //30 or more FPS show up in yellow
            fpsFont.setColor(1f, 1f, 0f, 1f)
        }else {
            //less than 30 FPS show up in red
            fpsFont.setColor(1f,0f,0f,1f)
        }
        fpsFont.draw(batch, "FPS: " + fps, x, y)
        fpsFont.setColor(1f,1f,1f,1f) //white
    }

    fun renderGui(batch: SpriteBatch) {
        batch.projectionMatrix = cameraGUI.combined
        batch.begin()
        //draw collected gold coins + text
        //(anchored to top left edge)
        renderGuiScore(batch)
        //draw collected feather icon (anchored to top left edge)
        renderGuiFeatherPowerup(batch)
        //draw extra lives icon + text ( anchored to top right edge)
        renderGuiExtraLive(batch)
        //draw FPS text (anchored to bottom right edge)
        if (GamePreferences.showFpsCounter)
        renderGuiFpsCounter(batch)
        //draw game over text
        renderGuiGameOverMessage(batch)
        batch.end()
    }


    fun resize(width: Int, height: Int) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width
        camera.update()
        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT
        cameraGUI.viewportWidth = Constants.VIEWPORT_GUI_HEIGHT / height * width
        cameraGUI.position.set(cameraGUI.viewportWidth / 2f, cameraGUI.viewportHeight / 2f , 0f)
        cameraGUI.update()
    }
    fun renderGuiGameOverMessage(batch: SpriteBatch) {

        val x : Float = cameraGUI.viewportWidth / 2f

        val y : Float = cameraGUI.viewportHeight / 2f

        if (worldController.isGameOver()) {

            val fontGameOver : BitmapFont = Assets.fonts.defaultBig

            fontGameOver.setColor(1f,0.75f,0.25f,1f)

            fontGameOver.draw(batch,"GAME OVER",x,y,0f,Align.center,true)

            fontGameOver.setColor(1f,1f,1f,1f)
        }
    }
    fun renderGuiFeatherPowerup(batch: SpriteBatch) {
        val x = -15f
        val y = 30f
        val timeLeftFeatherPowerup : Float = worldController.level.bunnyHead!!.timeLeftFeatherPowerup
        if (timeLeftFeatherPowerup > 0 ) {
            //Start icon fade in/out if the left power-up time is less than 4 seconds.
            //The fade interval is set to 5 changes per second
            if (timeLeftFeatherPowerup < 4 ) {
                if(((timeLeftFeatherPowerup.toInt() * 5) % 2) != 0) {
                    batch.setColor(1f,1f,1f,0.5f)
                }
            }
            batch.draw(Assets.feather.feather,x,y,50f,50f,100f,100f,0.35f,-0.35f,0f)
            batch.setColor(1f,1f,1f,1f)
            Assets.fonts.defaultSmall.draw(batch,"" + timeLeftFeatherPowerup.toInt(),x+60f,y+57f)
        }
    }

    override fun dispose() {
        batch.dispose()

    }
}



