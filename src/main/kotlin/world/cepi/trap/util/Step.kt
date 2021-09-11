package world.cepi.trap.util

import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block

class Step(
    val block: Block,
    val instance: Instance,
    val blockPosition: Point,
    val entitiy: Entity
)