package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block
import world.cepi.kstom.command.arguments.generation.annotations.*
import world.cepi.kstom.data.data

@Serializable
data class DamageTrapGenerator(
    @param:DefaultNumber(1.0)
    val damage: Float = 1f,
    @param:DefaultBlock(Block.MAGMA_BLOCK)
    override val defaultBlock: Block = Block.MAGMA_BLOCK
) : TrapGenerator() {
    override val blockId = 101.toShort()

    override fun generateData() = data {
        this[damageKey] = damage
    }

    companion object {
        const val damageKey = "damage"
    }
}