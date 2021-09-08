package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block
import net.minestom.server.tag.Tag
import world.cepi.kstom.command.arguments.generation.annotations.*

@Serializable
data class FallTrapGenerator(
    @param:DefaultNumber(3.0)
    val stageAmount: Int = 3
) : TrapGenerator() {

    override fun generateBlock(block: Block) =
        block.withTag(stageAmountKey, stageAmount)


    companion object {
        val stageAmountKey = Tag.Integer("stageAmount")
        val currentStageKey = Tag.Integer("currentStage")
    }
}