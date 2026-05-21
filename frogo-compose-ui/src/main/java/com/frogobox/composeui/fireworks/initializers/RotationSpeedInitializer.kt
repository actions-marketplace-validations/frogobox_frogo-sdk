package com.frogobox.composeui.fireworks.initializers

import com.frogobox.composeui.fireworks.Particle
import java.util.Random

class RotationSpeedInitializer(
    private val mMinRotationSpeed: Float,
    private val mMaxRotationSpeed: Float
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        val rotationSpeed =
            r!!.nextFloat() * (mMaxRotationSpeed - mMinRotationSpeed) + mMinRotationSpeed
        p!!.mRotationSpeed = rotationSpeed
    }

}