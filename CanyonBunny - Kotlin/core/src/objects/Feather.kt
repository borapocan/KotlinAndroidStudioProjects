package com.packtpub.libgdx.canyonbunny.objects

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.packtpub.libgdx.canyonbunny.Assets

class Feather() : AbstractGameObject() {

    private var regFeather = TextureRegion()

    var collected : Boolean = false

    init {

        dimension.set(0.5f, 0.5f)

        regFeather = Assets.feather.feather

        //Set bounding box for collision detection
        bounds.set(0f,0f,dimension.x,dimension.y)

    }

    override fun render(batch: SpriteBatch) {
        if  (collected) return

        var reg = regFeather

        batch.draw(reg.texture, position.x,position.y,origin.x,origin.y, dimension.x, dimension.y,scale.x,scale.y,
                rotation,reg.regionX,reg.regionY,reg.regionWidth,reg.regionHeight,false,false)


    }

    fun getScore() : Int {

        return 250
    }
}