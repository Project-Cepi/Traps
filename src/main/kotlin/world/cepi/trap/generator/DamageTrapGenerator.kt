package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.instance.block.Block
import net.minestom.server.tag.Tag
import world.cepi.kstom.command.arguments.generation.annotations.*
import world.cepi.trap.blocks.DamageTrap

@Serializable
data class DamageTrapGenerator(
    @param:DefaultNumber(1.0)
    val damage: Float = 1f
) : TrapGenerator() {

    @Transient
    override val handler = DamageTrap

    override fun generateBlock(block: Block) =
        block.withTag(damageKey, damage)

    companion object {
        val damageKey = Tag.Float("damage")
    }
}