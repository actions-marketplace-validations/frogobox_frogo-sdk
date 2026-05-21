package com.frogobox.composeui.fireworks.initializers

import com.frogobox.composeui.fireworks.Particle
import java.util.Random

class ScaleInitializer(
    private val mMinScale: Float,
    private val mMaxScale: Float,
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        val scale = r!!.nextFloat() * (mMaxScale - mMinScale) + mMinScale
        p!!.mScale = scale
    }

}