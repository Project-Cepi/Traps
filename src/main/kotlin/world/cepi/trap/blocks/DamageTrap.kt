package world.cepi.trap.blocks

import net.minestom.server.collision.BoundingBox
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
import world.cepi.trap.generator.DamageTrapGenerator

object DamageTrap : CustomBlock(Block.MAGMA_BLOCK, "damage-block") {

    override fun getCustomBlockId() = 101.toShort()

    override fun onInteract(player: Player, hand: Player.Hand, blockPosition: BlockPosition, data: Data?) = true

    override fun onDestroy(instance: Instance, blockPosition: BlockPosition, data: Data?) {

    }

    override fun onPlace(instance: Instance, blockPosition: BlockPosition, data: Data?) {

    }

    override fun update(instance: Instance, position: BlockPosition, blockData: Data?) {

        val chunk = instance.getChunkAt(position)
        val entities = instance.getChunkEntities(chunk)
        val entityBoundingBoxes = entities
            .filter { !it.isSneaking }
            .filterIsInstance<LivingEntity>()
            .filter { position == it.position.toBlockPosition().subtract(0, 1, 0) }

        entityBoundingBoxes.forEach {
            it.damage(DamageType.ON_FIRE, blockData!!.get<Float>(DamageTrapGenerator.damageKey)!!)
        }
    }

    override fun getUpdateOption() = UpdateOption(10, TimeUnit.TICK)


}