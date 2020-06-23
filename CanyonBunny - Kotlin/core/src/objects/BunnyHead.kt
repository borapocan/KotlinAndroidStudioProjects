package com.packtpub.libgdx.canyonbunny.objects

import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.packtpub.libgdx.canyonbunny.Assets
import com.packtpub.libgdx.canyonbunny.CharacterSkin
import com.packtpub.libgdx.canyonbunny.Constants
import com.packtpub.libgdx.canyonbunny.GamePreferences
import com.badlogic.gdx.Gdx



class BunnyHead() : AbstractGameObject(){

    var dustParticles : ParticleEffect = ParticleEffect()

    val TAG : String = "BunnyHead"

    val JUMP_TIME_MAX : Float = 0.3f

    val JUMP_TIME_MIN : Float = 0.1f

    val JUMP_TIME_OFFSET_FLYING : Float = JUMP_TIME_MAX - 0.018f

    enum class VIEW_DIRECTION { LEFT, RIGHT }

    enum class JUMP_STATE { GROUNDED , FALLING , JUMP_RISING , JUMP_FALLING }

    private var regHead = TextureRegion()

    var viewDirection = VIEW_DIRECTION.RIGHT

    var timeJumping : Float = 0f

    var jumpState = JUMP_STATE.FALLING

    var hasFeatherPowerup = false

    var timeLeftFeatherPowerup = 0f

    var ITEM_FEATHER_POWERUP_DURATION : Float = 9f


    init {
        dimension.set(1f,1f)

        regHead = Assets.bunny.head
        //Center image on game object
        origin.set(dimension.x / 2f ,dimension.y / 2f)
        //Bounding box for collision detection
        bounds.set(0f,0f,dimension.x, dimension.y)
        // Set physics values
        terminalVelocity.set(3.0f, 4.0f)
        friction.set(12.0f, 0.0f)
        acceleration.set(0.0f, -25.0f)
        //View direction
        viewDirection = VIEW_DIRECTION.RIGHT
        //Jump state
        jumpState = JUMP_STATE.FALLING
        timeJumping = 0f
        //Power-ups
        hasFeatherPowerup = false

        timeLeftFeatherPowerup = 0f

        //Particles
        dustParticles.load(Gdx.files.internal("particles/dust.pfx"),Gdx.files.internal("particles"))
    }

    fun setJumping(jumpKeyPressed : Boolean) {
        when ( jumpState ) {
            JUMP_STATE.GROUNDED -> {//Character is standing on a platform
                if (jumpKeyPressed) {
                    //Start counting jump time from the beginning
                    timeJumping = 0f
                    jumpState = JUMP_STATE.JUMP_RISING
                }

            }

            JUMP_STATE.JUMP_RISING -> { //Rising in the air
                if (!jumpKeyPressed) {

                    jumpState = JUMP_STATE.JUMP_FALLING


                }

            }

            JUMP_STATE.FALLING -> { //Falling down


            }

            JUMP_STATE.JUMP_FALLING -> {
                // Falling down after jump
                if( jumpKeyPressed && hasFeatherPowerup ){

                    timeJumping = JUMP_TIME_OFFSET_FLYING

                    jumpState = JUMP_STATE.JUMP_RISING

                }

            }


        }

    }

    fun setFeatherPowerup(pickedUp : Boolean) {

        hasFeatherPowerup = pickedUp

        if ( pickedUp) {

            timeLeftFeatherPowerup = Constants.ITEM_FEATHER_POWERUP_DURATION
        }

    }

    override fun update(deltaTime: Float) {

        dustParticles.update(deltaTime)

        super.update(deltaTime)

        if (velocity.x != 0f) {

            viewDirection = if (velocity.x < 0f) VIEW_DIRECTION.LEFT else VIEW_DIRECTION.RIGHT
        }
        if (timeLeftFeatherPowerup > 0f ) {

            timeLeftFeatherPowerup -= deltaTime

            if (timeLeftFeatherPowerup < 0f ) {
                //disable power-up
                timeLeftFeatherPowerup = 0f

                setFeatherPowerup(false)
            }
        }
    }

    override fun updateMotionY(deltaTime: Float) {

        when ( jumpState ) {
            JUMP_STATE.GROUNDED -> {

                jumpState = JUMP_STATE.FALLING
            }
            JUMP_STATE.JUMP_RISING -> {
                //Keep track of jump time
                timeJumping += deltaTime
                // Jump time left?
                if (timeJumping <= JUMP_TIME_MAX) {
                    //Still jumping
                    velocity.y = terminalVelocity.y
                }

            }
            JUMP_STATE.FALLING -> {
            }

            JUMP_STATE.JUMP_FALLING -> {
                //Add delta times to track jump time
                timeJumping += deltaTime
                //Jump to minimal height if jump key was pressed too short
                if (timeJumping > 0f && timeJumping <= JUMP_TIME_MIN) {
                    //Still jumping
                    velocity.y = terminalVelocity.y
                }
            }

        }
        if ( jumpState != JUMP_STATE.GROUNDED) {

            dustParticles.allowCompletion()

            super.updateMotionY(deltaTime)

        }
    }

    fun hasFeatherPowerup() : Boolean =  hasFeatherPowerup && timeLeftFeatherPowerup > 0f

    override fun render(batch: SpriteBatch) {
        //Apply Skin Color
        batch.setColor(CharacterSkin.values()[GamePreferences.charSkin].color)
        //Set special color when game object has a feather power-up
        if ( hasFeatherPowerup ) {
            batch.setColor(1.0f, 0.8f, 0.0f, 1.0f)
        }
         //Draw image
        val reg = regHead
        batch.draw(reg.texture, position.x, position.y, origin.x, origin.y,dimension.x, dimension.y,scale.x,scale.y, rotation, reg.regionX,
                reg.regionY, reg.regionWidth,reg.regionHeight,viewDirection == VIEW_DIRECTION.LEFT, false)

        //Reset color to white
        batch.setColor(1f,1f,1f,1f)

        //Draw particles
        dustParticles.draw(batch)

    }
    //Duration of feather power-up in seconds


}