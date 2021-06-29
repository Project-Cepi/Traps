package world.cepi.trap.blocks

import net.kyori.adventure.sound.Sound
import net.minestom.server.data.Data
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.CustomBlock
import net.minestom.server.potion.Potion
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.BlockPosition
import net.minestom.server.utils.time.TimeUnit
import net.minestom.server.utils.time.UpdateOption
import world.cepi.trap.generator.PotionTrapGenerator

object PotionTrap : CustomBlock(Block.MAGMA_BLOCK, "damage-block") {

    override fun getCustomBlockId() = 103.toShort()

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

        val potion = blockData!!.get<Potion>(PotionTrapGenerator.potionKey)!!

        entityBoundingBoxes.forEach {

            if (!it.activeEffects.any { activeEffect -> activeEffect.potion.effect == potion.effect }) {
                (it as? Player)?.playSound(Sound.sound(SoundEvent.TRIPWIRE_CLICK_ON, Sound.Source.BLOCK, 1f, 1f))
            } else {
                (it as? Player)?.playSound(Sound.sound(SoundEvent.TRIPWIRE_CLICK_ON, Sound.Source.BLOCK, .3f, 1f))
            }

            it.addEffect(potion)
        }
    }

    override fun getUpdateOption() = UpdateOption(10, TimeUnit.TICK)


}