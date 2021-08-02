package me.ephan.promotioncore.api;

import me.ephan.promotioncore.PlayerCache;
import me.ephan.promotioncore.PromotionCorePlugin;
import org.bukkit.entity.Player;

/**
 * A sample API class you can build on to use for your plugin.
 */
public final class SampleAPI {

	/**
	 * Return the main instance of this plugin
	 *
	 * @return
	 */
	public static PromotionCorePlugin getAPI() {
		return PromotionCorePlugin.getInstance();
	}

	/**
	 * Get the player cache. Creates the cache if it does not exist.
	 * <p>
	 * Please use with caution since we do create this cache on PlayerJoinEvent
	 * when the player joins automatically.
	 *
	 * @param player
	 * @return
	 */
	public static PlayerCache getPlayerCache(final Player player) {
		return PlayerCache.from(player);
	}
}
