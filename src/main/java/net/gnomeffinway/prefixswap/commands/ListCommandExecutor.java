package net.gnomeffinway.prefixswap.commands;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;
import net.milkbowl.vault.economy.Economy;

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
			ChatColor color;
			
			if(Prefix.fromString(record.getPrefix())==null)
				color=ChatColor.DARK_AQUA;
			else
				color=ChatColor.getByChar(Prefix.fromString(record.getPrefix()).getColor());
						
			String message = color + record.getPrefix() + ChatColor.WHITE + ": ";

			message += ChatColor.GRAY + "[";
/*			To be added with SQL	
			if(record.getPrefix().equals("Champion")){
				if(record.getState()==PrefixState.LOCKED && mcTopCheck(sender.getName()))
					record.setState(PrefixState.UNLOCKED);
				else
					if(record.getState()==PrefixState.UNLOCKED && !mcTopCheck(sender.getName()))
						record.setState(PrefixState.LOCKED);
			}
			*/
			
			if(record.getPrefix().equals("Merchant")){
				if(record.getState()==PrefixState.LOCKED && moneyCheck(sender.getName()))
					record.setState(PrefixState.UNLOCKED);
				else
					if(record.getState()==PrefixState.UNLOCKED && !moneyCheck(sender.getName()))
						record.setState(PrefixState.LOCKED);
			}
			
			if(record.getPrefix().equals("Veteran") && record.getState()==PrefixState.LOCKED && timeCheck(sender.getName()))
				record.setState(PrefixState.UNLOCKED);
	
			switch(record.getState()){
				case BASE:
					message += ChatColor.WHITE + "Base Rank";
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
		
		return true;
	}
	
/*	To be added with SQL
	private boolean mcTopCheck(String name){
		mcMMO mc=PrefixSwap.getMcMMO();
		mc.getFlatFileDirectory().
	}
	*/
	
	private boolean moneyCheck(String name){
		int minimum=50000;
		Economy econ=PrefixSwap.getEconomy();
		if(econ.getBalance(name)>=minimum)
			return true;
		return false;
	}
	
	private boolean timeCheck(String name){
		int res=0;
		
		try {
			ResultSet result = PrefixSwap.getMySQL().query("SELECT `onlinetime` FROM `lb-players` WHERE `playername` = '"+name+"' LIMIT 1");
			if(result.next()){
				res = Integer.parseInt(result.getString("onlinetime"));
			}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }
		
		if(res >= 60*60*30)
				return true;
		return false;
	}
		        
}
