package net.gnomeffinway.prefixswap.commands;

import java.util.Iterator;
import java.util.List;

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
		
		sender.sendMessage(ChatColor.GOLD + "-------- " + ChatColor.AQUA + target + ChatColor.GOLD + " --------");
		
		Iterator<PrefixRecord> iterator = list.iterator();
		while(iterator.hasNext()) {
			PrefixRecord record = iterator.next();
			ChatColor color;
			if(!record.isHidden() || record.getState() == PrefixState.UNLOCKED || sender.hasPermission("prefixswap.viewhidden")) {
			
				if(Prefix.fromString(record.getPrefix())==null)
					color=ChatColor.DARK_AQUA;
				else
					color=ChatColor.getByChar(Prefix.fromString(record.getPrefix()).getColor());
							
				String message = color + record.getPrefix() + ChatColor.WHITE + ": ";
	
				message += ChatColor.GRAY + "[";
						
			//	Requirements.medalistCheck(record);
				Requirements.merchantCheck(record);
				Requirements.veteranCheck(record);
				plugin.getDatabase().save(record);
		
				switch(record.getState()){
					case BASE:
						message += ChatColor.GRAY + "Base Rank";
						break;
					case NOTPURCHASED:
						message += ChatColor.GRAY + "Not Purchased";
						break;
					case LOCKED:
						message += ChatColor.GRAY + "Locked";
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
		}
		
		return true;
	}
		        
}
