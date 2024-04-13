package nn.iamj.resources.listeners;

import nn.iamj.resources.JResourceLoader;
import nn.iamj.resources.resources.Resource;
import nn.iamj.resources.utils.Coloriser;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public final class ResourceListener implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(final PlayerJoinEvent event) {
        final YamlConfiguration configuration = JResourceLoader.getInstance().getConfigManager().getConfiguration();

        if (configuration == null) return;

        final Player player = event.getPlayer();
        final Resource resource = JResourceLoader.getInstance().getResourceManager().getResource();

        if (resource == null || resource.getUrl().isEmpty()) return;

        player.setResourcePack(resource.getUrl());
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onResource(final PlayerResourcePackStatusEvent event) {
        final YamlConfiguration configuration = JResourceLoader.getInstance().getConfigManager().getConfiguration();
        final Player player = event.getPlayer();

        if (configuration == null) return;

        final PlayerResourcePackStatusEvent.Status status = event.getStatus();
        final Resource resource = JResourceLoader.getInstance().getResourceManager().getResource();

        if (resource == null || !resource.isForce()) return;

        switch (status) {
            case DECLINED, FAILED_DOWNLOAD ->
                player.kickPlayer(Coloriser.colorize(
                        configuration.getString("messages.resource-not-load", "&x&f&f&1&1&1&1You must download the resource pack to continue playing.")));
        }
    }

}
