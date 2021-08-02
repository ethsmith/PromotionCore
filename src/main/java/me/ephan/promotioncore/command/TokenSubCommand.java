package me.ephan.promotioncore.command;

import me.ephan.promotioncore.settings.Localization;
import me.ephan.promotioncore.settings.Settings;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class TokenSubCommand extends SimpleSubCommand {

	public TokenSubCommand(final SimpleCommandGroup parent) {
		super(parent, "token");

		setDescription("Use a token to receive a certain rank.");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		if (Settings.Token.TOKEN_ENABLED) {

		} else {
			tell(Localization.Plugin.FEATURE_DISABLED);
		}
	}
}
