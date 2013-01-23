package net.gnomeffinway.prefixswap.commands;

import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RevertCommandExecutor extends PrefixSwapCommand implements CommandExecutor {
	
	private GroupManager gm;

	public RevertCommandExecutor(PrefixSwap plugin) {
		super(plugin);
		this.plugin=plugin;
		gm=(GroupManager) plugin.getServer().getPluginManager().getPlugin("GroupManager");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 1){
			printArgsError(args);
			printHelp(sender, label);
			return true;
		}
		
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
			
		Iterator<PrefixRecord> itr = list.iterator();
		PrefixRecord next;
		while(itr.hasNext()){
			next=itr.next();
			if(next.getState() == PrefixState.BASE){
				setPrefix((Player) sender, Prefix.fromString(next.getPrefix()));
				return true;
			}
		}
		
		sender.sendMessage(COULD_NOT_FIND_PREFIX);
		return true;
	}
		
		
	private void setPrefix(Player base, Prefix prefix) {
		final AnjoPermissionsHandler handler = gm.getWorldsHolder().getWorldPermissions(base);
		
		if (handler == null)
		{
			return;
		}
		
		handler.addUserInfo(base.getName(), "prefix","&"+prefix.getColor()+prefix.getName());
		base.sendMessage(ChatColor.GOLD+"Your rank has been changed to "+ChatColor.getByChar(prefix.getColor())+prefix.getName());
	}
		        
}
