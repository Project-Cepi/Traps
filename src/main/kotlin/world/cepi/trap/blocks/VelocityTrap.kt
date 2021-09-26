package world.cepi.trap.blocks

import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.NamespaceID
import world.cepi.kstom.item.get
import world.cepi.kstom.serializer.VectorSerializer
import world.cepi.trap.util.Step
import world.cepi.trap.util.SteppedTrap

object VelocityTrap : SteppedTrap() {

    override fun getNamespaceId(): NamespaceID = NamespaceID.from("cepi:trap_velocity")

    override fun step(step: Step): Unit = with(step) {
        block.get("velocity", serializer = VectorSerializer)?.let {

            val isFixed = block.get<Boolean>("fixed")!!

            if (isFixed) {
                entity.velocity = it
            } else {
                entity.velocity = entity.velocity.add(entity.position.direction().normalize().mul(it))
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