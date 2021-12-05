package world.cepi.hotbarty

import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

object HotbartyManager : MutableMap<Int, ItemStack> by mutableMapOf() {
    fun refresh(player: Player) {
        HotbartyManager.forEach { index, item ->
            player.inventory.setItemStack(index, item)
        }
    }
}