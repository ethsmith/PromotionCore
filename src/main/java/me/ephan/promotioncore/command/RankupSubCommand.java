package me.ephan.promotioncore.command;

import me.ephan.promotioncore.PromotionCorePlugin;
import me.ephan.promotioncore.model.Promotion;
import me.ephan.promotioncore.settings.Localization;
import me.ephan.promotioncore.settings.Settings;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public final class RankupSubCommand extends SimpleSubCommand {

	private final PromotionCorePlugin plugin = PromotionCorePlugin.getInstance();

	public RankupSubCommand(final SimpleCommandGroup parent) {
		super(parent, "rankup");

		setDescription("Rankup to the next rank");
		//setPermission("chatcontrol.command.announce");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		if (Settings.Buy.BUY_ENABLED) {
			if (new Promotion(getPlayer()).promotionSuccessful())
				tell(Localization.Plugin.PLAYER_PROMOTED);
			else
				tell(Localization.Plugin.PLAYER_NOT_PROMOTED);
		} else {
			tell(Localization.Plugin.FEATURE_DISABLED);
		}
	}
}
