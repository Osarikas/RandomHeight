package ru.osarikas.randomHeight.listeners

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.persistence.PersistentDataType
import ru.osarikas.randomHeight.RandomHeight
import ru.osarikas.randomHeight.util.setRandomHeight

class PlayerPotionDrink(private val plugin: RandomHeight) : Listener {

    @EventHandler
    fun onPotionDrink(event: PlayerItemConsumeEvent) {
        val item = event.item
        val meta = item.itemMeta ?: return

        if (meta.persistentDataContainer.has(
                NamespacedKey(plugin, "random_height_potion"),
                PersistentDataType.INTEGER
            )) {
            val maxHeight = plugin.config.getDouble("potion.max-height")
            val minHeight = plugin.config.getDouble("potion.min-height")
            setRandomHeight(minHeight, maxHeight, event.player)
        }
    }
}