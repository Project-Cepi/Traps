package world.cepi.trap.blocks

import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.potion.Potion
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.NamespaceID
import world.cepi.kstom.item.get
import world.cepi.trap.generator.PotionTrapGenerator

object PotionTrap : BlockHandler {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:trap_potion")
    }

    override fun onTouch(touch: BlockHandler.Touch) {

        val potion = touch.block.get<Potion>(PotionTrapGenerator.potionKey) ?: return

        if (!touch.touching.activeEffects.any { activeEffect -> activeEffect.potion.effect == potion.effect }) {
            (touch.touching as? Player)?.playSound(Sound.sound(SoundEvent.BLOCK_TRIPWIRE_CLICK_ON, Sound.Source.BLOCK, 1f, 1f))
        } else {
            (touch.touching as? Player)?.playSound(Sound.sound(SoundEvent.BLOCK_TRIPWIRE_CLICK_OFF, Sound.Source.BLOCK, .3f, 1f))
        }
    }


}