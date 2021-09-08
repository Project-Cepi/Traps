package world.cepi.trap.blocks

import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.utils.NamespaceID
import world.cepi.trap.generator.DamageTrapGenerator

object DamageTrap : BlockHandler {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:trap_damage")
    }

    override fun onTouch(touch: BlockHandler.Touch) {
        (touch.touching as? LivingEntity)?.damage(DamageType.ON_FIRE, touch.block.getTag(DamageTrapGenerator.damageKey) ?: return)
    }


}