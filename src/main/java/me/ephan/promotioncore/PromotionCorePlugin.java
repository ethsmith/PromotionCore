package me.ephan.promotioncore;

import lombok.Getter;
import me.ephan.promotioncore.command.PromotionCoreCommandGroup;
import me.ephan.promotioncore.command.RankupCommand;
import me.ephan.promotioncore.listener.SignClickListener;
import me.ephan.promotioncore.model.Placeholders;
import me.ephan.promotioncore.model.Rank;
import me.ephan.promotioncore.settings.Settings;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.collection.StrictMap;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.model.Variables;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.Lang;
import org.mineacademy.fo.settings.SimpleLocalization;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PluginTemplate is a simple promotioncore you can use every time you make
 * a new plugin. This will save you time because you no longer have to
 * recreate the same skeleton and features each time.
 * <p>
 * It uses Foundation for fast and efficient development process.
 */
public final class PromotionCorePlugin extends SimplePlugin {

	private String loggerHeader;

	/**
	 * Automatically registers the main command group. A command group holds different
	 * commands, such as /chatcontrol is the main command group holding commands
	 * /chatcontrol mute, /chatcontrol clear etc.
	 */
	@Getter
	private final SimpleCommandGroup mainCommand = PromotionCoreCommandGroup.getInstance();

	/**
	 * Automatically load static settings classes. Settings in these classes can only be modified during startup.
	 * If you need to save/edit data during gameplay, such as in minigames plugins, use YamlConfig instead.
	 */
	@Getter
	private final List<Class<? extends YamlStaticConfig>> settings = Arrays.asList(Settings.class, SimpleLocalization.class);

	@Getter
	private final Set<Rank> ranks = new HashSet<>();

	/**
	 * Automatically perform login ONCE when the plugin starts.
	 */
	@Override
	protected void onPluginStart() {
	}

	/**
	 * Automatically perform login when the plugin starts and each time it is reloaded.
	 */
	@Override
	protected void onReloadablesStart() {

		// You can check for necessary plugins and disable loading if they are missing
		Valid.checkBoolean(HookManager.isVaultLoaded(), "You need to install Vault so that we can work with packets, offline player data, prefixes and groups.");

		// Load localization
		Lang.init();

		// Uncomment to load variables
		// Variable.loadVariables();

		// Register variables - PlaceholderAPI compatible
		Variables.addExpansion(Placeholders.getInstance());

		//
		// Add your own plugin parts to load automatically here
		//

		//
		// Add your own commands here
		//
		//registerCommands("promotioncore|pc", new PromotionCoreCommandGroup());
		registerCommand(new RankupCommand());

		// Register events
		registerEvents(SignClickListener.getInstance());

		this.ranks.clear();

		final StrictMap<Integer, String> ranks = Settings.Ranks.RANKS;
		final StrictMap<String, Double> prices = Settings.Buy.BUY_PRICES;
		final StrictMap<String, Long> times = Settings.Time.TIME_TIMES;

		for (final int value : ranks.keySet()) {
			final Rank rank = new Rank(value, ranks.get(value));
			loggerHeader = "[" + rank.getName() + "]";

			if (Settings.Buy.BUY_ENABLED)
				rank.setPrice(prices.get(rank.getName()));

			if (Settings.Time.TIME_ENABLED)
				rank.setTime(times.get(rank.getName()));

			if (Settings.Kill.KILL_ENABLED) {
				if (getKillSettings(rank).length > 2) {
					Common.error(new Error(), loggerHeader + " You only need player and mobs settings. Check the plugin page for examples. Disabling kill promote...");
					Settings.Kill.KILL_ENABLED = false;
					return;
				} else {
					rank.setPlayerKills(getPlayerKills(rank));
					rank.setMobKills(getMobKills(rank));
				}
			}
			this.ranks.add(rank);
			Common.log(rank.getName() + " - " + rank.getOrder());
		}
	}

	private String[] getKillSettings(final Rank rank) {
		final StrictMap<String, String> kills = Settings.Kill.KILL_KILLS;
		return kills.get(rank.getName()).replace(" ", "").split(",");
	}

	private int getKillSetting(final Rank rank, final String key) {
		int killSetting = 0;
		final String firstSetting = getKillSettings(rank)[0];
		final String secondSetting = getKillSettings(rank)[1];

		if (firstSetting.startsWith(key + "=")) {
			try {
				killSetting = Integer.parseInt(firstSetting.split(key + "=")[1]);
			} catch (final NumberFormatException e) {
				Common.error(new Error(), loggerHeader + " Invalid number at players setting");
			}
		} else if (secondSetting.startsWith(key + "=")) {
			try {
				killSetting = Integer.parseInt(secondSetting.split(key + "=")[1]);
			} catch (final NumberFormatException e) {
				Common.error(new Error(), loggerHeader + " Invalid number at players setting");
			}
		}
		return killSetting;
	}

	private int getPlayerKills(final Rank rank) {
		return getKillSetting(rank, "players");

	}

	private int getMobKills(final Rank rank) {
		return getKillSetting(rank, "mobs");
	}

	/* ------------------------------------------------------------------------------- */
	/* Static */
	/* ------------------------------------------------------------------------------- */

	/**
	 * Return the instance of this plugin, which simply refers to a static
	 * field already created for you in SimplePlugin but casts it to your
	 * specific plugin instance for your convenience.
	 *
	 * @return
	 */
	public static PromotionCorePlugin getInstance() {
		return (PromotionCorePlugin) SimplePlugin.getInstance();
	}
}
