package nn.iamj.resources.commands.impl;

import nn.iamj.resources.JResourceLoader;
import nn.iamj.resources.commands.AbstractCommand;
import nn.iamj.resources.managers.impl.ResourceManager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import nn.iamj.resources.managers.impl.ConfigManager;
import nn.iamj.resources.utils.Coloriser;

import java.util.Collections;
import java.util.List;

public final class ResourceCommand extends AbstractCommand {

    @Override
    public void execute(final CommandSender sender, final List<String> args) {
        final YamlConfiguration configuration = JResourceLoader.getInstance().getConfigManager().getConfiguration();

        if (configuration == null) {
            sender.sendMessage(Coloriser.colorize("&x&f&f&1&1&1&1An error occurred while executing the command."));

            return;
        }

        if (!sender.hasPermission("resource.use")) {
            sender.sendMessage(Coloriser.colorize(
                    configuration.getString("messages.no-perms", "&x&f&f&1&1&1&1You don't have permissions.")));

            return;
        }

        if (args.isEmpty()) {
            sender.sendMessage(Coloriser.colorize(
                    configuration.getString("messages.help", "&x&f&f&f&f&1&1Usage: /resource reload")));

            return;
        }

        final String arg = args.get(0);

        switch (arg) {
            case "reload": {
                final JResourceLoader loader = JResourceLoader.getInstance();

                final ConfigManager configManager = loader.getConfigManager();
                final ResourceManager resourceManager = loader.getResourceManager();

                configManager.reload();
                resourceManager.reload();

                sender.sendMessage(Coloriser.colorize(
                        configuration.getString("messages.reload.success", "&x&1&1&f&f&1&1Plugin success reloaded!")));

                break;
            }
            default: {
                sender.sendMessage(Coloriser.colorize(
                        configuration.getString("messages.reload.arg-not-found", "&x&f&f&1&1&1&1Arg &e{arg} &x&f&f&1&1&1&1not found.")
                                .replace("{arg}", arg)
                ));

                break;
            }
        }
    }

    @Override
    public List<String> hint(final CommandSender sender, final List<String> args) {
        return args.size() != 1 ? Collections.emptyList() : List.of("reload");
    }

}
