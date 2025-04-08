package ru.osarikas.randomHeight.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.osarikas.randomHeight.RandomHeight
import ru.osarikas.randomHeight.util.setRandomHeight

class FirstPlayerJoin(
    private val plugin: RandomHeight
) : Listener {
    @EventHandler
    fun onFirstPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        if(!player.hasPlayedBefore()){
            val maxHeight = plugin.config.getDouble("first-join.max-height")
            val minHeight = plugin.config.getDouble("first-join.min-height")
            setRandomHeight(minHeight, maxHeight, player)
        }
    }
}