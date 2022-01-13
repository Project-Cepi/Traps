package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.MinecraftServer
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.tag.Tag
import net.minestom.server.utils.time.TimeUnit
import world.cepi.kstom.command.arguments.generation.annotations.*
import world.cepi.kstom.nbt.TagBlock
import world.cepi.kstom.serializer.DurationSerializer
import world.cepi.trap.blocks.FallTrap
import java.time.Duration
import kotlin.random.Random

@Serializable
data class FallTrapGenerator(
    @param:DefaultTickDuration(20)
    @Serializable(with = DurationSerializer::class)
    val length: Duration = Duration.of(20, TimeUnit.SERVER_TICK)
) : TrapGenerator() {

    @Transient
    override val handler = FallTrap

    override fun generateBlock(block: Block) =
        block
            .withTag(ticks, length.toMillis() / MinecraftServer.TICK_MS)
            .withTag(FallTrapGenerator.block, block)
            .withTag(entityID, Random.nextInt(0, Int.MAX_VALUE))


    companion object {
        val ticks = Tag.Long("ticks")
        val currentTick = Tag.Long("currentTick")
        val block = TagBlock("block")
        val entityID = Tag.Integer("entityID")
    }
}