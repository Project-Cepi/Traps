package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.item.Material
import world.cepi.kstom.command.arguments.annotations.DefaultMaterial
import world.cepi.kstom.command.arguments.annotations.DefaultNumber
import world.cepi.kstom.data.data

@Serializable
data class FallTrapGenerator(
    @param:DefaultNumber(3.0)
    val stageAmount: Int = 3,
    @param:DefaultMaterial(Material.MAGMA_BLOCK)
    override val defaultBlock: Material = Material.MAGMA_BLOCK
) : TrapGenerator() {
    override val blockId = 102.toShort()

    override fun generateData() = data {
        this["stageAmount"] = stageAmount
    }

    companion object {
        const val stageAmountKey = "stageAmount"
        const val currentStageKey = "currentStage"
    }
}