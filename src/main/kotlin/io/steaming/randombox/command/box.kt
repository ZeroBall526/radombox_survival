package io.steaming.randombox.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class box : CommandExecutor {
    //command function page
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender is Player){
            if (sender.isOp){
                val p : Player = sender
                p.sendMessage("아이템을 지급했어요")

                val item = givebox()
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

    fun givebox(): ItemStack {
        var box  = ItemStack(Material.CHEST)
        box.itemMeta = box.itemMeta.apply{
            itemName(Component.text("GachaBox"))
            displayName(Component.text("랜덤 상자", TextColor.color(0xFF00FF), TextDecoration.BOLD))
            var lorelist : List<Component>? = listOf(Component.text("뭔가 세상에 있는 모든 아이템이 나올 듯한"), Component.text("수상한 랜덤 박스이다..."))
            lore(lorelist)
        }

        return box
    }
}