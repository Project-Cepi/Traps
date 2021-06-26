package world.cepi.trap.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.argumentsFromClass
import world.cepi.kstom.command.arguments.literal
import world.cepi.trap.generator.DamageTrapGenerator
import world.cepi.trap.generator.TrapGenerator
import kotlin.reflect.full.allSuperclasses

object TrapCommand : Command("trap") {

    init {

        val get = "get".literal()

        TrapGenerator.trapGenerators.forEach {

            val generatedArgs = argumentsFromClass(it)

            val trapName = it.simpleName!!.dropLast("TrapGenerator".length)

            addSyntax(get, trapName.literal(), *generatedArgs.args) { sender, args ->
                val instance = generatedArgs.createInstance(args, sender)

                instance.giveBlock(sender as Player)
            }
        }

    }

}