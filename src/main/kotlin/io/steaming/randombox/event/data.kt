package io.steaming.randombox.event

import org.bukkit.Material

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

