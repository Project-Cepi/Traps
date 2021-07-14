package world.cepi.trap.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.trap.generator.TrapGenerator
import kotlin.reflect.full.allSuperclasses

object TrapCommand : Command("trap") {

    init {

        val get = "get".literal()

        TrapGenerator.trapGenerators.forEach {

            val generatedSyntaxes = generateSyntaxes(it)

            val trapName = it.simpleName!!.dropLast("TrapGenerator".length)

            generatedSyntaxes.applySyntax(this, get, trapName.literal()) { instance ->
                instance.giveBlock(sender as Player)
            }
        }

    }

}