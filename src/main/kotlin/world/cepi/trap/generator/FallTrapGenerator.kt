package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
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
    val length: Duration = Duration.of(20, TimeUnit.SERVER_TICK),
    @param:DefaultTickDuration(20)
    @Serializable(with = DurationSerializer::class)
    val regeneration: Duration = Duration.of(20, TimeUnit.SERVER_TICK),
) : TrapGenerator() {

    @Transient
    override val handler = FallTrap

    override fun generateBlock(block: Block) =
        block
            .withTag(ticks, length.toMillis() / MinecraftServer.TICK_MS)
            .withTag(Companion.regeneration, regeneration.toMillis() / MinecraftServer.TICK_MS)
            .withTag(FallTrapGenerator.block, block)
            .withTag(entityID, Random.nextInt(0, Int.MAX_VALUE))


    override fun generateLore() = listOf(
        Component.empty(),
        Component.text("Break Time: ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
            .append(Component.text((length.toMillis() / MinecraftServer.TICK_MS).toString() + "t ", NamedTextColor.WHITE))
            .append(Component.text("(" + ((length.toMillis() / MinecraftServer.TICK_MS) / MinecraftServer.TICK_PER_SECOND).toString() + "s)", NamedTextColor.GRAY))
    )

    companion object {
        val ticks = Tag.Long("ticks")
        val regeneration = Tag.Long("regeneration")
        val currentTick = Tag.Long("currentTick")
        val block = TagBlock("block")
        val entityID = Tag.Integer("entityID")
    }
}