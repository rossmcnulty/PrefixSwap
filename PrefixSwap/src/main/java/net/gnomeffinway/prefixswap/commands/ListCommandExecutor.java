package net.gnomeffinway.prefixswap.commands;

import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixSwap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommandExecutor extends PrefixSwapCommand implements CommandExecutor {
	
	PrefixSwap plugin;
	
	public ListCommandExecutor(PrefixSwap plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player) || sender==null){
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
		
		String target = findTarget(sender.getName());
		
		List<PrefixRecord> list = PrefixSwap.getManager().getPrefixes(target);
		
		if(list==null){
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
		
		sender.sendMessage(ChatColor.GOLD + "----- " + ChatColor.AQUA + target + ChatColor.GOLD + " -----");
		
		Iterator<PrefixRecord> iterator = list.iterator();
		while(iterator.hasNext()) {
			PrefixRecord record = iterator.next();
			
			String message = ChatColor.DARK_AQUA + record.getPrefix() + ChatColor.WHITE + ": ";

			message += ChatColor.GRAY + "[";
				
			switch(record.getState()){
				case BASE:
					message += ChatColor.WHITE + "Base Rank";
					break;
				case NOTPURCHASED:
					message += ChatColor.GRAY + "Not Purchased";
					break;
				case NOTUNLOCKED:
					message += ChatColor.GRAY + "Not Unlocked";
					break;
				case UNLOCKED:
					message += ChatColor.GOLD + "Unlocked";
					break;
				default:
					message += ChatColor.RED + "ERROR";
					plugin.getLogger().severe("Invalid PrefixState in ListCommand!");
					break;
				}
				
				message += ChatColor.GRAY + "] ";
				
				message += ChatColor.GOLD + record.getDescShort();

		
			sender.sendMessage(message);
			
		}
		
		return true;
	}
}
