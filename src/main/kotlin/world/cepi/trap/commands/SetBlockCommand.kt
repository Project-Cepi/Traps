package world.cepi.trap.commands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kstom.command.kommand.Kommand

object SetBlockCommand : Kommand({
    val blockPosition = ArgumentType.RelativeBlockPosition("blockPosition")

    val blockState = ArgumentType.BlockState("block")

    syntax(blockPosition, blockState) {
        val position = (!blockPosition).from(player)

        player.instance!!.setBlock(position, !blockState)
    }
}, "setblock")