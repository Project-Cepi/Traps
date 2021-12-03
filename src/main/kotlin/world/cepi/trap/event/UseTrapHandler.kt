package world.cepi.trap.event

import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.util.playSound
import world.cepi.trap.TrapExtension
import world.cepi.trap.generator.trapGenerator

object UseTrapHandler {

    fun used(event: PlayerBlockInteractEvent) = with(event) {
        player.itemInMainHand.trapGenerator?.let {
            player.instance!!.setBlock(blockPosition, it.generateBlock(block).withHandler(it.handler))
            player.sendActionBar(Component.text("Trap set!", TrapExtension.trapColor))
            player.playSound(Sound.sound(
                SoundEvent.BLOCK_TRIPWIRE_CLICK_ON,
                Sound.Source.MASTER,
                0.5f,
                1.1f
            ), player.position)
        }
    }

}