package com.packtpub.libgdx.canyonbunny

//import com.mygdx.game.objects.BunnyHead
//import com.mygdx.game.objects.Feather
//import com.mygdx.objects.GoldCoin
//import com.mygdx.game.objects.Rock
//import com.mygdx.game.screens.MenuScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.Rectangle
import com.packtpub.libgdx.canyonbunny.objects.BunnyHead
import com.packtpub.libgdx.canyonbunny.objects.Feather
import com.packtpub.libgdx.canyonbunny.objects.GoldCoin
import com.packtpub.libgdx.canyonbunny.objects.Rock
import ktx.app.KtxGame
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import kotlin.properties.Delegates
import com.badlogic.gdx.Game
import com.packtpub.libgdx.canyonbunny.screens.MenuScreen
import com.packtpub.libgdx.canyonbunny.Level


class WorldController (val game : KtxGame<KtxScreen>) : KtxInputAdapter {

    //lateinit var testSprites: ArrayList<Sprite>


    lateinit var level : Level

    var lives = Constants.LIVES_START

    var score : Int = 0

    var livesVisual  = lives.toFloat()

    //private var selectedSprite by Delegates.notNull<Int>()

    private val TAG = "WorldController"

    //Rectangles for collision detection

    private val r1 : Rectangle = Rectangle()

    private val r2 : Rectangle = Rectangle()

    lateinit var bunnyHead : BunnyHead

    var timeLeftGameOverDelay by Delegates.notNull<Float>()

    var scoreVisual : Float = score.toFloat()

    fun backToMenu() {
        //switch to menu screen
        game.setScreen<MenuScreen>()
    }

    fun isGameOver() : Boolean {
        return lives < 0
    }
    fun isPlayerInWater() : Boolean {
        return level.bunnyHead!!.position.y < -5f
    }


    private fun onCollisionBunnyHeadWithRock(rock: Rock) {


        val heightDifference : Float = Math.abs(bunnyHead.position.y - ( rock.position.y + rock.bounds.height))

        if (heightDifference > 0.25f ) {

            val hitRightEdge : Boolean = bunnyHead.position.x > ( rock.position.x + rock.bounds.width / 2.0f )

            if ( hitRightEdge) {

                bunnyHead.position.x = rock.position.x + rock.bounds.width

            } else {

                bunnyHead.position.x = rock.position.x - bunnyHead.bounds.width
            }

            return
        }

        when ( bunnyHead.jumpState ) {

            BunnyHead.JUMP_STATE.GROUNDED -> {

                kotlin.run { }

            }

            BunnyHead.JUMP_STATE.JUMP_FALLING -> {

                bunnyHead.position.y = rock.position.y + bunnyHead.bounds.height + bunnyHead.origin.y

                bunnyHead.jumpState = BunnyHead.JUMP_STATE.GROUNDED }

            BunnyHead.JUMP_STATE.JUMP_RISING -> {

                bunnyHead.position.y = rock.position.y + bunnyHead.bounds.height + bunnyHead.origin.y

            }

        }

    }

    private  fun onCollisionBunnyWithGoldCoin(goldCoin: GoldCoin) {

        goldCoin.collected = true

        score += goldCoin.getScore()

        Gdx.app.log(TAG, "Gold coin collected")
    }

    private fun onCollisionBunnyWithFeather(feather: Feather) {

        feather.collected = true

        score += feather.getScore()

        level.bunnyHead?.setFeatherPowerup( true )

        Gdx.app.log(TAG, "Feather collected")

    }

    private fun testCollisions() {

        r1.set(level.bunnyHead?.position!!.x, level.bunnyHead?.position!!.y,level.bunnyHead?.bounds!!.width,
                level.bunnyHead?.bounds!!.height)

        //Test Collision
        for( rock in level.rocks) {

            r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height)

            if (!r1.overlaps(r2)) {

                continue

            }
            onCollisionBunnyHeadWithRock(rock)
            //IMPORTANT: must do all collisions for valid
            //edge testing on rocks

        }
        //Test collision: BunnyHead <-> Gold Coins

        for (goldCoin in level.goldcoins) {
            if (goldCoin.collected) {

                continue
            }
            r2.set(goldCoin.position.x, goldCoin.position.y, goldCoin.bounds.width, goldCoin.bounds.height )
            if (!r1.overlaps(r2)) {

                continue

            }
            onCollisionBunnyWithGoldCoin(goldCoin)

            break
        }

        //Test collision: BunnyHead <-> Feathers
        for (feather in level.feathers) {
            if (feather.collected) {

                continue

            }
            r2.set(feather.position.x, feather.position.y, feather.bounds.width, feather.bounds.height)

            if (!r1.overlaps(r2)) {

                continue

            }
            onCollisionBunnyWithFeather(feather)

            break
        }
    }

    private fun init() {
        //initTestObjects()
        lives = Constants.LIVES_START
        Gdx.input.inputProcessor = this
        timeLeftGameOverDelay = 0f
        initLevel()
        livesVisual = lives.toFloat()
    }

