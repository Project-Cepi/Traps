package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.command.builder.arguments.ArgumentEnum
import net.minestom.server.coordinate.Vec
import net.minestom.server.instance.block.Block
import net.minestom.server.tag.Tag
import world.cepi.kstom.command.arguments.generation.annotations.DefaultBoolean
import world.cepi.kstom.command.arguments.generation.annotations.EnumArgument
import world.cepi.kstom.item.with
import world.cepi.kstom.serializer.VectorSerializer
import world.cepi.trap.blocks.VelocityTrap
import world.cepi.trap.util.VelocityTrapType

@Serializable
data class VelocityTrapGenerator(
    @Serializable(with = VectorSerializer::class)
    val vector: Vec,
    @EnumArgument("FORCE_FIXED", flattenType = ArgumentEnum.Format.LOWER_CASED)
    val trapType: VelocityTrapType
) : TrapGenerator() {

    @Transient
    override val handler = VelocityTrap

    override fun generateBlock(block: Block) =
        block
            .with("velocity", item = vector, serializer = VectorSerializer)
            .withTag(Tag.Integer("fixed"), trapType.ordinal)
}