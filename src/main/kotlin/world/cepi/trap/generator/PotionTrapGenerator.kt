package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.item.Material
import net.minestom.server.potion.Potion
import net.minestom.server.potion.PotionEffect
import world.cepi.kstom.command.arguments.generation.annotations.*
import world.cepi.kstom.item.with
import world.cepi.kstom.serializer.PotionSerializer
import world.cepi.trap.blocks.PotionTrap

@Serializable
data class PotionTrapGenerator(
    @param:DefaultPotionEffect("nausea")
    val potionEffect: PotionEffect,
    @param:DefaultNumber(3.0)
    val amplifier: Byte = 3,
    @param:DefaultNumber(3.0)
    val duration: Double = 3.0,
    @param:DefaultBoolean(true)
    val particles: Boolean = true,
    @param:DefaultBoolean(true)
    val icon: Boolean = true,
    @param:DefaultBoolean(false)
    val ambient: Boolean = false
) : TrapGenerator() {
    override fun generateBlock(block: Block) =
        block.with("potion",
            serializer = PotionSerializer,
            item = Potion(potionEffect, amplifier, (duration * 20).toInt(), particles, icon, ambient)
        )

    @Transient
    override val handler = PotionTrap

    companion object {
        const val potionKey = "potion"
    }
}