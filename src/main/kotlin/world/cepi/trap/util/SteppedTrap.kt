package world.cepi.trap.util

import net.minestom.server.instance.block.BlockHandler

abstract class SteppedTrap : BlockHandler {

    override fun isTickable() = true

    abstract fun step(step: Step)

    open fun tickStep(tick: BlockHandler.Tick) {}

    override fun tick(tick: BlockHandler.Tick): Unit = with(tick) {
        instance.entities.filter { it.boundingBox.intersectWithBlock(blockPosition.withY { it + 1 })
        }.forEach { step(Step(block, instance, blockPosition, it)) }
        tickStep(tick)
    }

}