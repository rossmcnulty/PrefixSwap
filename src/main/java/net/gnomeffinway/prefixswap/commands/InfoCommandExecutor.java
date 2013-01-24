package net.gnomeffinway.prefixswap.commands;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;
import net.gnomeffinway.prefixswap.util.Requirements;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommandExecutor extends PrefixSwapCommand implements CommandExecutor {
	
	PrefixSwap plugin;
	
	public InfoCommandExecutor(PrefixSwap plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 2){
			printArgsError(args);
			printHelp(sender, label);
			return true;
		}
		
		if(!(sender instanceof Player) || sender==null){
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
		
		Prefix prefix = Prefix.fromString(args[1]);
		
		if(prefix == null) {
			sender.sendMessage(COULD_NOT_FIND_PREFIX);
			return true;
		}
		
		PrefixRecord record = PrefixSwap.getManager().getPrefix(sender.getName(), prefix.getName());
		
		if(prefix == null || record==null || (record.getState() != PrefixState.UNLOCKED && record.isHidden() && !sender.hasPermission("prefixswap.viewhidden"))){
			sender.sendMessage(COULD_NOT_FIND_PREFIX);
			return true;
		}
	
		String message = "";
				
		message += ChatColor.GOLD + "----- " + ChatColor.getByChar(prefix.getColor()) + record.getPrefix() + ChatColor.GOLD + " -----";
		message += "\n" + ChatColor.GRAY + record.getDescLong();
		
		if(record.getState() == PrefixState.BASE) {
			sender.sendMessage(message);
			return true;
		}
		
		message += "\n" + ChatColor.GOLD + "Status: " + ChatColor.GRAY + record.getState();
		message += "\n" + ChatColor.GOLD + "Progress: ";
		if(record.getState() == PrefixState.UNLOCKED)
			message += ChatColor.GRAY + "Complete";
		else {
			message += ChatColor.GRAY + Requirements.progCheck(sender.getName(),prefix);
		}
		
		sender.sendMessage(message);
		
		return true;
	}
		        
}
