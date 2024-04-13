package nn.iamj.resources.commands;

import nn.iamj.resources.JResourceLoader;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {

    public void register(final String label) {
        final PluginCommand command = JResourceLoader.getInstance().getCommand(label);

        if (command == null) return;

        command.setExecutor(this);
        command.setTabCompleter(this);
    }

    public abstract void execute(final CommandSender sender, final List<String> args);
    public abstract List<String> hint(final CommandSender sender, final List<String> args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.execute(sender, Arrays.asList(args));
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return this.hint(sender, Arrays.asList(args));
    }

}
