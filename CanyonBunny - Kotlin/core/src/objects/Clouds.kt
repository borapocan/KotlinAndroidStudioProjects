package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.packtpub.libgdx.canyonbunny.objects.AbstractGameObject
import kotlin.random.Random
import kotlin.random.nextInt

class Clouds(var length: Float) : AbstractGameObject() {

    private class Cloud(var regCloud: TextureRegion) : AbstractGameObject() {


        override fun render(batch: SpriteBatch) {

            var reg: TextureRegion = regCloud

            batch.draw(reg.texture, position.x + origin.x, position.y + origin.y, origin.x, origin.y,
                    dimension.x, dimension.y, scale.x, scale.y, rotation, reg.regionX, reg.regionY,
                    reg.regionWidth, reg.regionHeight, false, false)

        }
    }

    private val distFac: Int = 5

    private val numClouds = length / distFac

    private var clouds: ArrayList<Clouds> = arrayListOf()

    private val regClouds: ArrayList<TextureRegion> = arrayListOf()


    init {

        dimension.set(3.0f, 1.5f)

        regClouds.add(Assets.levelDecoration.cloud01)

        regClouds.add(Assets.levelDecoration.cloud02)

        regClouds.add(Assets.levelDecoration.cloud03)

    }

    private fun spawnCloud(): Cloud {

        //select random cloud image
        val cloud = Cloud(regClouds.random())

        cloud.dimension.set(dimension)

        //position
        val pos = Vector2()

        //position after end of level
        pos.x = length + 10

        //base position
        pos.y += 1.75f

        //random additional position
        pos.y += MathUtils.random(0.0f , 0.2f) * randBool()

        cloud.position.set(pos)

        //speed
        var speed : Vector2 = Vector2()
        speed.x += 0.5f //base speed

        //random additional speed
        speed.x += MathUtils.random(0.0f, 0.75f)
        cloud.terminalVelocity.set(speed)
        speed.x *= -1 //move left
        cloud.velocity.set(speed)

        return cloud

    }

    private fun randBool() : Int{

        val rand = Random.nextInt(IntRange(-10, 10))

        return if(rand > 0) 1 else 0

    }

    override fun render(batch: SpriteBatch) {

        clouds.forEach { it.render(batch) }
    }

    override fun update(deltaTime: Float) {
        for (i : Int in clouds.size-1 until 0) {

            val cloud = clouds.get(i)

            cloud.update(deltaTime)

            if (cloud.position.x < -10) {
                //cloud moved outside of the world.
                //destroy and spawn new cloud at end of level.
                clouds.removeAt(i)

                cloud.spawnCloud()


            }

        }
    }
}





