package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.packtpub.libgdx.canyonbunny.objects.AbstractGameObject


class WaterOverlay(var length: Float) : AbstractGameObject() {

    private var regWaterOverlay = Assets.levelDecoration.waterOverlay

    init {

        dimension.set(length * 10 , 3f)

        origin.x = -dimension.x / 2
    }

    override fun render(batch: SpriteBatch) {

        var reg : TextureRegion? = null

        reg = regWaterOverlay

        batch.draw(reg.texture, position.x + origin.x, position.y + origin.y ,origin.x, origin.y,
                dimension.x , dimension.y , scale.x , scale.y , rotation , reg.regionX , reg.regionY,
                reg.regionWidth , reg.regionHeight, false, false)

    }

}