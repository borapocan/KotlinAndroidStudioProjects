package com.packtpub.libgdx.canyonbunny

    class Constants {
        companion object {
            //Visible game world is 5 meters wide
            const val VIEWPORT_WIDTH = 5.0f

            //Visible game world is 5 meters tall
            const val VIEWPORT_HEIGHT = 5.05f

            //GUI Width
            const val VIEWPORT_GUI_WIDTH = 800.0f

            //GUI Height
            const val VIEWPORT_GUI_HEIGHT = 480.0f

            //Location of description file for texture atlas
            const val TEXTURE_ATLAS_OBJECTS = "android/assets/images/canyonbunny.pack"

            //Location of image file for level 01
            const val LEVEL_01 = "android/assets/levels/level-01.png"

            //Amount of extra lives at level start
            const val LIVES_START = 3

            const val ITEM_FEATHER_POWERUP_DURATION = 9f

            //Delay after game over
            const val TIME_DELAY_GAME_OVER : Float = 3f

            const val TEXTURE_ATLAS_UI = "images/canyonbunny-ui.pack.atlas"

            const val TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas"

            //Location of description file for skins
            const val SKIN_LIBGDX_UI = "images/uiskin.json"

            const val SKIN_CANYONBUNNY_UI = "images/canyonbunny-ui.json"

            const val PREFERENCES = "canyonbunny.preferences"

        }
    }


