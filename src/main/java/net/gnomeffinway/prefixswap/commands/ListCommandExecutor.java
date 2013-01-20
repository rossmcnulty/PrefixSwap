package net.gnomeffinway.prefixswap.commands;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ballzofsteel.LBStats.LBStats;

public class ListCommandExecutor extends PrefixSwapCommand implements CommandExecutor {
	
	PrefixSwap plugin;
	LBStats stats;
	
	public ListCommandExecutor(PrefixSwap plugin) {
		super(plugin);
		this.plugin=plugin;
		stats=(LBStats) plugin.getServer().getPluginManager().getPlugin("LBStats");
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
				
			int num = 3;
			ArrayList<String> topTimes=new ArrayList<String>();
			try {
		    	ResultSet result = LBStats.mySQLDatabase.query("SELECT `playername`, `onlinetime` FROM `lb-players` ORDER BY `onlinetime` DESC LIMIT " + num);
		    	while (result.next()) { 
		    		String name = result.getString("playername");
		    		topTimes.add(name);
		       } 
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			if(record.getPrefix().equals("Veteran") && hasTopTime(sender.getName(),topTimes)){
				if(hasTopTime(sender.getName(),topTimes))
					record.setState(PrefixState.UNLOCKED);
				if(record.getState()==PrefixState.UNLOCKED)
					if(!hasTopTime(sender.getName(),topTimes))
						record.setState(PrefixState.LOCKED);
			}
	
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
	
	private boolean hasTopTime(String name, ArrayList<String> top){
		for(int x=0; x<top.size(); x++)
			if(top.get(x).equals(name))
				return true;	
		return false;
	}
	
		        
}
