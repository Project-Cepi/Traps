package world.cepi.trap.listener

import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.instance.block.Block
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag
import world.cepi.kstom.item.get
import world.cepi.trap.generator.TrapGenerator
import kotlin.reflect.KClass

object TrapPlaceHandler {

    fun onPlace(event: PlayerBlockPlaceEvent) = with(event) {
        val item = player.getItemInHand(hand)

        val rawClassName = item.meta.getTag(Tag.String("blockClass"))

        val trapGeneratorClass = TrapGenerator.trapGenerators.firstOrNull {
            it.simpleName!! == rawClassName
        } ?: return

        val trapGenerator = item.meta.get("block", trapGeneratorClass) ?: return

        block = block.withHandler()
        blockStateId = item.meta.getTag(Tag.Short("material"))!!

        blockData = trapGenerator.generateData().also {
            it.set("material", blockStateId)
        }
        customBlockId = trapGenerator.blockId

    }

}