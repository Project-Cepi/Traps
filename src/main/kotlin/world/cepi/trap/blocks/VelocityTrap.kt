package world.cepi.trap.blocks

import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import net.minestom.server.tag.Tag
import net.minestom.server.utils.NamespaceID
import world.cepi.kstom.item.get
import world.cepi.kstom.serializer.VectorSerializer
import world.cepi.trap.util.Step
import world.cepi.trap.util.SteppedTrap
import world.cepi.trap.util.VelocityTrapType

object VelocityTrap : SteppedTrap() {

    override fun getNamespaceId(): NamespaceID = NamespaceID.from("cepi:trap_velocity")

    override fun step(step: Step): Unit = with(step) {
        block.get("velocity", serializer = VectorSerializer)?.let {

            val trapType = VelocityTrapType.values()[block.getTag(Tag.Integer("fixed"))!!]

            entity.velocity = when (trapType) {
                VelocityTrapType.DIRECTIONAL ->
                   entity.velocity.add(entity.position.direction().normalize().mul(it))
                VelocityTrapType.FORCE_FIXED -> it
                VelocityTrapType.FIXED -> entity.velocity.add(it)
            }

            (entity as? Player)?.playSound(
                Sound.sound(
                    SoundEvent.UI_LOOM_TAKE_RESULT,
                    Sound.Source.MASTER,
                    1f,
                    2f
                )
            )
        }
    }
}