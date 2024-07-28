package io.steaming.randombox.command

import io.steaming.randombox.event.data
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class toolbox : CommandExecutor {
    private val data = data()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender is Player){
            if (sender.isOp){
                val p : Player = sender
                p.sendMessage("아이템을 지급했어요")
                p.sendMessage("툴박스 리스트 ${data.toolboxlist}")

                val item = givetoolbox()
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

    //랜덤도구박스 생성
    private fun givetoolbox(amount : Int = 1): ItemStack {
        val box  = ItemStack(Material.CHEST)
        box.amount = amount
        box.itemMeta = box.itemMeta.apply{
            itemName(Component.text("GachaToolBox"))
            displayName(Component.text("도구 랜덤 상자", TextColor.color(0x63C5DA), TextDecoration.BOLD))
            val lorelist : List<Component> = listOf(Component.text("뭔가 세상에 있는 모든 도구 및 전투 아이템이 나올 듯한"),Component.text("수상한 랜덤 박스이다..."))
            lore(lorelist)
        }
        return box
    }
}