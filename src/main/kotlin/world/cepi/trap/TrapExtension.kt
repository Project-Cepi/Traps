package world.cepi.trap

import net.minestom.server.extensions.Extension;

class TrapExtension : Extension() {

    override fun initialize() {
        logger.info("[TrapExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[TrapExtension] has been disabled!")
    }

}