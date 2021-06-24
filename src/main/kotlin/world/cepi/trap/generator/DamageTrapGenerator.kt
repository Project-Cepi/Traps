package world.cepi.trap.generator

import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class DamageTrapGenerator : TrapGenerator {
    override fun giveBlock(player: Player) {
        player.inventory.addItemStack(ItemStack.of(Material.MAGMA_BLOCK))
    }
}