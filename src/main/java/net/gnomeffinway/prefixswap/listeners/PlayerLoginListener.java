package net.gnomeffinway.prefixswap.listeners;

import java.util.List;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;

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
		if(list==null || list.size()==0){
			PrefixRecord record = new PrefixRecord();
			Prefix prefix=Prefix.fromString(getPrefix(plr));
			if(prefix==null){
				plugin.getServer().getLogger().severe(PREFIX_NOT_RECOGNIZED);
				return;
			}
			record.setPrefix(prefix.getName());
			record.setTarget(plr.getName());
			record.setDescShort(prefix.getShortDesc());
			record.setDescLong(prefix.getLongDesc());
			record.setTime(System.currentTimeMillis());
			record.setState(PrefixState.BASE);
			plugin.getDatabase().save(record);
		}
	}
	
	public String getPrefix(final Player base)
	{
		final AnjoPermissionsHandler handler = gm.getWorldsHolder().getWorldPermissions(base);
		if (handler == null)
		{
			return null;
		}
		System.out.println(handler.getUserPrefix(base.getName()));
		return handler.getUserPrefix(base.getName());
	}
	
}
