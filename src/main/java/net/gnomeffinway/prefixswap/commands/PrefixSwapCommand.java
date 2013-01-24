package net.gnomeffinway.prefixswap.commands;

import net.gnomeffinway.prefixswap.PrefixSwap;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class PrefixSwapCommand {
	protected PrefixSwap plugin;

	protected final String COULD_NOT_FIND_PLAYER = ChatColor.RED + "Could not find that player!";
	protected final String COULD_NOT_FIND_PREFIX = ChatColor.RED + "Could not find that prefix for <PLAYER>!";
	protected final String PREFIX_NOT_UNLOCKED = ChatColor.RED + "That prefix isn't unlocked!";

	public PrefixSwapCommand(PrefixSwap plugin) {
		this.plugin = plugin;
	}

	protected String findTarget(String target) {
		OfflinePlayer search = plugin.getServer().getPlayer(target);

		if(search == null) {
			plugin.getServer().getOfflinePlayer(target);
		}

		if(search != null) {
			target = search.getName();
		}

		return target;
	}

	protected void printArgsError(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Invalid arguments passed to ");
		sb.append(this.getClass().getName());
		sb.append(": ");
		for(String arg : args) {
			sb.append(arg);
			sb.append(" ");
		}
		plugin.getLogger().severe(sb.toString());
	}

	protected void printHelp(CommandSender sender, String label) {
		if(sender.hasPermission("prefixswap.info")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " info <prefix>");
		}
		if(sender.hasPermission("prefixswap.list")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " list");
		}
		if(sender.hasPermission("prefixswap.revert")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " revert");
		}
		if(sender.hasPermission("prefixswap.swap")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " swap <prefix>");
		}
		if(sender.hasPermission("prefixswap.unlock")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " unlock <player> <prefix>");
		}
	}
}
