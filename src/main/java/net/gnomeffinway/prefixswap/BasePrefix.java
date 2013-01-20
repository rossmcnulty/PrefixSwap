package net.gnomeffinway.prefixswap;

public class BasePrefix {

	private String name;
	private String shortDescription;
	private String longDescription;
	private PrefixState defaultState;
	
	public BasePrefix(String name, String shortDescription, String longDescription){
		this.name=name;
		this.shortDescription=shortDescription;
		this.longDescription=longDescription;
		defaultState=PrefixState.NOTUNLOCKED;
	}
	
	public String getName() {
		return name;
	}

	public String getShortDesc() {
		return shortDescription;
	}

	public String getLongDesc() {
		return longDescription;
	}

	public PrefixState getDefaultState() {
		return defaultState;
	}
	
}
