package world.cepi.trap.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.trap.generator.TrapGenerator
import kotlin.reflect.full.allSuperclasses

object TrapCommand : Command("trap") {

    init {

        val handle = "handle".literal()
        val lookCoordinate = ArgumentType.RelativeBlockPosition("block")

        TrapGenerator.trapGenerators.forEach {

            val generatedSyntaxes = generateSyntaxes(it)

            val trapName = it.simpleName!!.dropLast("TrapGenerator".length)

            generatedSyntaxes.applySyntax(this, handle, trapName.literal(), lookCoordinate) { instance ->

                val player = sender as? Player ?: return@applySyntax

                player.instance!!.setBlock(
                    context[lookCoordinate].from(player),
                    instance.generateBlock(player.instance!!.getBlock(context[lookCoordinate].from(player)))
                )

                player.sendMessage("Trap set!")
            }
        }

    }

}