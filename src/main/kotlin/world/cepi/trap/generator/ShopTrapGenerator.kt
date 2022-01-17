package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.instance.block.Block
import net.minestom.server.tag.Tag
import world.cepi.itemextension.item.Item
import world.cepi.kstom.command.arguments.context.OffItemContextParser
import world.cepi.kstom.command.arguments.generation.annotations.ParameterContext
import world.cepi.kstom.item.with
import world.cepi.trap.blocks.ShopTrap

@Serializable
data class ShopTrapGenerator(
    val price: Int,
    @ParameterContext(OffItemContextParser::class)
    val item: Item
) : TrapGenerator() {

    @Transient
    override val handler = ShopTrap

    override fun generateBlock(block: Block) =
        block
            .withTag(Tag.Integer("price"), price)
            .with("item", item = item, serializer = Item.serializer())
}