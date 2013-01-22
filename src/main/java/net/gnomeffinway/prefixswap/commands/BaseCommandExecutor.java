package net.gnomeffinway.prefixswap.commands;

import net.gnomeffinway.prefixswap.PrefixSwap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommandExecutor extends PrefixSwapCommand implements CommandExecutor {
	private final String NO_PERMISSION = ChatColor.RED + "You do not have permission to use that command!";

	private CommandExecutor listCommand  = new ListCommandExecutor(plugin);
	private CommandExecutor swapCommand = new SwapCommandExecutor(plugin);
	

	public BaseCommandExecutor(PrefixSwap plugin) {
		super(plugin);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			printHelp(sender, label);
			return true;
		}

		if(args[0].equalsIgnoreCase("list")) {
			if(!sender.hasPermission("prefixswap.list")) {
				sender.sendMessage(NO_PERMISSION);
				return true;
			}
			listCommand.onCommand(sender, cmd, label, args);
		} else if(args[0].equalsIgnoreCase("swap")) {
				if(!sender.hasPermission("prefixswap.swap")) {
					sender.sendMessage(NO_PERMISSION);
					return true;
				}
			swapCommand.onCommand(sender, cmd, label, args);
		} else {
			printHelp(sender, label);
		}
		

		return true;
	}
}
