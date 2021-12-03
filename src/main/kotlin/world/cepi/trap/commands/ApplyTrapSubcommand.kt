package world.cepi.trap.commands

import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.kstom.util.*
import world.cepi.trap.generator.trapGenerator

object ApplyTrapSubcommand : Kommand({
    val block = ArgumentType.BlockState("block")
    val radius = ArgumentType.Integer("radius").min(1).max(100)

    syntax(block, radius) {

        val trap = player.itemInMainHand.trapGenerator ?: return@syntax

        val (pX, pY, pZ) = player.position.roundToBlock()
        val x = pX.toInt()
        val y = pY.toInt()
        val z = pZ.toInt()

        val divRadius = (!radius) / 2

        ((x - divRadius)..(x + divRadius)).forEach { x ->
            ((y - divRadius)..(y + divRadius)).forEach { y ->
                ((z - divRadius)..(z + divRadius)).forEach { z ->

                    val currentBlock = player.instance!!.getBlock(x, y, z)

                    if ((!block).id() == currentBlock.id()) player.instance!!.setBlock(
                        Vec(x.toDouble(), y.toDouble(), z.toDouble()),
                        trap.generateBlock(currentBlock).withHandler(trap.handler)
                    )

                }
            }
        }
    }
}, "apply")