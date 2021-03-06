package world.cepi.trap.blocks

import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.utils.NamespaceID
import world.cepi.trap.generator.DamageTrapGenerator
import world.cepi.trap.util.Step
import world.cepi.trap.util.SteppedTrap
import java.util.*

object DamageTrap : SteppedTrap() {
    override fun getNamespaceId(): NamespaceID =
        NamespaceID.from("cepi:trap_damage")


    override fun step(step: Step): Unit = with(step) {
        (entity as? LivingEntity)
            ?.damage(DamageType.ON_FIRE, block.getTag(DamageTrapGenerator.damageKey) ?: return)
    }


}