package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.tag.Tag
import world.cepi.kstom.command.arguments.generation.annotations.*
import world.cepi.trap.blocks.FallTrap

@Serializable
data class FallTrapGenerator(
    @param:DefaultNumber(3.0)
    val stageAmount: Int = 3
) : TrapGenerator() {

    @Transient
    override val handler = FallTrap

    override fun generateBlock(block: Block) =
        block.withTag(stageAmountKey, stageAmount)


    companion object {
        val stageAmountKey = Tag.Integer("stageAmount")
        val currentStageKey = Tag.Integer("currentStage")
    }
}