package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.data.Data
import net.minestom.server.entity.Player
import net.minestom.server.instance.block.Block
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag
import world.cepi.kstom.item.clientData
import world.cepi.kstom.item.item
import world.cepi.kstom.item.withMeta

@Serializable
sealed class TrapGenerator {

    abstract val defaultBlock: Material
    abstract val blockId: Short

    fun giveBlock(player: Player) = player.inventory.addItemStack(item(defaultBlock) {
        withMeta {

            this.set(Tag.String("blockClass"), this@TrapGenerator::class.simpleName!!)
            this.set(Tag.Short("material"), defaultBlock.block?.blockId ?: Block.MAGMA_BLOCK.blockId)

            clientData {
                this["block"] = this@TrapGenerator
            }
        }
    })


    open fun generateData(): Data = Data.EMPTY

    companion object {
        val trapGenerators by lazy { TrapGenerator::class.sealedSubclasses }
    }

}