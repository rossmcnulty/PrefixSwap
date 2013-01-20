package net.gnomeffinway.prefixswap;

public enum PrefixState {
	BASE(1), NOTPURCHASED(2), NOTUNLOCKED(3), UNLOCKED(4);

	private int value;

	PrefixState(int value) {
		this.value = value;
	}

	public int toInt() {
		return value;
	}

	public static PrefixState fromInt(int value) {
		switch (value) {
		case 1:
			return BASE;
		case 2:
			return NOTPURCHASED;
		case 3:
			return NOTUNLOCKED;
		case 4:
			return UNLOCKED;
		default:
			return null;
		}
	}
}
