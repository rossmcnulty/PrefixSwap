package net.gnomeffinway.prefixswap.commands;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnlockCommandExecutor extends PrefixSwapCommand implements CommandExecutor {

	public UnlockCommandExecutor(PrefixSwap plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 3){
			printArgsError(args);
			printHelp(sender, label);
			return true;
		}
		
		String target = findTarget(args[1]);
		
		if(target==null){
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
			
		Prefix prefix = Prefix.fromString(args[2]);
		
		if(prefix == null) {
			sender.sendMessage(COULD_NOT_FIND_PREFIX);
			return true;
		}
		
		PrefixRecord record = PrefixSwap.getManager().getPrefix(sender.getName(), prefix.getName());
		
		if(prefix == null || record==null || (record.getState() != PrefixState.UNLOCKED && record.isHidden() && !sender.hasPermission("prefixswap.viewhidden"))){
			sender.sendMessage(COULD_NOT_FIND_PREFIX);
			return true;
		}
		
		if(record.getState() == PrefixState.UNLOCKED) {
			sender.sendMessage(ChatColor.RED + target + "has already unlocked " + record.getPrefix());
			return true;
		}
		
		record.setState(PrefixState.UNLOCKED);
		plugin.getDatabase().save(record);
		
		Player player = plugin.getServer().getPlayer(target);
		
		if(player != null)
			player.sendMessage(ChatColor.GOLD + record.getDescShort() + " is now "+record.getState().toString().toLowerCase());

		return true;
	}
		
}
