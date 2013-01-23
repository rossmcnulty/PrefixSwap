package net.gnomeffinway.prefixswap;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import net.gnomeffinway.prefixswap.api.PrefixManager;
import net.gnomeffinway.prefixswap.commands.BaseCommandExecutor;
import net.gnomeffinway.prefixswap.listeners.PlayerLoginListener;
import net.gnomeffinway.prefixswap.util.MySQLDatabase;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.gmail.nossr50.mcMMO;

public class PrefixSwap extends JavaPlugin{
	private static PrefixManager manager;
	private static MySQLDatabase mySQL;
	private static Economy economy;
	private static mcMMO mcmmo;

	@Override
	public void onEnable() {
		initDatabase();

		manager = new SimplePrefixManager(this);
		
		getCommand("prefixswap").setExecutor(new BaseCommandExecutor(this));
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		checkPlugins();
		
		PlayerLoginListener login=new PlayerLoginListener(getServer());
		
		getServer().getPluginManager().registerEvents(login, this);
		
        BukkitScheduler scheduler = getServer().getScheduler();
        
        scheduler.scheduleSyncRepeatingTask(this, new PrefixMonitor(this), 0, 200);
		
		getLogger().info("Finished loading " + getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		try {
		      mySQL.close();
		} catch (Exception e) {
		      this.getLogger().severe("[PrefixSwap] Filed to clost LogBlock Database");
		      e.printStackTrace();
		} 
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
	
	public static Economy getEconomy() {
		return economy;
	}

	public static PrefixManager getManager() {
		return manager;
	}
	
	public static mcMMO getMcMMO() {
		return mcmmo;
	}
	
	public static MySQLDatabase getMySQL() {
		return mySQL;
	}
	
	public void checkPlugins() {
		RegisteredServiceProvider<Economy> rspEcon = getServer().getServicesManager().getRegistration(Economy.class);
	     
		mcmmo=(mcMMO) getServer().getPluginManager().getPlugin("mcMMO");
		
        //check if there is an economy plugin
        if (rspEcon != null) {
        	economy = rspEcon.getProvider();
			getServer().getLogger().info("[PrefixSwap] Using " + economy.getName() + " plugin");
        } 
        else {
        	getServer().getLogger().info("[PrefixSwap] Economy plugin not found");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
        
        if(mcmmo != null) {
			getServer().getLogger().info("[PrefixSwap] McMMO hooked");
        }
        else {
        	getServer().getLogger().info("[PrefixSwap] McMMO not found");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
		
		if(connectSQL()) {
			this.getLogger().info("[PrefixSwap] Successfully connected to LogBlock Database");
			keepSQLActive();
		}
		else {
			this.getLogger().severe("[PrefixSwap] Failed to connect to LogBlock Database");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	
	public boolean connectSQL() {
	    try
	    {
	      mySQL = new MySQLDatabase(getConfig().getString("host"), getConfig().getString("port"), getConfig().getString("username"), getConfig().getString("password"), getConfig().getString("database"));
	      mySQL.open();
	      return true;
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return false;
	}
	
	public void keepSQLActive() {
	    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	      public void run() {
	        if ((!PrefixSwap.this.pingSQL()) && 
	          (!PrefixSwap.this.connectSQL())) {
	          PrefixSwap.this.getLogger().severe("[PrefixSwap] Error re-establishing connection to database");
	        } 
	        
	        PrefixSwap.this.keepSQLActive();
	      } 
	    }, 600);
	}
	
	public boolean pingSQL() {
	    try {
	      ResultSet res = mySQL.query("SELECT * FROM `lb-players` LIMIT 1");
	      if (res.next()) {
	        return true;
	      } 
	    }
	    catch (Exception localException) {
	    }
	    
	    return false;
	} 

}

