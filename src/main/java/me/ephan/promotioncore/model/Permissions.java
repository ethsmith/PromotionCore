package me.ephan.promotioncore.model;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mineacademy.fo.command.PermsCommand;
import org.mineacademy.fo.command.annotation.Permission;
import org.mineacademy.fo.command.annotation.PermissionGroup;
import org.mineacademy.fo.constants.FoPermissions;

/**
 * A sample permissions class. This is the preferred way of keeping all permissions
 * of your plugin in one place.
 * <p>
 * You will also be able to use the {@link PermsCommand} to list them automatically
 * if you choose to this class.
 */
public final class Permissions extends FoPermissions {

	private final static RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);

	private static final net.milkbowl.vault.permission.Permission perms = permProvider.getProvider();

	/**
	 * A sample permission group for your convenience. The {@link PermissionGroup}
	 * is used in the {@link PermsCommand} for your convenience automatically.
	 */
	@PermissionGroup("Execute main plugin command for /{label}.")
	public static final class Command {

		@Permission("Sends a sample message")
		public static final String SAMPLE = "plugintemplate.command.sample";
	}

	public static net.milkbowl.vault.permission.Permission getVaultPerms() {
		return perms;
	}
}
