package me.ephan.promotioncore.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ephan.promotioncore.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mineacademy.fo.Messenger;

/**
 * A sample listener for events.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SignClickListener implements Listener {

	/**
	 * The singleton instance
	 */
	@Getter
	private static final SignClickListener instance = new SignClickListener();

	/**
	 * Listen for player join and loads his data
	 *
	 * @param event
	 */
	@EventHandler
	public void onJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();

		PlayerCache.from(player); // Load player's cache
		Messenger.success(player, "Welcome to the game!");
	}
}
