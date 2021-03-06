package net.gnomeffinway.prefixswap.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixSwap;
import net.gnomeffinway.prefixswap.api.NotifyChanges;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener{
	
	private PrefixSwap plugin;
	private GroupManager gm;
	private String PREFIX_NOT_RECOGNIZED="<PLAYER>'s prefix not recognized!";
	
	public PlayerLoginListener(Server server){
		plugin=(PrefixSwap) server.getPluginManager().getPlugin("PrefixSwap");
		gm=(GroupManager) server.getPluginManager().getPlugin("GroupManager");
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerLogin(PlayerLoginEvent event){
		Player plr=event.getPlayer();
		List<PrefixRecord> list = PrefixSwap.getManager().getPrefixes(plr.getName());
		
		//Getting all unlockable ranks
		List<Prefix> unlockables=new ArrayList<Prefix>();
		unlockables.add(Prefix.LABRAT);
		unlockables.add(Prefix.MEDALIST);
		unlockables.add(Prefix.MERCHANT);
		unlockables.add(Prefix.VETERAN);
		Iterator<Prefix> itr=unlockables.iterator();
		
		if(list==null || list.size()==0){
			Prefix prefix=Prefix.fromString(getGroupPrefix(plr));
			if(prefix==null){
				plugin.getServer().getLogger().severe(PREFIX_NOT_RECOGNIZED);
			}
			else
				PrefixSwap.getManager().addPrefix(plr.getName(), prefix, NotifyChanges.TARGET);
		} else {
			Prefix prefix=Prefix.fromString(getGroupPrefix(plr));
			if(prefix==null){
				plugin.getServer().getLogger().severe(PREFIX_NOT_RECOGNIZED);
			}
			else {
				Prefix base = Prefix.fromString(PrefixSwap.getManager().getBasePrefix(plr.getName()).getPrefix());
				if(!base.getName().equals(prefix.getName())) {
					PrefixSwap.getManager().addPrefix(plr.getName(), prefix, NotifyChanges.TARGET);
					PrefixSwap.getManager().delPrefix(plr.getName(), base, NotifyChanges.TARGET);
				}
			}
		}
		
		Prefix next;
		while(itr.hasNext()){
			next=itr.next();
			if(PrefixSwap.getManager().getPrefix(plr.getName(), next.getName())==null){
				PrefixSwap.getManager().addPrefix(plr.getName(), next, NotifyChanges.TARGET);
			}
		}
	}
	
	public String getGroupPrefix(final Player base) {
		final AnjoPermissionsHandler handler = gm.getWorldsHolder().getWorldPermissions(base);
		if (handler == null)
		{
			return null;
		}
		return handler.getGroupPrefix(handler.getGroup(base.getName()));
	}
	
}
