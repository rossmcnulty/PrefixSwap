package net.gnomeffinway.prefixswap;

import java.util.List;

import net.gnomeffinway.prefixswap.api.NotifyChanges;
import net.gnomeffinway.prefixswap.api.PrefixManager;

public class SimplePrefixManager implements PrefixManager {
	private PrefixSwap plugin;

	public SimplePrefixManager(PrefixSwap plugin) {
		this.plugin = plugin;
	}

	public List<PrefixRecord> getPrefixes(String playerName) {
		return plugin.getDatabase().find(PrefixRecord.class).where().ieq("target", playerName).findList();
	}

	public PrefixRecord getPrefix(String playerName, String prefix) {
		return plugin.getDatabase().find(PrefixRecord.class).where().ieq("target", playerName).eq("prefix", prefix).findUnique();
	}

	public String addPrefix(String targetName, BasePrefix prefix, NotifyChanges notify) {
		String newPrefix = prefix.getName();
		
		//Should alphabetize this for organization

		PrefixRecord record = new PrefixRecord();
		record.setPrefix(newPrefix);
		record.setTarget(targetName);
		record.setDescShort(prefix.getShortDesc());
		record.setDescLong(prefix.getLongDesc());
		record.setTime(System.currentTimeMillis());
		record.setState(prefix.getDefaultState());

		plugin.getDatabase().save(record);

		return newPrefix;
	}

}
