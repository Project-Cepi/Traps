package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.data.Data
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import world.cepi.kstom.item.clientData
import world.cepi.kstom.item.item
import world.cepi.kstom.item.withMeta

@Serializable
sealed class TrapGenerator {

    abstract val defaultBlock: Material
    abstract val blockId: Short

    fun giveBlock(player: Player) = player.inventory.addItemStack(item(defaultBlock) {
        withMeta {
            clientData {
                this["blockClass"] = this@TrapGenerator::class
                this["block"] = this@TrapGenerator
            }
        }
    })


    open fun generateData(): Data = Data.EMPTY

    companion object {
        val trapGenerators = TrapGenerator::class.sealedSubclasses
    }

}