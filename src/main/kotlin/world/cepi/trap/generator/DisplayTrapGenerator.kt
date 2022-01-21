package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.instance.block.Block
import world.cepi.itemextension.item.Item
import world.cepi.itemextension.item.context.ItemOffHandContextParser
import world.cepi.itemextension.item.itemSerializationModule
import world.cepi.kstom.command.arguments.generation.annotations.ParameterContext
import world.cepi.kstom.item.with
import world.cepi.trap.blocks.DisplayTrap

@Serializable
data class DisplayTrapGenerator(
    @ParameterContext(ItemOffHandContextParser::class)
    val item: Item
) : TrapGenerator() {

    @Transient
    override val handler = DisplayTrap

    override fun generateBlock(block: Block) =
        block.with("item", item = item, module = itemSerializationModule)
}