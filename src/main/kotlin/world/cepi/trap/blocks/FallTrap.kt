package world.cepi.trap.blocks

import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockFace
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.network.packet.server.play.BlockBreakAnimationPacket
import net.minestom.server.utils.NamespaceID
import world.cepi.kstom.util.sendBlockDamage
import world.cepi.kstom.util.sendBreakBlockEffect
import world.cepi.trap.generator.FallTrapGenerator
import world.cepi.trap.util.Step
import world.cepi.trap.util.SteppedTrap
import kotlin.math.roundToInt

object FallTrap : SteppedTrap() {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:trap_fall")
    }

    override fun step(step: Step) {

        if (step.block.isAir) return

        if (step.block.hasTag(FallTrapGenerator.currentTick)) return

        step.instance.setBlock(
            step.blockPosition,
            step.block
                .withTag(FallTrapGenerator.currentTick, 0)
        )

    }

    override fun tickStep(tick: BlockHandler.Tick) {

        if (!tick.block.hasTag(FallTrapGenerator.currentTick)) return

        val currentTick = tick.block.getTag(FallTrapGenerator.currentTick)!!
        val ticks = tick.block.getTag(FallTrapGenerator.ticks)!!
        val regeneration = tick.block.getTag(FallTrapGenerator.regeneration)!!

        if (tick.block.isAir && regeneration == currentTick) {
            tick.instance.setBlock(
                tick.blockPosition,
                tick.block.getTag(FallTrapGenerator.block)!!.withHandler(this)
                    .withTag(FallTrapGenerator.ticks, ticks)
                    .withTag(FallTrapGenerator.regeneration, regeneration)
                    .withTag(FallTrapGenerator.block, tick.block)
                    .withTag(FallTrapGenerator.entityID, tick.block.getTag(FallTrapGenerator.entityID)!!)
                    .withTag(FallTrapGenerator.shouldDestroyAdjacentSameMaterial, tick.block.getTag(FallTrapGenerator.shouldDestroyAdjacentSameMaterial))
            )
            return
        }

        if (!tick.block.isAir && ticks == currentTick) {
            tick.instance.setBlock(
                tick.blockPosition,
                Block.AIR.withHandler(this)
                    .withTag(FallTrapGenerator.currentTick, 0)
                    .withTag(FallTrapGenerator.ticks, ticks)
                    .withTag(FallTrapGenerator.regeneration, regeneration)
                    .withTag(FallTrapGenerator.block, tick.block)
                    .withTag(FallTrapGenerator.entityID, tick.block.getTag(FallTrapGenerator.entityID)!!)
                    .withTag(FallTrapGenerator.shouldDestroyAdjacentSameMaterial, tick.block.getTag(FallTrapGenerator.shouldDestroyAdjacentSameMaterial))
            )

            BlockFace.values().forEach {

                val relativeBlockPosition = tick.blockPosition.relative(it)

                val block = tick.instance.getBlock(relativeBlockPosition)

                if (block.handler() == this) {
                    tick.instance.setBlock(relativeBlockPosition, block.withTag(FallTrapGenerator.currentTick, 0))
                }
            }

            tick.instance.getChunkAt(tick.blockPosition)
                ?.sendBlockDamage(100, tick.blockPosition, tick.block.getTag(FallTrapGenerator.entityID)!!)

            tick.instance.getChunkAt(tick.blockPosition)
                ?.sendBreakBlockEffect(tick.blockPosition, tick.block)

        } else {

            tick.instance.setBlock(
                tick.blockPosition,
                tick.block.withTag(FallTrapGenerator.currentTick, currentTick + 1L)
            )

            if (tick.block.isAir) return

            tick.instance.getChunkAt(tick.blockPosition)?.sendBlockDamage(
                ((10 * currentTick) / (ticks)).toDouble().roundToInt().toByte(),
                tick.blockPosition,
                tick.block.getTag(FallTrapGenerator.entityID)!!
            )
        }
    }


}