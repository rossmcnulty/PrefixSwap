package net.gnomeffinway.prefixswap;

import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.prefixswap.util.Requirements;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PrefixMonitor implements Runnable {
    private final PrefixSwap plugin;

    public PrefixMonitor(final PrefixSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String name;
        final GroupManager gm = (GroupManager) plugin.getServer().getPluginManager().getPlugin("GroupManager");
                
        for (Player player : plugin.getServer().getOnlinePlayers()) {
        	
    		final AnjoPermissionsHandler handler = gm.getWorldsHolder().getWorldPermissions(player);
        	name=player.getName();
    		List<PrefixRecord> list = PrefixSwap.getManager().getPrefixes(name);

    		if(list == null)
    			return;
    		
    		Iterator<PrefixRecord> itr = list.iterator();
    		PrefixRecord next;
    		while(itr.hasNext()) {
    			next=itr.next();
    /*			Requirements medalist=Requirements.medalistCheck(next)
     * 			if(medalist != Requirements.FAILED && medalist != Requirements.SAME && player.isOnline()) {
    				if(medalist == Requirements.UNLOCKED) {
    					plugin.getDatabase().save(next);
    					player.sendMessage(ChatColor.GOLD + next.getDescShort() + " is now "+next.getState().toString().toLowerCase());
    				}
    				else {
    					plugin.getDatabase().save(next);
    					player.sendMessage(ChatColor.GOLD + next.getDescShort() + " is now "+next.getState().toString().toLowerCase());
    					
    					handler.removeUserInfo(player.getName(), "prefix");
    					Prefix groupPrefix=Prefix.fromString(handler.getGroupPrefix(player.getName()));
    					
    					if(groupPrefix == null) {
    						return;
    					}
    					player.sendMessage(ChatColor.GOLD+"Your rank has been changed to "+ChatColor.getByChar(groupPrefix.getColor())+groupPrefix.getName());
    				}
					return;
   				}
   	*/			
    			Requirements merchant=Requirements.merchantCheck(next);
    			if(merchant != Requirements.FAILED && merchant != Requirements.SAME && player.isOnline()) {
    				if(merchant == Requirements.UNLOCKED) {
    					plugin.getDatabase().save(next);
    					player.sendMessage(ChatColor.GOLD + next.getDescShort() + " is now "+next.getState().toString().toLowerCase());
    				}
    				else {
    					plugin.getDatabase().save(next);
    					player.sendMessage(ChatColor.GOLD + next.getDescShort() + " is now "+next.getState().toString().toLowerCase());
    					
    					handler.removeUserInfo(player.getName(), "prefix");
    					Prefix groupPrefix=Prefix.fromString(handler.getGroupPrefix(player.getName()));
    					
    					if(groupPrefix == null) {
    						return;
    					}
    					player.sendMessage(ChatColor.GOLD+"Your rank has been changed to "+ChatColor.getByChar(groupPrefix.getColor())+groupPrefix.getName());
    				}
					return;
   				}
    			
    			Requirements veteran=Requirements.veteranCheck(next);
    			if(veteran != Requirements.FAILED && veteran != Requirements.SAME && player.isOnline()) {
    				if(veteran == Requirements.UNLOCKED) {
    					plugin.getDatabase().save(next);
    					player.sendMessage(ChatColor.GOLD + next.getDescShort() + " is now "+next.getState().toString().toLowerCase());
    				}
    				else {
    					plugin.getDatabase().save(next);
    					player.sendMessage(ChatColor.GOLD + next.getDescShort() + " is now "+next.getState().toString().toLowerCase());
    					
    					handler.removeUserInfo(player.getName(), "prefix");
    					Prefix groupPrefix=Prefix.fromString(handler.getGroupPrefix(player.getName()));
    					
    					if(groupPrefix == null) {
    						return;
    					}
    					player.sendMessage(ChatColor.GOLD+"Your rank has been changed to "+ChatColor.getByChar(groupPrefix.getColor())+groupPrefix.getName());
    				}
					return;
   				}
    		}

        }
    }

}
