package world.cepi.gooey

import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.item.ItemDropEvent
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension;
import world.cepi.kstom.event.listenOnly

class GooeyExtension : Extension() {

    override fun initialize() {
        logger.info("[Gooey] has been enabled!")
    }

    override fun postInitialize() {
        eventNode.listenOnly<PlayerSpawnEvent> {
            InventoryManager.refresh(player)
        }

        eventNode.listenOnly<InventoryPreClickEvent> {
            if (InventoryManager.slot(slot) != null) {
                isCancelled = true
                return@listenOnly
            }
        }

        eventNode.listenOnly<ItemDropEvent> {
            if (InventoryManager.hasItem(itemStack)) {
                isCancelled = true
                return@listenOnly
            }
        }
    }

    override fun terminate() {
        logger.info("[Gooey] has been disabled!")
    }

}
