package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler

@Serializable
sealed class TrapGenerator {
    open fun generateBlock(block: Block): Block = block

    open val handler: BlockHandler
        get() = throw IllegalStateException("GET for handler not implemented!")

    companion object {
        val trapGenerators = arrayOf(
            DamageTrapGenerator::class,
            FallTrapGenerator::class,
            VelocityTrapGenerator::class,
            PotionTrapGenerator::class
        )
    }

}