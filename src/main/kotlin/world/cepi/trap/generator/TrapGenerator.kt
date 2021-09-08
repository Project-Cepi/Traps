package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block

@Serializable
sealed class TrapGenerator {
    open fun generateBlock(block: Block): Block = block

    companion object {
        val trapGenerators by lazy { TrapGenerator::class.sealedSubclasses }
    }

}