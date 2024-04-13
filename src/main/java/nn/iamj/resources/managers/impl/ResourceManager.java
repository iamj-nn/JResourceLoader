package nn.iamj.resources.managers.impl;

import lombok.Getter;
import nn.iamj.resources.JResourceLoader;
import nn.iamj.resources.managers.Manager;
import org.bukkit.configuration.file.YamlConfiguration;
import nn.iamj.resources.resources.Resource;

@Getter
public final class ResourceManager implements Manager {

    private Resource resource;

    @Override
    public void initialize() {
        final YamlConfiguration configuration = JResourceLoader.getInstance().getConfigManager().getConfiguration();

        if (configuration == null) return;

        final String url = configuration.getString("resource.url", "");
        final String prompt = configuration.getString("resource.prompt", "");
        final boolean force = configuration.getBoolean("resource.force", true);

        this.resource = new Resource(url, prompt, force);
    }

    @Override
    public void reload() {
        this.resource = null;

        this.initialize();
    }

    @Override
    public void shutdown() {
        this.resource = null;
    }

}
