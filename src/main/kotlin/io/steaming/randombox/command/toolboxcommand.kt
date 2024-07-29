package io.steaming.randombox.command

import io.steaming.randombox.box
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class toolboxcommand : CommandExecutor {
    private val genbox = box()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender is Player){
            if (sender.isOp){
                val p : Player = sender
                val item = genbox.givetoolbox()

                item.amount = 64

                p.inventory.addItem(item)
                return true
            }else{
                sender.sendMessage(Component.text("명령어를 사용할 권한이 없어요!").color(TextColor.color(0xFF0000)))
                return false
            }
        }else{
            sender.sendMessage("플레이어가 아닌 대상에겐 지급할수 없어요!")
            return false
        }

    }
}