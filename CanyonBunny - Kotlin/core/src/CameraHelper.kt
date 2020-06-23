package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
//import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.packtpub.libgdx.canyonbunny.objects.AbstractGameObject



//import com.mygdx.game.objects.AbstractGameObject

class CameraHelper {

    private val TAG = "CameraHelper"

    private val MAX_ZOOM_IN = 0.25f
    private val MAX_ZOOM_OUT = 10.0f

    private val FOLLOW_SPEED = 4.0f

    var position: Vector2 = Vector2()
    private var zoom: Float = 1.0f
    var target : AbstractGameObject? = null
    //var target: Sprite? = null

    fun update(deltaTime: Float) {

        if (target != null) {
            return



            position.x = target!!.position.x + target!!.origin.x
            position.y = target!!.position.y + target!!.origin.y

            position.lerp(target!!.position, FOLLOW_SPEED * deltaTime)
            //Prevent camera from moving down too far
            position.y = Math.max(-1f, position.y)

        }
    }

    fun setPosition(x: Float, y: Float) {

            //position.set(x, y) = this
        this.position.set(x, y)

    }
    //var Vector2 : Position() {return position}

    //fun getPosition() : Vector2 {return position}

    fun addZoom(amount: Float) = setZoom(zoom + amount)

    fun setZoom(zoom: Float) {
        val zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT)
    }

    fun getZoom(): Float = zoom

    //fun setTarget(target: AbstractGameObject) {
        //this.target = target
    //}

    //fun getTarget(): Sprite {
      //  target
    //}



    fun hasTarget(): Boolean = target != null

    fun hasTarget(target: Sprite): Boolean {
        return hasTarget() && this.target!!.equals(target)

    }

    fun applyTo(camera: OrthographicCamera) {
            camera.position.x = position.x
            camera.position.y = position.y
            camera.zoom = zoom
            camera.update()
    }

}





