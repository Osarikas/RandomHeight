package ru.osarikas.randomHeight.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import kotlin.random.Random

fun setRandomHeight(minHeight : Double, maxHeight : Double, p: Player) {
    Bukkit.dispatchCommand(
        Bukkit.getConsoleSender(),
        "attribute ${p.name} minecraft:scale base set ${Random.nextDouble(minHeight, maxHeight)}"
    )
}