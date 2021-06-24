package world.cepi.trap

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.trap.commands.TrapCommand

class TrapExtension : Extension() {

    override fun initialize() {

        TrapCommand.register()

        logger.info("[TrapExtension] has been enabled!")
    }

    override fun terminate() {

        TrapCommand.unregister()

        logger.info("[TrapExtension] has been disabled!")
    }

}