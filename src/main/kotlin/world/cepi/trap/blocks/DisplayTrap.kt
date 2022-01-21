package world.cepi.trap.blocks

import net.minestom.server.entity.ItemEntity
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.tag.Tag
import net.minestom.server.utils.NamespaceID
import world.cepi.itemextension.item.Item
import world.cepi.itemextension.item.itemSerializationModule
import world.cepi.kstom.item.get

object DisplayTrap : BlockHandler {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:shop_trap")
    }

    override fun onPlace(placement: BlockHandler.Placement) {

        val itemEntity = ItemEntity(placement.block.get<Item>("item", module = itemSerializationModule)!!.renderItem(1))

        itemEntity.isPickable = false
        itemEntity.setNoGravity(true)
        itemEntity.isCustomNameVisible = true

        itemEntity.setInstance(placement.instance, placement.blockPosition.add(0.5, 0.25, 0.5))

        placement.instance.setBlock(
            placement.blockPosition,
            placement.instance.getBlock(placement.blockPosition)
                .withTag(Tag.Integer("entityID"), itemEntity.entityId)
        )
    }

    override fun onDestroy(destroy: BlockHandler.Destroy) {
        destroy.instance.entities.firstOrNull { it.entityId == destroy.block.getTag(Tag.Integer("entityID")) }?.remove()
    }

}