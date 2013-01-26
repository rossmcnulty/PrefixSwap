package net.gnomeffinway.prefixswap.api;

import java.util.List;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;

public interface PrefixManager {
	public List<PrefixRecord> getPrefixes(String playerName);

	public PrefixRecord getPrefix(String playerName, String prefix);
	
	public PrefixRecord getBasePrefix(String playerName);

	public String addPrefix(String targetName, Prefix prefix, NotifyChanges notify);
	
	public void delPrefix(String targetName, Prefix prefix, NotifyChanges notify);

}
