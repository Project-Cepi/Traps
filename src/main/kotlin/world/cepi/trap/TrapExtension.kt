package world.cepi.trap

import net.kyori.adventure.text.format.TextColor
import net.minestom.server.extensions.Extension
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.event.listenOnly
import world.cepi.trap.commands.TrapCommand
import world.cepi.trap.event.UseTrapHandler
import world.cepi.trap.generator.TrapGenerator

class TrapExtension : Extension() {

    companion object {
        val trapColor = TextColor.color(150, 67, 198)
    }

    override fun initialize() {

        TrapCommand.register()

        eventNode.listenOnly(UseTrapHandler::used)

        val trapAmount = TrapGenerator.trapGenerators.size

        logger.info("[TrapExtension] has been enabled - $trapAmount trap${if (trapAmount == 1) "" else "s"} loaded!")
    }

    override fun terminate() {

        TrapCommand.unregister()

        logger.info("[TrapExtension] has been disabled!")
    }

}