package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set
import world.cepi.trap.TrapExtension
import world.cepi.trap.generator.TrapGenerator.Companion.module

@Serializable
sealed class TrapGenerator {
    open fun generateBlock(block: Block): Block = block

    abstract val handler: BlockHandler

    companion object {
        val trapGenerators = arrayOf(
            DamageTrapGenerator::class,
            FallTrapGenerator::class,
            VelocityTrapGenerator::class,
            PotionTrapGenerator::class
        )

        val module = SerializersModule {
            polymorphic(TrapGenerator::class) {
                subclass(DamageTrapGenerator::class)
                subclass(FallTrapGenerator::class)
                subclass(VelocityTrapGenerator::class)
                subclass(PotionTrapGenerator::class)
            }
        }
    }

    fun generateItem() = item(Material.PURPLE_DYE) {
        displayName(
            Component.text(
                this@TrapGenerator::class.simpleName!!.dropLast("TrapGenerator".length) + " Trap Generator",
                TrapExtension.trapColor
            ).decoration(TextDecoration.ITALIC, false)
        )

        this["trap", module] = this@TrapGenerator
    }

}

val ItemStack.trapGenerator: TrapGenerator? get() = this.get("trap", module)