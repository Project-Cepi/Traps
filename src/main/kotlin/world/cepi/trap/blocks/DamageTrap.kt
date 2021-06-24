package world.cepi.trap.blocks

import net.minestom.server.data.Data
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.CustomBlock
import net.minestom.server.utils.BlockPosition
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption

object DamageTrap : CustomBlock(Block.MAGMA_BLOCK, "damage-block") {

    override fun getCustomBlockId() = 101.toShort()

    override fun onInteract(player: Player, hand: Player.Hand, blockPosition: BlockPosition, data: Data?) = true

    override fun onDestroy(instance: Instance, blockPosition: BlockPosition, data: Data?) {

    }

    override fun onPlace(instance: Instance, blockPosition: BlockPosition, data: Data?) {

    }

    override fun scheduledUpdate(instance: Instance, position: BlockPosition, blockData: Data?) {
        super.scheduledUpdate(instance, position, blockData)
    }

    override fun handleContact(instance: Instance, position: BlockPosition, touching: Entity) {
        if (touching !is LivingEntity) return

        touching.damage(DamageType.ON_FIRE,1f)
    }

    override fun getUpdateOption() = UpdateOption(10, TimeUnit.TICK)


}