package world.cepi.trap.blocks

import net.kyori.adventure.sound.Sound
import net.minestom.server.data.Data
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.NamespaceID
import net.minestom.server.utils.time.TimeUnit
import world.cepi.trap.generator.FallTrapGenerator

object FallTrap : BlockHandler {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:trap_fall")
    }
//
//    override fun onPlace(placement: BlockHandler.Placement) {
//        placement.instance.setBlock(placement.blockPosition, placement.block.withTag())
//    }

//    override fun onPlace(instance: Instance, blockPosition: BlockPosition, data: Data?) {
//        data!!.set(
//            FallTrapGenerator.currentStageKey,
//            data.get<Int>(FallTrapGenerator.stageAmountKey)
//        )
//    }
//
//    override fun update(instance: Instance, position: BlockPosition, blockData: Data?) {
//
//        val currentStage = blockData!!.get<Int>(FallTrapGenerator.currentStageKey)
//
//        val chunk = instance.getChunkAt(position)
//
//        if (chunk!!.instance.getBlockStateId(position) == Block.AIR.blockId) {
//            chunk.instance.refreshBlockStateId(position, blockData.get<Short>("material")!!)
//        }
//
//        if (currentStage == 0) {
//            blockData.set(FallTrapGenerator.currentStageKey, blockData.get<Int>(FallTrapGenerator.stageAmountKey))
//            chunk.instance.refreshBlockStateId(position, Block.AIR.blockId)
//            return
//        }
//
//        val entities = instance.getChunkEntities(chunk)
//        val anyPlayerOnBlock = entities
//            .filterIsInstance<Player>()
//            .filter { position == it.position.toBlockPosition().subtract(0, 1, 0) }
//
//        if (anyPlayerOnBlock.isEmpty()) return
//
//        blockData.set(FallTrapGenerator.currentStageKey, blockData.get<Int>(FallTrapGenerator.currentStageKey)!! - 1)
//
//        anyPlayerOnBlock.forEach {
//            it.playSound(Sound.sound(SoundEvent.STONE_BREAK, Sound.Source.BLOCK, 1f, 1f))
//        }
//    }
//
//    override fun getUpdateOption() = UpdateOption(10, TimeUnit.TICK)


}