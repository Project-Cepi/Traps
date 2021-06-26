package world.cepi.trap

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.event.listenOnly
import world.cepi.trap.commands.TrapCommand
import world.cepi.trap.generator.TrapGenerator
import world.cepi.trap.listener.TrapPlaceHandler

class TrapExtension : Extension() {

    override fun initialize() {

        TrapCommand.register()

        eventNode.listenOnly(TrapPlaceHandler::onPlace)

        val trapAmount = TrapGenerator.trapGenerators.size

        logger.info("[TrapExtension] has been enabled - $trapAmount trap${if (trapAmount == 1) "" else "s"} loaded!")
    }

    override fun terminate() {

        TrapCommand.unregister()

        logger.info("[TrapExtension] has been disabled!")
    }

}