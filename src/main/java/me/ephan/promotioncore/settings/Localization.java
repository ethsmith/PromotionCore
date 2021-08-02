package me.ephan.promotioncore.settings;

import org.mineacademy.fo.settings.SimpleLocalization;

public class Localization extends SimpleLocalization {

	public Localization() {
		super();
	}

	public static class Plugin {

		public static String FEATURE_DISABLED;

		public static String PLAYER_NOT_PROMOTED;

		public static String PLAYER_PROMOTED;

		private static void init() {
			pathPrefix("PromotionCore");
			FEATURE_DISABLED = getString("Feature_Disabled");
			PLAYER_NOT_PROMOTED = getString("Player_Not_Promoted");
			PLAYER_PROMOTED = getString("Player_Promoted");
		}
	}
}
