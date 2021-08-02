package me.ephan.promotioncore.command;

import me.ephan.promotioncore.settings.Localization;
import me.ephan.promotioncore.settings.Settings;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class ApplySubCommand extends SimpleSubCommand {

	public ApplySubCommand(final SimpleCommandGroup parent) {
		super(parent, "apply");

		setDescription("Apply for the starting rank on the server");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		if (Settings.Apply.APPLY_ENABLED) {

		} else {
			tell(Localization.Plugin.FEATURE_DISABLED);
		}
	}
}