//    private fun initTestObjects() {
//        //Create new array for 5 sprites
//        testSprites
//    }

    private fun initLevel() {

        score = 0
        level = Level(Constants.LEVEL_01)
        cameraHelper.target = level.bunnyHead
        bunnyHead = level.bunnyHead!!
        scoreVisual = score.toFloat()




    }

    fun update(deltaTime: Float) {

        handleDebugInput(deltaTime)

        if (isGameOver()) {
            timeLeftGameOverDelay -= deltaTime
            if (timeLeftGameOverDelay < 0 ) backToMenu()
        } else {
            handleInputGame(deltaTime)
        }

        cameraHelper.update(deltaTime)

        level.update(deltaTime)

        testCollisions()

        if (!isGameOver() && isPlayerInWater()) {
            lives--
            if (isGameOver()) {
                timeLeftGameOverDelay = Constants.TIME_DELAY_GAME_OVER
            }else {
                initLevel()
            }
            level.mountains.updateScrollPosition(cameraHelper.position)
        }
        if (livesVisual > lives) {

            livesVisual = Math.max(lives.toFloat(), livesVisual -1 * deltaTime)
        }
        if (scoreVisual < score) scoreVisual = Math.min(score.toFloat(), scoreVisual + 250f * deltaTime)
    }

    private fun handleDebugInput(deltaTime: Float) {
        if (Gdx.app.type != Application.ApplicationType.Desktop) return

        //Selected Sprite Controls
//        var sprMoveSpeed = 5 * deltaTime
//
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) moveSelectedSprite(-sprMoveSpeed, 0f)
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) moveSelectedSprite(sprMoveSpeed, 0f)
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) moveSelectedSprite(0f, sprMoveSpeed)
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) moveSelectedSprite(0f, -sprMoveSpeed)

        if (cameraHelper.target == level.bunnyHead) {

            //Camera controls (zoom)
            var camMoveSpeed = 5 * deltaTime
            val camMoveSpeedAccelerationFactor = 5f

            //if (cameraHelper.target = null) {     }
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) camMoveSpeed *= camMoveSpeedAccelerationFactor
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveCamera(-camMoveSpeed, 0f)
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveCamera(camMoveSpeed, 0f)
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) moveCamera(0f, camMoveSpeed)
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) moveCamera(0f, -camMoveSpeed)
            if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) cameraHelper.position.set(0f, 0f)

        }


        //Camera Controls (zoom)
        var camZoomSpeed = 1 * deltaTime
        val camZoomSpeedAccelerationFactor = 5f
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) camZoomSpeed *= camZoomSpeedAccelerationFactor
        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) cameraHelper.addZoom(camZoomSpeed)
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) cameraHelper.addZoom(-camZoomSpeed)
        if (Gdx.input.isKeyPressed(Input.Keys.SLASH)) cameraHelper.setZoom(1f)
    }

    private fun moveCamera(x: Float, y: Float) {
        cameraHelper.position.set(cameraHelper.position.x + x, cameraHelper.position.y + y)
        //x += cameraHelper.getPosition().x
        //y += cameraHelper.getPositipn().y
        //cameraHelper.position.set(x,y)

    }


//    private fun moveSelectedSprite(x: Float, y: Float) {
//        testSprites[selectedSprite].translate(x, y)
//    }

    var cameraHelper = CameraHelper()

    init {
        Gdx.input.inputProcessor = this
        //initTestObjects()
        val cameraHelper = CameraHelper()

    }


    override fun keyUp(keycode: Int): Boolean {
        // Reset game world
        if (keycode == Keys.R) {
            initLevel() //?
            Gdx.app.debug(TAG, "Game world resetted")
        }

        //Toggle camera follow
        else if (keycode == Keys.ENTER) {
            cameraHelper.target = null
            Gdx.app.debug(TAG, "Camera follow enabled:")
        }
        else if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
            backToMenu()
        }
//        //Select next sprite
//        else if (keycode == Keys.SPACE) {
//            // ?? selectedSprite = (selectedSprite + 1) % TestSprite.length
//            //Update camera's target to follow the currently selected sprite
//            //if (cameraHelper.target()) {
//            // cameraHelper.target(testSprites[selectedSprite]) }
//            Gdx.app.debug(TAG, "Sprite #" + selectedSprite + "selected")
//        }
//        //Toggle camera follow
//        else if (keycode == Keys.ENTER) {
//            cameraHelper.target = null
//            Gdx.app.debug(TAG, "Camera follow enabled:")
//        }
        return false
    }

    fun handleInputGame(deltaTime: Float) {

        if (cameraHelper.target == level.bunnyHead) {

            //Player movement
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {

                level.bunnyHead!!.velocity.x = -level.bunnyHead!!.terminalVelocity.x

            } else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {

                level.bunnyHead!!.velocity.x = level.bunnyHead!!.terminalVelocity.x

            } else {

                //Execute auto-forward movement on non-desktop platform
                if (Gdx.app.type != Application.ApplicationType.Desktop) {

                    level.bunnyHead!!.velocity.x = level.bunnyHead!!.terminalVelocity.x

                }
            }

            //Bunny Jump
            if (Gdx.input.isTouched || Gdx.input.isKeyPressed(Keys.SPACE)) {

                level.bunnyHead!!.setJumping(true)

            } else {

                level.bunnyHead!!.setJumping(false)

            }
        }
    }
}
