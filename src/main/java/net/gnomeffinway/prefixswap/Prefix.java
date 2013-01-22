package net.gnomeffinway.prefixswap;

public enum Prefix {
	
	//Base ranks
	SERF("Serf", 'f', "Punishment rank", "This rank disallows players from using many features and commands"),
	PEASANT("Peasant", '8', "Default rank", "Base rank for all non-donor and non-Townsman players"),
	TOWNSMAN("Townsman", '8', "Town member rank", "Base rank for players who are part of a town"),
	MINSTREL("Minstrel",'5', "Donor 1 rank", "Base rank for all first tier donors"),
	BARON("Baron",'b', "Donor 2 rank", "Base rank for all second tier donors"),
	DUKE("Duke",'a', "Donor 3 rank", "Base rank for all third tier donors"),
	ARCHDUKE("Archduke",'6',"Donor 4 rank","Base rank for all fourth tier donors"),

	//Unlockable ranks
	CHAMPION("Champion", '3', "McMMO leader rank", "Unlocked by being in the top 3 on /mctop", PrefixState.LOCKED),
	MERCHANT("Merchant", '3', "Wealth rank", "Unlocked by acquiring $25,000 and over", PrefixState.LOCKED),
	VETERAN("Veteran",'3',"Long-term rank", "Unlocked by playing for 30 hours and over", PrefixState.LOCKED);
	
	
	private String name;
	private char color;
	private String shortDescription;
	private String longDescription;
	private PrefixState state;
	
	private Prefix(String name, char color, String shortDescription, String longDescription){
		this.name=name;
		this.color=color;
		this.shortDescription=shortDescription;
		this.longDescription=longDescription;
		state=PrefixState.BASE;
	}
	
	private Prefix(String name, char color, String shortDescription, String longDescription, PrefixState state){
		this.name=name;
		this.color=color;
		this.shortDescription=shortDescription;
		this.longDescription=longDescription;
		this.state=state;
	}
	
	public static Prefix fromString(String value){
		if(value.equalsIgnoreCase(SERF.getName()))
			return SERF;
		if(value.equalsIgnoreCase(PEASANT.getName()))
			return PEASANT;
		if(value.equalsIgnoreCase(TOWNSMAN.getName()))
			return TOWNSMAN;
		if(value.equalsIgnoreCase(MINSTREL.getName()))
			return MINSTREL;
		if(value.equalsIgnoreCase(BARON.getName()))
			return BARON;
		if(value.equalsIgnoreCase(DUKE.getName()))
			return DUKE;
		if(value.equalsIgnoreCase(ARCHDUKE.getName()))
			return ARCHDUKE;
		if(value.equalsIgnoreCase(CHAMPION.getName()))
			return CHAMPION;
		if(value.equalsIgnoreCase(MERCHANT.getName()))
			return MERCHANT;
		if(value.equalsIgnoreCase(VETERAN.getName()))
			return VETERAN;
		if(value.equalsIgnoreCase("&"+SERF.getColor()+SERF.getName()))
			return SERF;
		if(value.equalsIgnoreCase("&"+PEASANT.getColor()+PEASANT.getName()))
			return PEASANT;
		if(value.equalsIgnoreCase("&"+TOWNSMAN.getColor()+TOWNSMAN.getName()))
			return TOWNSMAN;
		if(value.equalsIgnoreCase("&"+MINSTREL.getColor()+MINSTREL.getName()))
			return MINSTREL;
		if(value.equalsIgnoreCase("&"+BARON.getColor()+BARON.getName()))
			return BARON;
		if(value.equalsIgnoreCase("&"+DUKE.getColor()+DUKE.getName()))
			return DUKE;
		if(value.equalsIgnoreCase("&"+ARCHDUKE.getColor()+ARCHDUKE.getName()))
			return ARCHDUKE;
		if(value.equalsIgnoreCase("&"+CHAMPION.getColor()+CHAMPION.getName()))
			return CHAMPION;
		if(value.equalsIgnoreCase("&"+MERCHANT.getColor()+MERCHANT.getName()))
			return MERCHANT;
		if(value.equalsIgnoreCase("&"+VETERAN.getColor()+VETERAN.getName()))
			return VETERAN;
		return null;		
	}
	
	public String getName() {
		return name;
	}
	
	public char getColor(){
		return color;
	}

	public String getShortDesc() {
		return shortDescription;
	}

	public String getLongDesc() {
		return longDescription;
	}

	public PrefixState getState() {
		return state;
	}
		
}
