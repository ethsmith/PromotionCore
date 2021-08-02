package me.ephan.promotioncore.model;

import me.ephan.promotioncore.PromotionCorePlugin;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

public class Promotion {

	private final PromotionCorePlugin plugin = PromotionCorePlugin.getInstance();

	private final Permission perms = Permissions.getVaultPerms();

	private final Player player;

	private final Rank rank;

	private boolean success = false;

	public Promotion(final Player player) {
		this.player = player;
		this.rank = getNextRank();
		onPromotion();
	}

	public void onPromotion() {
		if (!promotePlayer())
			Common.error(new Error(), "Could not promote " + player.getName() + " to " + rank.getName() + ", are they are online and does the group exist?");

		//processCommands();
		success = true;
	}

	public boolean promotePlayer() {
		if (!groupExists())
			return false;

		perms.playerAddGroup(player, rank.getName());
		removePreviousGroups();
		return true;
	}

	private boolean groupExists() {
		for (final String groupName : perms.getGroups()) {
			if (groupName.equalsIgnoreCase(rank.getName()))
				return true;
		}
		return false;
	}

	private void removePreviousGroups() {
		for (final String existing : perms.getPlayerGroups(player)) {
			if (!existing.equalsIgnoreCase(rank.getName()))
				perms.playerRemoveGroup(player, existing);
		}
	}

	private Rank getNextRank() {
		for (final Rank rank : plugin.getRanks()) {
			if ((getPreviousRank().getOrder() + 1) == rank.getOrder())
				return rank;
		}
		return null;
	}

	private Rank getPreviousRank() {
		for (final Rank rank : plugin.getRanks()) {
			if (rank.getName().equalsIgnoreCase(perms.getPrimaryGroup(player)))
				return rank;
		}
		return null;
	}


//	public void processCommands() {
//		/* TODO Setup config access */
//		final List<String> consoleCommands = Settings.Commands.COMMANDS;
//		final List<String> playerCommands = Settings.Commands.COMMANDS.keySet();
//
//		if (consoleCommands != null && !consoleCommands.isEmpty()) {
//			final ConsoleCommandSender console = plugin.getServer().getConsoleSender();
//			for (final String command : consoleCommands)
//				plugin.getServer().dispatchCommand(console, command.replace("%player%", player.getName()));
//		}
//
//		if (playerCommands != null && !playerCommands.isEmpty())
//			for (final String command : playerCommands)
//				player.performCommand(command);
//	}
//
//	private String[] getConsoleCommands() {
//
//	}
//
//	private String[] getPlayerCommands() {
//		String[] commands =
//	}
//
//	private String getCommands() {
//		for (final String group : Settings.Commands.COMMANDS.keySet()) {
//			if (rank.getName() == group)
//				return Settings.Commands.COMMANDS.get(group);
//		}
//		return null;
//	}

	public Player getPlayer() {
		return player;
	}

	public Rank getRank() {
		return rank;
	}

	public boolean promotionSuccessful() {
		return success;
	}
}
