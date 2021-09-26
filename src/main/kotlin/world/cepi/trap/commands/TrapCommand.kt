package world.cepi.trap.commands

import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kstom.command.arguments.delegate
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.trap.generator.TrapGenerator

object TrapCommand : Kommand({
    val handle by literal
    val lookCoordinate by ArgumentType::RelativeBlockPosition.delegate()

    TrapGenerator.trapGenerators.forEach {

        val generatedSyntaxes = generateSyntaxes(it)

        val trapName = it.simpleName!!.dropLast("TrapGenerator".length)

        generatedSyntaxes.applySyntax(this, handle, trapName.literal(), lookCoordinate) { instance ->

            val player = sender as? Player ?: return@applySyntax
            val location = context[lookCoordinate].from(player)

            player.instance!!.setBlock(
                location,
                instance.generateBlock(player.instance!!.getBlock(location)).withHandler(instance.handler)
            )

            player.sendMessage("Trap set!")
        }
    }

    applyHelp {
        """
            Traps are a way to make blocks emit special properties whenever an action happens to it.
            
            Usage:
            /trap handle (trapName) (trapCoordinates) (...trapProperties)
            
            TIP:
            trapCoordinates automatically autocompletes to the block you are looking at.
        """.trimIndent()
    }

}, "trap", "traps")