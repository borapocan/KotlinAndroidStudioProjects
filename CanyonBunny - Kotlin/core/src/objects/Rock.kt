package com.packtpub.libgdx.canyonbunny.objects

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.packtpub.libgdx.canyonbunny.Assets
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import kotlin.random.Random.*


class Rock(var length: Int = 1) : AbstractGameObject() {
    //class Rock(var length: Int) : AbstractGameObject() {

    private val regEdge = Assets.rock.edge

    private val regMiddle = Assets.rock.middle

    private val FLOAT_CYCLE_TIME : Float = 2.0f

    private val FLOAT_AMPLITUDE : Float = 0.25f

    private var floatCycleTimeLeft : Float = MathUtils.random(0f, FLOAT_CYCLE_TIME / 2f)

    private var floatingDownwards : Boolean

    private var floatTargetPosition : Vector2?

    init {

        var length : Int = 1

        dimension.set(1f,1.5f)

        floatingDownwards = false
        floatCycleTimeLeft = MathUtils.random(0f, FLOAT_CYCLE_TIME / 2f)
        floatTargetPosition = null

        //Start length of this rock

    }

   // fun setLength( length: Int) {

     //   this.length = length
        //Update bounding box for collision detection
       // bounds.set(0f,0f,dimension.x * length, dimension.y)
    //}


    override fun render(batch: SpriteBatch) {

        //var reg : TextureRegion? = null

        var length : Int = 1

        var relX : Float = 0f
        var relY : Float = 0f

        //Draw left edge

        var reg  = regEdge
        relX -= dimension.x / 4f

        //batch.draw(reg.getTexture(), position.x + relX, position.y + relY,
               // origin.x, origin.y, dimension.x / 4, dimension.y, scale.x,
               // scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
               // reg.getRegionWidth(), reg.getRegionHeigth(), false, false)

        batch.draw(reg.texture , position.x + relX , position.y + relY , origin.x , origin.y , relX ,
                dimension.y / 4f, scale.x , scale.y , rotation , reg.regionX , reg.regionY
                , reg.regionWidth , reg.regionHeight , false , false)

        //Draw middle

        relX = 0f

        reg = regMiddle

        for (i in 0 until length) {

            batch.draw(reg.texture , position.x + relX, position.y + relY, origin.x , origin.y ,
                    dimension.x , dimension.y , scale.x , scale.y , rotation , reg.regionX , reg.regionY
                    , reg.regionWidth , reg.regionHeight , false , false)
            relX += dimension.x

        }

        // Draw the right edge

        //Draw right edge

        reg = regEdge

        batch.draw(reg.texture , position.x + relX , position.y + relY , origin.x + dimension.x / 8f , origin.y,
                dimension.x/4f , dimension.y , scale.x , scale.y, rotation , reg.regionX, reg.regionY,  reg.regionWidth,
                reg.regionWidth , true , false)


    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        floatCycleTimeLeft -= deltaTime

        if (floatTargetPosition == null) {
            floatTargetPosition = Vector2(position)

        }
        if (floatCycleTimeLeft <= 0) {
            floatCycleTimeLeft = FLOAT_CYCLE_TIME
            floatingDownwards = !floatingDownwards
            floatTargetPosition!!.y += FLOAT_AMPLITUDE * (floatingDownwards.toString().toFloat())
        }
        position.lerp(floatTargetPosition, deltaTime)
    }

}

