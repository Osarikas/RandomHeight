package ru.osarikas.randomHeight

import org.bukkit.plugin.java.JavaPlugin
import ru.osarikas.randomHeight.listeners.FirstPlayerJoin
import ru.osarikas.randomHeight.listeners.PlayerPotionDrink

class RandomHeight : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        server.pluginManager.registerEvents(FirstPlayerJoin(this), this)
        server.pluginManager.registerEvents(PlayerPotionDrink(this), this)
        PotionOfHeightCraftManager().registerCustomCraft(this)
    }
}
