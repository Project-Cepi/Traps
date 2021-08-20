package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block
import net.minestom.server.tag.Tag
import world.cepi.kstom.command.arguments.generation.annotations.*
import world.cepi.kstom.data.data

@Serializable
data class FallTrapGenerator(
    @param:DefaultNumber(3.0)
    val stageAmount: Int = 3,
    @param:DefaultBlock(Block.STONE_BRICKS)
    override val defaultBlock: Block = Block.STONE_BRICKS
) : TrapGenerator() {
    override val blockId = 102.toShort()

    override fun generateData() = data {
        this["stageAmount"] = stageAmount
    }

    companion object {
        const val stageAmountKey = Tag.Integer("stageAmount")
        val currentStageKey = Tag.Integer("currentStage")
    }
}