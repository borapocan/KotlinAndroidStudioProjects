package com.packtpub.libgdx.canyonbunny

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.packtpub.libgdx.canyonbunny.objects.*


class Level(private val fileName : String) {

    private val TAG = "Level"



    enum class BLOCK_TYPE(r: Int, g: Int, b: Int) {

        EMPTY(0, 0, 0), // BLACK
        ROCK(0, 255, 0), // GREEEN
        PLAYER_SPAWNPOINT(255, 255, 255), // WHITE
        ITEM_FEATHER(255, 0, 255), // PURPLE
        ITEM_GOLD_COIN(255, 255, 255); //YELLOW

        private val color: Int = r shl 24 or g shl 16 or b shl 8 or 0xff

        fun sameColor(color: Int) =
                this.color == color

    }

    //OBJECTS

    var rocks: ArrayList<Rock> = arrayListOf()


    // Load image file that represents the level data

    val pixmap: Pixmap = Pixmap(Gdx.files.internal(fileName))

    // Scan pixels from top-left to bottom-right

    var lastPixel: Int = -1

    //Player Character
    var bunnyHead : BunnyHead? = null

    var goldcoins : ArrayList<GoldCoin> = arrayListOf()

    var feathers : ArrayList<Feather> = arrayListOf()




    init {


        for (pixelY in 0 until pixmap.height) {

            for (pixelX in 0 until pixmap.width) {

                val obj: AbstractGameObject? = null

                var offSetHeight: Float = 0f

                //Height grows from bottom to top

                val baseHeight = pixmap.height - pixelY

                //Get color of current pixel as 32-bit RGBA value

                val currentPixel: Int = pixmap.getPixel(pixelX, pixelY)


                //Find matching color value to identify block type at (x,y)
                //point and create the corresponding game object if there is a match

                //empty space

                if (BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {

                    //do nothing
                }

                //rock

                else if (BLOCK_TYPE.ROCK.sameColor(currentPixel)) {

                    if (lastPixel != currentPixel) {

                        val obj = Rock()

                        val heightIncreaseFactor: Float = 0.25f

                        val offSetHeight = -2.5f

                        obj.position.set(pixelX.toFloat(), baseHeight * obj.dimension.y * heightIncreaseFactor + offSetHeight)

                        rocks.add(obj)

                    } else {

                        //rocks[rocks.size - 1].increaseLength(1)
                    }
                }

                //player spawn point

                else if (BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {

                    val obj = BunnyHead()

                    offSetHeight = -3.0f

                    obj.position.set(pixelX.toFloat(), baseHeight * obj.dimension.y + offSetHeight)

                    bunnyHead = obj

                }

                //feather

                else if (BLOCK_TYPE.ITEM_FEATHER.sameColor(currentPixel)) {

                    val obj = Feather()

                    offSetHeight = -1.5f

                    obj.position.set(pixelX.toFloat(), baseHeight * obj.dimension.y + offSetHeight)

                    feathers.add(obj)


                }

                //gold coin

                else if (BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel)) {

                    val obj = GoldCoin()

                    offSetHeight = -1.5f

                    obj.position.set(pixelX.toFloat(), baseHeight * obj.dimension.y + offSetHeight)

                    goldcoins.add(obj)


                }

                //unknown object / pixel color

                else {

                    val r = 0xff and (currentPixel ushr 24) // red color channel

                    val g = 0xff and (currentPixel ushr 16) // green color channel

                    val b = 0xff and (currentPixel ushr 8) // blue color channel

                    val a = 0xff and currentPixel // alpha channel

                    Gdx.app.error(TAG, "Unknown Object at x<" + pixelX + "> y <"
                            + pixelX + ">: r<" + r + "> g <" + g + "> b <" + b + "> a <" + a + ">")

                }

                lastPixel = currentPixel

            }

        }

    }

    //Decoration
    private val clouds = Clouds(pixmap.width.toFloat())

    val mountains = Mountains(pixmap.width)

    private val waterOverlay = WaterOverlay(pixmap.width.toFloat())


    init {
        clouds.position.set(0f, 2f)

        mountains.position.set(-1f, -1f)

        waterOverlay.position.set(0f, -3.75f)

        //free memory
        pixmap.dispose()

        Gdx.app.debug(TAG, "level" + fileName + "loaded")
    }



    fun render( batch : SpriteBatch) {

        //Draw Mountains
        mountains.render(batch)

        //Draw Rocks
        rocks.forEach { it.render(batch) }

        //Draw Water Overlay
        waterOverlay.render(batch)

        //Draw Clouds
        clouds.render(batch)

        //Draw Gold Coins
        goldcoins.forEach { it.render(batch) }

        //Draw Feathers
        feathers.forEach { it.render(batch) }

        //Draw Player Character
        bunnyHead?.render(batch)
    }

    fun update(deltaTime : Float) {

        bunnyHead?.update(deltaTime)

        rocks.forEach { it.update(deltaTime) }

        goldcoins.forEach { it.update(deltaTime) }

        feathers.forEach { it.update(deltaTime) }

        clouds.update(deltaTime)

    }


}
