package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.item.Material
import world.cepi.kstom.command.arguments.annotations.DefaultMaterial
import world.cepi.kstom.command.arguments.annotations.DefaultNumber
import world.cepi.kstom.data.data

@Serializable
data class DamageTrapGenerator(
    @param:DefaultNumber(1.0)
    val damage: Float = 1f,
    @param:DefaultMaterial(Material.MAGMA_BLOCK)
    override val defaultBlock: Material = Material.MAGMA_BLOCK
) : TrapGenerator() {
    override val blockId = 101.toShort()

    override fun generateData() = data {
        this["damage"] = damage
    }
}