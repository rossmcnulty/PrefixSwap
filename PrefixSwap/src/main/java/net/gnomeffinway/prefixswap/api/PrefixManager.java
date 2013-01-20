package net.gnomeffinway.prefixswap.api;

import java.util.List;

import net.gnomeffinway.prefixswap.BasePrefix;
import net.gnomeffinway.prefixswap.PrefixRecord;

public interface PrefixManager {
	public List<PrefixRecord> getPrefixes(String playerName);

	public PrefixRecord getPrefix(String playerName, String prefix);

	public String addPrefix(String targetName, BasePrefix prefix, NotifyChanges notify);

}
