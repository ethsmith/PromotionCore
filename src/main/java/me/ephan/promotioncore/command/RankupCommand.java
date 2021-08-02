package me.ephan.promotioncore.command;

import org.mineacademy.fo.command.SimpleCommand;

public class RankupCommand extends SimpleCommand {

	public RankupCommand() {
		super("rankup");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		getPlayer().performCommand("pc rankup");
	}
}
