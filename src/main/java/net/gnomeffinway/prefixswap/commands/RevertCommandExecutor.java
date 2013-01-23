package net.gnomeffinway.prefixswap.commands;

import net.gnomeffinway.prefixswap.Prefix;
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
				
		final AnjoPermissionsHandler handler = gm.getWorldsHolder().getWorldPermissions((Player) sender);
		
		if (handler == null)
		{
			plugin.getServer().getLogger().severe("[PrefixSwap] Could not find handler!");
			return true;
		}

		handler.removeUserInfo(sender.getName(), "prefix");
		Prefix groupPrefix=Prefix.fromString(handler.getGroupPrefix(sender.getName()));
		
		if(groupPrefix == null) {
			return true;
		}
		sender.sendMessage(ChatColor.GOLD+"Your rank has been changed to "+ChatColor.getByChar(groupPrefix.getColor())+groupPrefix.getName());

		return true;
	}
		
		        
}
