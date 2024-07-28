package io.steaming.randombox.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

import kotlin.random.Random
import kotlin.random.nextInt

class Event : Listener {

    private val data = data()

    //모든 제작 작업이 된 아이템 랜덤 박스로 바꿔치기
    @EventHandler
    fun PlayerCrafting(e : PrepareItemCraftEvent){
        val inv = e.inventory
        val result = inv.result ?: return
        
        //예외 : 기본이 되는 판자, 막대기, 작업대, 버튼(너무 날 먹임), 가죽값옷, 나무도구, 돌도구는 제외
        if(result.type == Material.CRAFTING_TABLE){
            val item = ItemStack(Material.CRAFTING_TABLE)
            item.itemMeta = item.itemMeta.apply {
                val lorelist : List<Component> = listOf(Component.text("이걸로 무언가 의미 있는걸 만들긴 어려워 보인다..."))
                lore(lorelist)
            }
            inv.result = item
        }
        else if(result.type == Material.STICK){
        }
        else if(data.buttonlist.contains(result.type)){
            val item = ItemStack(result.type)
            item.itemMeta = item.itemMeta.apply{
                val lorelist : List<Component> = listOf(Component.text("창조자가 날먹 싫어 해서 그런지 그냥 가지고 놀라고"),Component.text("만들어지는 듯 하다..."))
                lore(lorelist)
            }
            inv.result = item
        }
        else if(data.planklist.contains(result.type)){

        }
        else if(data.exclude_tool.contains(result.type)){
            val index = result.amount
            inv.result = givebox(index)
        }
        else if(data.toolboxlist.toTypedArray().contains(result.type)){
            inv.result = givetoolbox(1)
        }
        else if(Material.entries.toTypedArray().contains(result.type)){
            val index = result.amount
            inv.result = givebox(index)
        }
    }

    //박스 열었을 때 동작할 함수
    @EventHandler
    fun OpenBox(e : PlayerInteractEvent){
        val p : Player = e.player
        val item = p.inventory.itemInMainHand

        if((e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK)){
            // active gacha
            if (item.itemMeta.itemName() == Component.text("GachaBox")){
                //일반가챠
                val itemamount = item.amount
                if (itemamount > 1){
                    p.inventory.setItemInMainHand(givebox(itemamount-1))
                }else{
                    p.inventory.removeItem(givebox())
                }

                gacha(p)
                e.isCancelled = true
            }else if (item.itemMeta.itemName() == Component.text("GachaToolBox")){
                //도구가챠
                val itemamount = item.amount
                if (itemamount>1){
                    p.inventory.setItemInMainHand(givetoolbox(itemamount-1))
                }else{
                    p.inventory.removeItem(givetoolbox())
                }

                toolgacha(p)
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    //주민 거래 통제
    fun Blocking_trade(e : InventoryOpenEvent){
        val p = e.player

        if (e.inventory.type == InventoryType.MERCHANT){
            p.sendMessage("주민: ${p.name}은(는) 계속 거기 살아 주민은 갈거야")
            e.isCancelled = true
        }
    }
    
    //랜덤박스 생성
    private fun givebox(amount : Int = 1): ItemStack {
        val box  = ItemStack(Material.CHEST)
        box.amount = amount
        box.itemMeta = box.itemMeta.apply{
            itemName(Component.text("GachaBox"))
            displayName(Component.text("랜덤 상자", TextColor.color(0xFF00FF), TextDecoration.BOLD))
            val lorelist : List<Component> = listOf(Component.text("뭔가 세상에 있는 모든 아이템이 나올 듯한"),Component.text("수상한 랜덤 박스이다..."))
            lore(lorelist)
        }
        return box
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

    //랜덤 아이템 지급
    private fun gacha(player : Player){
        try{
            var item: Material?
            while(true){
                val RandomIndex = Random.nextInt(Material.entries.size)
                item = Material.entries[RandomIndex]
                if (!item.isLegacy && item.isItem){
                    break
                }
            }
            //디버그코드
            //player.sendMessage(item.toString())

            if(player.inventory.firstEmpty() == -1){
                player.world.dropItemNaturally(player.location, ItemStack(item!!))
            }else{
                player.inventory.addItem(ItemStack(item!!))
            }
            announce(player, ItemStack(item))
        }catch (e : Exception){
            player.sendMessage("상자를 여는중 문제가 생겼습니다")
            println(e)
            player.sendMessage(e.toString())
        }

    }

    //랜덤 도구 아이템 지급
    private fun toolgacha(player : Player){
        try{
            var type: Material?
            while(true){
                val RandomIndex = Random.nextInt(data.toolboxlist.size)
                type = data.toolboxlist[RandomIndex]
                if (!type.isLegacy && type.isItem){
                    break
                }
            }
            //디버그코드
            //player.sendMessage(item.toString())

            val item = ItemStack(type!!)
            if (item.itemMeta is Damageable){
                val randomindex : Int = Random.nextInt(1 .. item.type.maxDurability.toInt()+1)
                val itemmeta = item.itemMeta

                val damageable = itemmeta as Damageable
                damageable.damage = randomindex
                item.itemMeta = itemmeta
            }

            if(player.inventory.firstEmpty() == -1){
                player.world.dropItemNaturally(player.location, item)
            }else{
                player.inventory.addItem(item)
            }
            //결과물 출력코드
            announce(player, item)
        }catch (e : Exception){
            player.sendMessage("상자를 여는중 문제가 생겼습니다")
            println(e)
            player.sendMessage(e.toString())
        }

    }

    //획득알림 && 비틱
    private fun announce(player: Player, item : ItemStack){
        //player.sendMessage("랜덤 상자에서 ${ItemStack(item).displayName()}를(을) 뽑으셨습니다!")
        player.sendMessage(Component.text("랜덤 상자에서 ")
            .append(Component.text(item.type.name).color(TextColor.color(0xC89BF1)))
            .append(Component.text("를(을) 뽑으셨습니다!")))
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 2f)

        if(data.announce_item.toTypedArray().contains(item.type)){
            val onplayer = Bukkit.getServer().onlinePlayers

            onplayer.forEach(){
                it.sendMessage(Component.text("${player.name}님이 랜덤 상자에서 ")
                    .append(Component.text(item.type.name, TextColor.color(0x8482e6))).append(Component.text("를(을) 마침내 드디어 뽑았습니다!")))
                it.playSound(it.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.7f,2f)
            }
        }
    }
}