package world.cepi.hotbarty

import net.minestom.server.event.inventory.InventoryClickEvent
import net.minestom.server.event.item.ItemDropEvent
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.extensions.Extension;
import world.cepi.kstom.event.listenOnly

class HotbartyExtension : Extension() {

    override fun initialize() {
        logger.info("[Hotbarty] has been enabled!")
    }

    override fun postInitialize() {
        eventNode.listenOnly<PlayerSpawnEvent> {
            HotbartyManager.refresh(player)
        }

        eventNode.listenOnly<InventoryClickEvent> {
            if (HotbartyManager.containsKey(slot)) return@listenOnly
        }

        eventNode.listenOnly<ItemDropEvent> {
            if (HotbartyManager.containsValue(itemStack)) return@listenOnly
        }
    }

    override fun terminate() {
        logger.info("[Hotbarty] has been disabled!")
    }

}
