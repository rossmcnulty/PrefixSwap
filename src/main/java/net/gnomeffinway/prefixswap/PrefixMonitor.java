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
                
        for (Player player : plugin.getServer().getOnlinePlayers()) {
        	
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
    					setPrefix(player, Prefix.fromString(PrefixSwap.getManager().getBasePrefix(player.getName()).getPrefix()));
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
    					setPrefix(player, Prefix.fromString(PrefixSwap.getManager().getBasePrefix(player.getName()).getPrefix()));
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
    					setPrefix(player, Prefix.fromString(PrefixSwap.getManager().getBasePrefix(player.getName()).getPrefix()));
    				}
					return;
   				}
    		}

        }
    }
	
	private void setPrefix(Player base, Prefix prefix) {
		final GroupManager gm = (GroupManager) plugin.getServer().getPluginManager().getPlugin("GroupManager");
		final AnjoPermissionsHandler handler = gm.getWorldsHolder().getWorldPermissions(base);
		
		if (handler == null)
		{
			return;
		}
		
		handler.addUserInfo(base.getName(), "prefix","&"+prefix.getColor()+prefix.getName());
		base.sendMessage(ChatColor.GOLD+"Your prefix has been reverted to "+ChatColor.getByChar(prefix.getColor())+prefix.getName());
	}
}
