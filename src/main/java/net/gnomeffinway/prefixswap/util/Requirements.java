package net.gnomeffinway.prefixswap.util;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import net.gnomeffinway.prefixswap.Prefix;
import net.gnomeffinway.prefixswap.PrefixRecord;
import net.gnomeffinway.prefixswap.PrefixState;
import net.gnomeffinway.prefixswap.PrefixSwap;
import net.milkbowl.vault.economy.Economy;

public enum Requirements {
	
	FAILED(0),SAME(1),UNLOCKED(2),LOCKED(3);

	private int value;
	
	Requirements(int value) {
		this.value=value;
	}
	
	public int toInt() {
		return value;
	}
	
	public static Requirements fromInt(int value) {
		switch (value) {
		case 0:
				return FAILED;
		case 1:
				return SAME;
		case 2:
				return UNLOCKED;
		case 3:
				return LOCKED;
		default:
				return null;
		}
	}
	
	/* To be added w/ mcMMO SQL
	public static Requirments medalistCheck(PrefixRecord record) {
		if(record.getPrefix().equals("Medalist")){
			if(record.getState() == PrefixState.UNLOCKED){
				if(mcTopCheck(record.getTarget()))
					return SAME;
				else {
					record.setState(PrefixState.LOCKED);
					return LOCKED;
				}	
			}
			else
				if(mcTopCheck(record.getTarget())){
					record.setState(PrefixState.UNLOCKED);
					return UNLOCKED;
				}
		}
		return FAILED;
	}
	*/	
	
	public static Requirements merchantCheck(PrefixRecord record) {
		if(record.getPrefix().equals("Merchant")){
			if(record.getState() == PrefixState.UNLOCKED){
				if(moneyCheck(record.getTarget()))
					return SAME;
				else {
					record.setState(PrefixState.LOCKED);
					return LOCKED;
				}	
			}
			else
				if(moneyCheck(record.getTarget())){
					record.setState(PrefixState.UNLOCKED);
					return UNLOCKED;
				}
		}
		return FAILED;
	}
	
	public static Requirements veteranCheck(PrefixRecord record) {
		if(record.getPrefix().equals("Veteran")){
			if(record.getState() == PrefixState.UNLOCKED){
				if(timeCheck(record.getTarget()))
					return SAME;
				else {
					record.setState(PrefixState.LOCKED);
					return LOCKED;
				}	
			}
			else
				if(timeCheck(record.getTarget())){
					record.setState(PrefixState.UNLOCKED);
					return UNLOCKED;
				}
		}
		return FAILED;
	}
	
	
/*	To be added with SQL
	publis static boolean mcTopCheck(String name) {
		mcMMO mc=PrefixSwap.getMcMMO();
		mc.getFlatFileDirectory().
	}
	*/
	
	public static boolean moneyCheck(String name) {
		int minimum=50000;
		Economy econ=PrefixSwap.getEconomy();
		if(econ.getBalance(name)>=minimum)
			return true;
		return false;
	}
	
	public static boolean timeCheck(String name) {
		int res=0;
		
		try {
			ResultSet result = PrefixSwap.getMySQL().query("SELECT `onlinetime` FROM `lb-players` WHERE `playername` = '"+name+"' LIMIT 1");
			if(result.next()){
				res = Integer.parseInt(result.getString("onlinetime"));
			}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }
		
		if(res >= 60*60*30)
				return true;
		return false;
	}
	
	public static String progCheck(String name, Prefix prefix) {
	
		if(prefix.getName().equals("Merchant")) {
			double bal=0;
			Economy econ=PrefixSwap.getEconomy();
			bal=econ.getBalance(name);
			return toDecimal(bal,2);
		}
		
		if(prefix.getName().equals("Veteran")) {
			int res=0;
			double resConverted=0;
			
			try {
				ResultSet result = PrefixSwap.getMySQL().query("SELECT `onlinetime` FROM `lb-players` WHERE `playername` = '"+name+"' LIMIT 1");
				if(result.next()){
					res = Integer.parseInt(result.getString("onlinetime"));
					resConverted = res/3600;
					return toDecimal(resConverted, 2) + " hours";
				}
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    	return null;
		    }
		}
		
		return "No progress found";
	}
	
	public static String toDecimal(double num, int place) {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		return df.format(num);
	}
	
}
