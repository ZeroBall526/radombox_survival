package io.steaming.randombox

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class data {
    val toolboxlist = (Material.entries.filter { material: Material ->  material.name.contains("HOE")}
            + Material.entries.filter { material: Material ->  material.name.contains("PICKAXE")}
            + Material.entries.filter { material: Material ->  material.name.contains("AXE")}
            + Material.entries.filter { material: Material ->  material.name.contains("SHOVEL")}
            + Material.entries.filter { material: Material -> material.name.contains("BUCKET")}
            + Material.entries.filter { material: Material -> material.name.contains("SWORD")}
            + Material.entries.filter { material: Material -> material.name.contains("AXE")}
            + Material.entries.filter { material: Material -> material.name.contains("HELMET")}
            + Material.entries.filter { material: Material -> material.name.contains("CHESTPLATE")}
            + Material.entries.filter { material: Material -> material.name.contains("LEGGINGS")}
            + Material.entries.filter { material: Material -> material.name.contains("BOOTS")}
            + Material.TRIDENT + Material.MACE + Material.SHIELD + Material.FLINT_AND_STEEL + Material.FISHING_ROD
            + Material.SHEARS + Material.BRUSH + Material.ELYTRA + Material.CROSSBOW
            - Material.entries.filter { material: Material -> material.name.contains("WAXED")})

    val planklist  = Material.entries.filter { material: Material ->  material.name.contains("PLANKS")}

    val buttonlist = Material.entries.filter { material: Material ->  material.name.contains("BUTTON")}

    val exclude_tool = (Material.entries.filter { material: Material ->  material.name.contains("WOODEN")}
            + Material.entries.filter { material: Material ->  material.name.contains("LEATHER")}
            + Material.STONE_AXE + Material.STONE_PICKAXE + Material.STONE_SHOVEL + Material.STONE_SWORD)

    val announce_item = ( Material.entries.filter { material: Material ->  material.name.contains("DIAMOND")}
            + Material.entries.filter { material: Material ->  material.name.contains("NETHERITE")}
            -Material.DIAMOND-Material.DEEPSLATE_DIAMOND_ORE-Material.DIAMOND_ORE-Material.DIAMOND_HORSE_ARMOR
            -Material.NETHERITE_INGOT-Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE-Material.NETHERITE_SCRAP)
}

class box {

    //랜덤도구박스 생성
    fun givetoolbox(amount : Int = 1): ItemStack {
        val box  = ItemStack(Material.CHEST)
        box.amount = amount
        box.itemMeta = box.itemMeta.apply{
            itemName(Component.text("GachaToolBox"))
            displayName(Component.text("도구 랜덤 상자", TextColor.color(0x63C5DA), TextDecoration.BOLD))
            val lorelist : List<Component> = listOf(Component.text("뭔가 세상에 있는 모든 도구 및 전투 아이템이 나올 듯한"), Component.text("수상한 랜덤 박스이다..."))
            lore(lorelist)
        }
        return box
    }

    //랜덤박스 생성
    fun givebox(amount : Int = 1): ItemStack {
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
}

