package com.packtpub.libgdx.canyonbunny.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.packtpub.libgdx.canyonbunny.CanyonBunnyMain
import com.badlogic.gdx.tools.texturepacker.TexturePacker
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings
import com.badlogic.gdx.tools.texturepacker.TexturePacker.process
import com.badlogic.gdx.tools.texturepacker.TexturePackerFileProcessor

import kotlin.RuntimeException

object DesktopLauncher {
    private var rebuildAtlas = true
    private var drawDebugOutline = true
    val ex = RuntimeException()

    @JvmStatic
    fun main(arg: Array<String>) {


//        if (rebuildAtlas) {
//
//            val settings = Settings()
//            settings.maxWidth = 1024 * 2
//            settings.maxHeight = 1024 * 2
//            settings.duplicatePadding = false
//            settings.debug = drawDebugOutline
//            TexturePacker.process(settings, "Android_Kotlin/CanyonBunny/desktop/assets-raw/images", "Android_Kotlin/CanyonBunny/android/assets/images"
//                    , "CanyonBunny/desktop/images/canyonbunny.png")
//            TexturePacker.process(settings, "desktop/assets-raw/images/images-ui", "android/assets/images",
//                    "canyonbunny-ui")
//        }/* else {
//            throw java.lang.RuntimeException("Error packing images bro...")
//            throw java.lang.IllegalArgumentException("cant load")
//        }*/


        val config = LwjglApplicationConfiguration()
        config.title = "CanyonBunny"
        config.width = 800
        config.height = 400

        LwjglApplication(CanyonBunnyMain(), config)
    }

}

