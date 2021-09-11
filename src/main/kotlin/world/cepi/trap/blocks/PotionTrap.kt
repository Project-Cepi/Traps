package world.cepi.trap.blocks

import kotlinx.serialization.serializer
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.potion.Potion
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.NamespaceID
import world.cepi.kstom.item.get
import world.cepi.kstom.serializer.PotionSerializer
import world.cepi.trap.generator.PotionTrapGenerator
import world.cepi.trap.util.Step
import world.cepi.trap.util.SteppedTrap

object PotionTrap : SteppedTrap() {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:trap_potion")
    }

    override fun step(step: Step) = with(step) {
        val potion = block.get(PotionTrapGenerator.potionKey, serializer = PotionSerializer) ?: return

        if (!entity.activeEffects.none { activeEffect -> activeEffect.potion.effect == potion.effect }) {
            (entity as? Player)
                ?.playSound(Sound.sound(SoundEvent.BLOCK_TRIPWIRE_CLICK_ON, Sound.Source.BLOCK, 1f, 1f))
            entity.addEffect(potion)
        }
    }


}