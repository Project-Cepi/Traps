package world.cepi.trap.generator

import kotlinx.serialization.Serializable
import net.minestom.server.data.Data
import net.minestom.server.entity.Player
import net.minestom.server.instance.block.Block
import net.minestom.server.tag.Tag
import world.cepi.kstom.item.set
import world.cepi.kstom.item.item
import world.cepi.kstom.item.withMeta

@Serializable
sealed class TrapGenerator {

    abstract val defaultBlock: Block
    abstract val blockId: Int

    fun giveBlock(player: Player) = player.inventory.addItemStack(item(defaultBlock.registry().material()!!) {
        withMeta {

            this.set(Tag.String("blockClass"), this@TrapGenerator::class.simpleName!!)
            this.set(Tag.Integer("material"), defaultBlock.id())

            this["block"] = this@TrapGenerator

        }
    })


    open fun generateData(): Data = Data.EMPTY

    companion object {
        val trapGenerators by lazy { TrapGenerator::class.sealedSubclasses }
    }

}