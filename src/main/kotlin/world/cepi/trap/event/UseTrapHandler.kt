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
        player.itemInMainHand.trapGenerator?.handler?.let {
            block.withHandler(it)
            player.sendActionBar(Component.text("Trap set!", TrapExtension.trapColor))
            player.playSound(Sound.sound(
                SoundEvent.BLOCK_NOTE_BLOCK_PLING,
                Sound.Source.MASTER,
                1f,
                2f
            ), player.position)
        }
    }

}