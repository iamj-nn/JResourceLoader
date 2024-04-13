package nn.iamj.resources;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import nn.iamj.resources.commands.impl.ResourceCommand;
import nn.iamj.resources.managers.impl.ConfigManager;
import nn.iamj.resources.managers.impl.ResourceManager;
import nn.iamj.resources.listeners.ResourceListener;

@Getter
public final class JResourceLoader extends JavaPlugin {

    @Getter
    private static JResourceLoader instance;

    private ConfigManager configManager;
    private ResourceManager resourceManager;

    @Override
    public void onLoad() {
        instance = this;

        this.configManager = new ConfigManager();
        this.resourceManager = new ResourceManager();

        this.configManager.initialize();
    }

    @Override
    public void onEnable() {
        this.resourceManager.initialize();

        this.getServer().getPluginManager().registerEvents(new ResourceListener(), instance);

        (new ResourceCommand()).register("resource");
    }

    @Override
    public void onDisable() {
        this.resourceManager.shutdown();
        this.configManager.shutdown();

        instance = null;
    }

}
