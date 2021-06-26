package world.cepi.trap.listener

import net.minestom.server.event.player.PlayerBlockPlaceEvent
import world.cepi.kstom.item.get
import world.cepi.trap.generator.TrapGenerator
import kotlin.reflect.KClass

object TrapPlaceHandler {

    fun onPlace(event: PlayerBlockPlaceEvent) = with(event) {
        val item = player.getItemInHand(hand)

        val trapGeneratorClass = item.meta.get<KClass<out TrapGenerator>>("blockClass") ?: return

        val trapGenerator = item.meta.get("block", trapGeneratorClass) ?: return

        blockData = trapGenerator.generateData()
        customBlockId = trapGenerator.blockId

    }

}