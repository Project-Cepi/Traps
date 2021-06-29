package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.instance.block.Block
import net.minestom.server.item.Material
import net.minestom.server.potion.Potion
import net.minestom.server.potion.PotionEffect
import world.cepi.kstom.command.arguments.annotations.*
import world.cepi.kstom.data.data

@Serializable
data class PotionTrapGenerator(
    @param:DefaultPotionEffect(PotionEffect.CONFUSION)
    val potionEffect: PotionEffect,
    @param:DefaultNumber(3.0)
    val amplifier: Byte = 3,
    @param:DefaultNumber(60.0)
    val duration: Int = 60,
    @param:DefaultBoolean(true)
    val particles: Boolean = true,
    @param:DefaultBoolean(true)
    val icon: Boolean = true,
    @param:DefaultBoolean(false)
    val ambient: Boolean = false,
    @param:DefaultBlock(Block.EMERALD_BLOCK)
    override val defaultBlock: Block = Block.EMERALD_BLOCK
) : TrapGenerator() {
    override val blockId = 103.toShort()

    override fun generateData() = data {
        this["potion"] = Potion(potionEffect, amplifier, duration, particles, icon, ambient)
    }

    companion object {
        const val potionKey = "potion"
    }
}