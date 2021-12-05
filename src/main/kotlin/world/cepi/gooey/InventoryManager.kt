package world.cepi.gooey

import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

object InventoryManager {

    private val internalMap = mutableMapOf<Int, ItemStack>()
    private val playerMap = mutableMapOf<Player, MutableMap<Int, ItemStack>>()

    fun slot(slot: Int): ItemStack? = internalMap[slot]
    fun hasItem(item: ItemStack) = internalMap.containsValue(item)

    operator fun set(slot: Int, itemStack: ItemStack) {
        internalMap[slot] = itemStack
    }

    fun remove(slot: Int) = internalMap.remove(slot)

    fun put(player: Player, slot: Int, itemStack: ItemStack) {
        playerMap.computeIfAbsent(player) { mutableMapOf() }[slot] = itemStack
    }

    fun remove(player: Player, slot: Int) {
        playerMap.computeIfAbsent(player) { mutableMapOf() }.remove(slot)
    }

    fun refresh(player: Player) {
        internalMap.forEach { (index, item) ->
            player.inventory.setItemStack(index, item)
        }
    }
}