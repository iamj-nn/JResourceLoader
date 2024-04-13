package nn.iamj.resources.managers.impl;

import lombok.Getter;
import nn.iamj.resources.JResourceLoader;
import nn.iamj.resources.managers.Manager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@Getter
public final class ConfigManager implements Manager {

    private YamlConfiguration configuration;
    private File file;

    @Override
    public void initialize() {
        final JResourceLoader loader = JResourceLoader.getInstance();

        this.file = new File(loader.getDataFolder(), "config.yml");

        if (!this.file.exists())
            JResourceLoader.getInstance().saveResource("config.yml", false);

        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void reload() {
        this.file = null;
        this.configuration = null;

        this.initialize();
    }

    @Override
    public void shutdown() {
        this.file = null;
        this.configuration = null;
    }

}
