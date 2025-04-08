package ru.osarikas.randomHeight

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class PotionOfHeightCraftManager {
    fun registerCustomCraft(plugin: RandomHeight) {
        val mm = MiniMessage.miniMessage()
        val potionName = plugin.config.getString("potion.name")
        val potionLore = plugin.config.getStringList("potion.lore")

        val lore = potionLore.map { line ->
            mm.deserialize(line)
        }

        val result = ItemStack(Material.POTION)
        val resultMeta = result.itemMeta as PotionMeta


        resultMeta.customName(mm.deserialize("$potionName"))
        resultMeta.lore(lore)
        resultMeta.color = Color.fromRGB(0, 208, 180)
        resultMeta.clearCustomEffects()
        resultMeta.persistentDataContainer.set(
            NamespacedKey(plugin, "random_height_potion"),
            PersistentDataType.INTEGER,
            993
        )
        result.setItemMeta(resultMeta)


        val key = NamespacedKey(plugin, "potionOfHeight")
        val recipe = ShapedRecipe(key, result)

        recipe.shape(*plugin.config.getStringList("potion.craft.shape").toTypedArray<String>())

        for (ingredientKey in Objects.requireNonNull(plugin.config.getConfigurationSection("potion.craft.ingredients"))
            ?.getKeys(false)!!) {
            val materialName = checkNotNull(plugin.config.getString("potion.craft.ingredients.$ingredientKey"))
            val material = Material.matchMaterial(materialName)

            if (material == null) {
                plugin.logger.severe("Invalid ingredient material in config: $materialName")
                return
            }

            recipe.setIngredient(ingredientKey[0], material)
        }

        plugin.server.addRecipe(recipe)
    }
}