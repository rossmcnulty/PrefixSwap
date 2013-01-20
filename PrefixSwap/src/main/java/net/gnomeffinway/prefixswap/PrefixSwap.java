package net.gnomeffinway.prefixswap;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import net.gnomeffinway.prefixswap.api.PrefixManager;
import net.gnomeffinway.prefixswap.commands.BaseCommandExecutor;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PrefixSwap extends JavaPlugin implements Listener{
	private static PrefixManager manager;

	@Override
	public void onEnable() {
		initDatabase();

		manager = new SimplePrefixManager(this);
		
		getCommand("prefixswap").setExecutor(new BaseCommandExecutor(this));
		
		
		
		getLogger().info("Finished loading " + getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		getLogger().info("Finished unloading " + getDescription().getFullName());
	}
	
	private void initDatabase() {
		try {
			getDatabase().find(PrefixRecord.class).findRowCount();
		} catch (PersistenceException ex) {
			getLogger().info("Initializing database");
			this.installDDL();
		}
	}
	
	@Override
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(PrefixRecord.class);
		return list;
	}

	public static PrefixManager getManager() {
		return manager;
	}

}

