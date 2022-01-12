package world.cepi.trap.blocks

import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.network.packet.server.play.BlockBreakAnimationPacket
import net.minestom.server.utils.NamespaceID
import world.cepi.trap.generator.FallTrapGenerator
import world.cepi.trap.util.Step
import world.cepi.trap.util.SteppedTrap
import kotlin.math.roundToInt
import kotlin.random.Random

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

        if (ticks == currentTick) {
            tick.instance.setBlock(
                tick.blockPosition,
                Block.AIR.withHandler(this)
                    .withTag(FallTrapGenerator.currentTick, 0)
            )
        } else {
            tick.instance.setBlock(
                tick.blockPosition,
                tick.block.withTag(FallTrapGenerator.currentTick, currentTick + 1L)
            )

            tick.instance.getChunkAt(tick.blockPosition)?.sendPacketToViewers(
                BlockBreakAnimationPacket(Random.nextInt(0, 100000), tick.blockPosition,
                    ((10 * currentTick) / (ticks)).toDouble().roundToInt().toByte()
                )
            )
        }
    }


}