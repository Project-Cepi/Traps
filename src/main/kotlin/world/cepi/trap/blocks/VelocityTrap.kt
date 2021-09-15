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
            entity.velocity = entity.velocity.add(entity.position.direction().normalize().mul(it))

            (entity as? Player)?.playSound(
                Sound.sound(
                    SoundEvent.ENTITY_WITHER_SHOOT,
                    Sound.Source.MASTER,
                    1f,
                    2f
                )
            )
        }
    }
}