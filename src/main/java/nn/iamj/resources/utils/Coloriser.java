package nn.iamj.resources.utils;

import org.bukkit.ChatColor;

public final class Coloriser {

    private Coloriser() {}

    @SuppressWarnings("deprecation")
    public static String colorize(final String string) {
        if (string == null)
            return null;

        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
