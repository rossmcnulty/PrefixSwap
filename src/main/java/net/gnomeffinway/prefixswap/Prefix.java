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
	ARTISAN("Artisan",'3',"Crafter prefix","Unlocked by crafting over 10,000 items", PrefixState.LOCKED),
	BUTCHER("Butcher",'3',"Mob murderer prefix", "Unlocked by killing 5,000 non-hostile mobs or more", PrefixState.LOCKED),
	MEDALIST("Medalist", '3', "McMMO leader prefix", "Unlocked by being in the top 3 on /mctop", PrefixState.LOCKED),
	MERCHANT("Merchant", '3', "Wealth prefix", "Unlocked by acquiring $50,000 and over", PrefixState.LOCKED),
	VETERAN("Veteran",'3',"Long-term prefix", "Unlocked by playing for 30 hours and over", PrefixState.LOCKED),
	
	//Hidden ranks
	LABRAT("Labrat", '3', "Tester prefix", "Unlocked if subjected to unreasonable server testing", PrefixState.LOCKED, true),
	
	//Buyable ranks
	CREEPER("Creeper",'2',"Mobsters pack","Psssssshhhh...",PrefixState.NOTPURCHASED,true),
	SQUID("Squid",'2',"Mobsters pack","May cause users to float randomly above water",PrefixState.NOTPURCHASED,true),
	ZOMBIE("Zombie",'2',"Mobsters pack","Euurrgghhh",PrefixState.NOTPURCHASED,true),
	PIGMAN("Pigman",'2',"Mobsters pack","Is it a pig? Is it a man? No! It's a Pigman!",PrefixState.NOTPURCHASED,true),
	OCELOT("Ocelot",'2',"Mobsters pack","Keepin' them Creepers at bay all day e'ry day",PrefixState.NOTPURCHASED,true),
	STEVE("Steve",'2',"Mobsters pack bonus","Not quite as crazy as Steve-O",PrefixState.NOTPURCHASED,true);

	
	private String name;
	private char color;
	private String shortDescription;
	private String longDescription;
	private PrefixState state;
	private boolean hidden;
	
	private Prefix(String name, char color, String shortDescription, String longDescription){
		this.name=name;
		this.color=color;
		this.shortDescription=shortDescription;
		this.longDescription=longDescription;
		state=PrefixState.BASE;
		hidden=false;
	}
	
	private Prefix(String name, char color, String shortDescription, String longDescription, PrefixState state){
		this.name=name;
		this.color=color;
		this.shortDescription=shortDescription;
		this.longDescription=longDescription;
		this.state=state;
		hidden=false;
	}
	
	private Prefix(String name, char color, String shortDescription, String longDescription, PrefixState state, boolean hidden){
		this.name=name;
		this.color=color;
		this.shortDescription=shortDescription;
		this.longDescription=longDescription;
		this.state=state;
		this.hidden=hidden;
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
		if(value.equalsIgnoreCase(LABRAT.getName()))
			return LABRAT;
		if(value.equalsIgnoreCase(MEDALIST.getName()))
			return MEDALIST;
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
		if(value.equalsIgnoreCase("&"+LABRAT.getColor()+LABRAT.getName()))
			return LABRAT;
		if(value.equalsIgnoreCase("&"+MEDALIST.getColor()+MEDALIST.getName()))
			return MEDALIST;
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
	
	public boolean isHidden() {
		return hidden;
	}
		
}
