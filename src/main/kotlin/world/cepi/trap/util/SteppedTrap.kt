package world.cepi.trap.util

import net.minestom.server.instance.block.BlockHandler

abstract class SteppedTrap : BlockHandler {

    override fun isTickable() = true

    abstract fun step(step: Step)

    override fun tick(tick: BlockHandler.Tick): Unit = with(tick) {
        instance.entities.filter {
            it.position.x() in tick.blockPosition.x()..tick.blockPosition.x() + 1.0 &&
                    it.position.y() in tick.blockPosition.y()..tick.blockPosition.y() + 2.0 &&
                    it.position.z() in tick.blockPosition.z()..tick.blockPosition.z() + 1.0
        }.forEach { step(Step(block, instance, blockPosition, it)) }
    }

}