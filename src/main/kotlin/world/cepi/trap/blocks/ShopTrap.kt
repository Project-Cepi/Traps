package world.cepi.trap.blocks

import net.minestom.server.entity.ItemEntity
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.tag.Tag
import net.minestom.server.utils.NamespaceID
import world.cepi.itemextension.item.Item
import world.cepi.kstom.item.get

object ShopTrap : BlockHandler {

    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from("cepi:shop_trap")
    }

    override fun onPlace(placement: BlockHandler.Placement) {

        val itemEntity = ItemEntity(placement.block.get("item", serializer = Item.serializer())!!.renderItem(1))

        itemEntity.isPickable = false
        itemEntity.setNoGravity(true)

        itemEntity.setInstance(placement.instance, placement.blockPosition)

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