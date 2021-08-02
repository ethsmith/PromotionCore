package me.ephan.promotioncore.command;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;

/**
 * Holds all /chc main commands
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PromotionCoreCommandGroup extends SimpleCommandGroup {

	/**
	 * The singleton of this class
	 */
	@Getter
	private final static SimpleCommandGroup instance = new PromotionCoreCommandGroup();

	/**
	 * @see org.mineacademy.fo.command.SimpleCommandGroup#getHeaderPrefix()
	 */
	@Override
	protected String getHeaderPrefix() {
		return "" + ChatColor.DARK_RED + ChatColor.BOLD;
	}

	@Override
	protected String getCredits() {
		return "&7Visit &fephan.codes/promotioncore &7for more information.";
	}

	/**
	 * @see org.mineacademy.fo.command.SimpleCommandGroup#registerSubcommands()
	 */
	@Override
	protected void registerSubcommands() {

		// Register a sample command for this group
		registerSubcommand(new RankupSubCommand(this));
		registerSubcommand(new ApplySubCommand(this));
		registerSubcommand(new TokenSubCommand(this));

		// Register the premade commands from Foundation
		///registerSubcommand(new PermsCommand(Permissions.class, "promotioncore.command.perms"));
		//registerSubcommand(new DebugCommand("templateplugin.command.debug"));
		registerSubcommand(new ReloadCommand("promotioncore.command.reload"));
	}
}
