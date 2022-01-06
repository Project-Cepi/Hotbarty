package world.cepi.gooey

import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.item.ItemDropEvent
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension;
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node

class GooeyExtension : Extension() {

    override fun initialize(): LoadStatus {

        node.listenOnly<PlayerSpawnEvent> {
            InventoryManager.refresh(player)
        }

        node.listenOnly<InventoryPreClickEvent> {
            if (InventoryManager.slot(slot) != null) {
                isCancelled = true
                return@listenOnly
            }
        }

        node.listenOnly<ItemDropEvent> {
            if (InventoryManager.hasItem(itemStack)) {
                isCancelled = true
                return@listenOnly
            }
        }

        log.info("[Gooey] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        log.info("[Gooey] has been disabled!")
    }

}
