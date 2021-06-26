package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.item.Material

@Serializable
class DamageTrapGenerator(val damage: Int) : TrapGenerator() {
    override val defaultBlock = Material.MAGMA_BLOCK
    override val blockId = 101.toShort()
}