package net.gnomeffinway.prefixswap;

public enum PrefixState {
	BASE(1), NOTPURCHASED(2), LOCKED(3), UNLOCKED(4);

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
			return LOCKED;
		case 4:
			return UNLOCKED;
		default:
			return null;
		}
	}
}
