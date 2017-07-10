package com.mygdx.game.fixedstep

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import ktx.assets.toInternalFile
import ktx.scene2d.container

class MyGdxGame : ForkedKtxGame<KtxScreen>(useFixedStep = true) {

    override fun create() {
        super.create()

        addScreen(MyScreen())
        setScreen<MyScreen>()
    }
}

class MyScreen : KtxScreen {

    private var stage = MyStage()

    override fun render(delta: Float) {
        stage.apply {
            viewport.apply()
            act(delta)
            draw()
        }
    }
}

class MyStage : Stage() {
    init {
        addActor(
                container {
                    setFillParent(true)
                    center()
                    add(RotatingActor())
                }
        )
    }
}

class RotatingActor : Image(Texture("badlogic.jpg".toInternalFile())) {
    var rotationInDegrees = 0F

    init {
        setOrigin(Align.center)
    }

    override fun act(delta: Float) {
        super.act(delta)

        rotationInDegrees = (rotationInDegrees + 3) % 360
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        rotation = rotationInDegrees
    }
}
