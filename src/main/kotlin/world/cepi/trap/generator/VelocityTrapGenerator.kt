package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.coordinate.Vec
import net.minestom.server.instance.block.Block
import world.cepi.kstom.item.with
import world.cepi.kstom.serializer.VectorSerializer
import world.cepi.trap.blocks.VelocityTrap

@Serializable
data class VelocityTrapGenerator(
    @Serializable(with = VectorSerializer::class)
    val vector: Vec
) : TrapGenerator() {

    @Transient
    override val handler = VelocityTrap

    override fun generateBlock(block: Block) =
        block.with("velocity", item = vector, serializer = VectorSerializer)
}