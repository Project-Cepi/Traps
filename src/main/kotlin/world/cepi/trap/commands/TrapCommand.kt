package world.cepi.trap.commands

import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.item.AddCreationalItem
import world.cepi.kstom.command.arguments.generation.ClassArgumentGenerator
import world.cepi.kstom.command.arguments.generation.argumentsFromClass
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.trap.generator.TrapGenerator
import world.cepi.trap.generator.trapGenerator

object TrapCommand : Kommand({
    val create by literal

    val set by literal
    val coordinates = ArgumentType.RelativeBlockPosition("trapCoordinates")

    TrapGenerator.trapGenerators.forEach {

        val generatedSyntaxes = ClassArgumentGenerator(it)

        val trapName = it.simpleName!!.dropLast("TrapGenerator".length).lowercase()

        generatedSyntaxes.applySyntax(this, create, trapName.literal()) { instance ->

            val player = sender as? Player ?: return@applySyntax

            AddCreationalItem.put(player, instance.generateItem())
        }
    }

    syntax(set, coordinates).onlyPlayers {
        val trap = player.itemInMainHand.trapGenerator ?: return@onlyPlayers

        val relativeCoordinates = (!coordinates).from(player)

        player.instance!!.setBlock(relativeCoordinates, trap.generateBlock(player.instance!!.getBlock(relativeCoordinates)).withHandler(trap.handler))
    }

    applyHelp {
        """
            Traps are a way to make blocks emit special properties whenever an action happens to it.
            
            Usage:
            <yellow>/trap handle (trapName) (trapCoordinates) (...trapProperties)
            
            <blue>TIP:
            trapCoordinates automatically autocompletes to the block you are looking at.
        """.trimIndent()
    }

    addSubcommands(ApplyTrapSubcommand)

}, "trap", "traps")