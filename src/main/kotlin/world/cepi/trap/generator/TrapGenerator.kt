package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler

@Serializable
sealed class TrapGenerator {
    open fun generateBlock(block: Block): Block = block

    open val handler: BlockHandler
        get() = Field

    companion object {
        val trapGenerators = arrayOf(
            DamageTrapGenerator::class,
            FallTrapGenerator::class,
            PotionTrapGenerator::class
        )
    }

}