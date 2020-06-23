package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.packtpub.libgdx.canyonbunny.objects.AbstractGameObject
import com.badlogic.gdx.math.Vector2

class Mountains(var length : Int) : AbstractGameObject() {


    private var regMountainLeft = Assets.levelDecoration.mountainLeft

    private var regMountainRigth = Assets.levelDecoration.mountainRight


    init {

        dimension.set(10f, 2f)

        // shifts mountain and extend length
        origin.x = -dimension.x * 2

        length += dimension.x.toInt() * 2

    }

    fun updateScrollPosition(camPosition: Vector2){
        position.set(camPosition.x, position.y)
    }

    private fun drawMountain( batch: SpriteBatch , offSetX : Float , offSetY : Float , tintColor : Float,parallaxSpeedX : Float) {

        var reg : TextureRegion? = null

        batch.setColor(tintColor , tintColor , tintColor ,1f)

        var xRel : Float = dimension.x * offSetX

        var yRel : Float = dimension.y * offSetY

        //mountains span the whole level

        var mountainLength : Int = 0

        mountainLength += MathUtils.ceil(length / (2 * dimension.x) * (1 - parallaxSpeedX))

        mountainLength += MathUtils.ceil(0.5f + offSetX)

        for (i in 0 until mountainLength) {

            //mountain left

            reg = regMountainLeft

            batch.draw(reg.texture, origin.x + xRel + position.x * parallaxSpeedX, position.y + origin.y + yRel ,
                    origin.x, origin.y, dimension.x, dimension.y, scale.x , scale.y , rotation, reg.regionX,
                    reg.regionY, reg.regionWidth, reg.regionHeight, false, false)

            xRel += dimension.x

            //mountain right

            reg = regMountainRigth

            batch.draw(reg.texture, origin.x + xRel + position.x * parallaxSpeedX, position.y + origin.y + yRel,
                    origin.x, origin.y, dimension.x, dimension.y, scale.x , scale.y, rotation ,
                    reg.regionX, reg.regionY, reg.regionWidth, reg.regionHeight, false, false)

            xRel += dimension.x

        }

        //reset color to white

        batch.setColor(1f, 1f, 1f, 1f)

    }

    override fun render(batch: SpriteBatch) {

        //distant mountains (dark grey)

        drawMountain(batch , 0.5f , 0.5f , 0.5f,0.8f)

        // 80% distant mountains (gray)

        drawMountain(batch , 0.25f , 0.25f , 0.7f, 0.5f)

        // 50% distant mountains (light gray)

        drawMountain(batch , 0.0f , 0.0f , 0.9f, 0.3f)
    }

}