package me.ephan.promotioncore.settings;

import org.mineacademy.fo.collection.StrictMap;
import org.mineacademy.fo.settings.SimpleSettings;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.List;

/**
 * A sample settings class, utilizing {@link YamlStaticConfig} with prebuilt settings.yml handler
 * with a bunch of preconfigured keys, see resources/settings.yml
 */
@SuppressWarnings("unused")
public final class Settings extends SimpleSettings {

	/**
	 * @see org.mineacademy.fo.settings.SimpleSettings#getConfigVersion()
	 */
	@Override
	protected int getConfigVersion() {
		return 1;
	}

	/**
	 * Attempt to save comments and symlink settings.yml
	 *
	 * @return
	 */
	@Override
	protected boolean saveComments() {
		return true;
	}

	/**
	 * Place the sections where user can create new "key: value" pairs
	 * here so that they are not removed while adding comments.
	 * <p>
	 * Example use in ChatControl: user can add new channels in "Channels.List"
	 * section so we place "Channels.List" here.
	 *
	 * @return the ignored sections
	 */
	@Override
	protected List<String> getUncommentedSections() {
		return Arrays.asList(
				"Ranks",
				"Commands",
				"Buy.Prices",
				"Time.Times",
				"Kill.Kills");
	}

	public static class Metrics {

		public static Boolean METRICS_ENABLED;

		private static void init() {
			pathPrefix(null);
			METRICS_ENABLED = getBoolean("Metrics.Enabled");
		}
	}

	public static class Hierarchy {

		public static Boolean HIERARCHY_ENABLED;

		private static void init() {
			pathPrefix(null);
			HIERARCHY_ENABLED = getBoolean("Hierarchy.Enabled");
		}
	}

	public static class Ranks {

		public static StrictMap<Integer, String> RANKS;

		private static void init() {
			pathPrefix(null);
			RANKS = new StrictMap<>(getMap("Ranks", Integer.class, String.class));
		}
	}

	public static class Commands {

		public static StrictMap<String, String> COMMANDS;

		private static void init() {
			pathPrefix(null);
			COMMANDS = new StrictMap<>(getMap("Commands", String.class, String.class));
		}
	}

	public static class Token {

		public static Boolean TOKEN_ENABLED;

		private static void init() {
			pathPrefix(null);
			TOKEN_ENABLED = getBoolean("Token.Enabled");
		}
	}

	public static class Apply {

		public static Boolean APPLY_ENABLED;

		public static Boolean APPLY_FREEZE_PLAYER;

		public static Boolean APPLY_MUTE_PLAYER_CHAT;

		public static Boolean APPLY_HIDE_ALL_CHAT;

		public static Boolean APPLY_KICK_WRONG_PASSWORD;

		public static String APPLY_PASSWORD;

		public static String APPLY_DEFAULT_GROUP;

		public static String APPLY_PROMOTION_GROUP;

		private static void init() {
			pathPrefix("Apply");

			APPLY_ENABLED = getBoolean("Enabled");

			APPLY_FREEZE_PLAYER = getBoolean("Freeze_Player");

			APPLY_MUTE_PLAYER_CHAT = getBoolean("Mute_Player_Chat");

			APPLY_HIDE_ALL_CHAT = getBoolean("Hide_All_Chat");

			APPLY_KICK_WRONG_PASSWORD = getBoolean("Kick_Wrong_Password");

			APPLY_PASSWORD = getString("Password");

			APPLY_DEFAULT_GROUP = getString("Default_Group");

			APPLY_PROMOTION_GROUP = getString("Promotion_Group");
		}
	}

	public static class Buy {

		public static Boolean BUY_ENABLED;

		public static StrictMap<String, Double> BUY_PRICES;

		private static void init() {
			pathPrefix("Buy");

			BUY_ENABLED = getBoolean("Enabled");

			BUY_PRICES = new StrictMap<>(getMap("Prices", String.class, Double.class));
		}
	}

	public static class Time {

		public static Boolean TIME_ENABLED;

		public static Boolean TIME_COUNT_OFFLINE;

		public static StrictMap<String, Long> TIME_TIMES;

		public static List<String> TIME_NO_PROMOTE;

		private static void init() {
			pathPrefix("Time");

			TIME_ENABLED = getBoolean("Enabled");

			TIME_COUNT_OFFLINE = getBoolean("Count_Offline");

			TIME_TIMES = new StrictMap<>(getMap("Times", String.class, Long.class));

			TIME_NO_PROMOTE = getStringList("No_Promote");
		}
	}

	public static class Kill {

		public static Boolean KILL_ENABLED;

		public static Boolean KILL_COUNT_FRIENDLY_MOBS;

		public static StrictMap<String, String> KILL_KILLS;

		private static void init() {
			pathPrefix("Kill");

			KILL_ENABLED = getBoolean("Enabled");

			KILL_COUNT_FRIENDLY_MOBS = getBoolean("Count_Friendly_Mobs");

			KILL_KILLS = new StrictMap<>(getMap("Kills", String.class, String.class));
		}
	}

	/*
	 * Automatically called method when we load settings.yml to load values in this class
	 */
	private static void init() {
		pathPrefix(null);
	}
}
