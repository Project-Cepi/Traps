package world.cepi.trap.generator

import net.minestom.server.entity.Player

sealed interface TrapGenerator {

    fun giveBlock(player: Player)

}