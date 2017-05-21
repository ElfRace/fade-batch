package fadebatch.date.enums;

/**
 * 返回的值
 */
public enum HolderType {
	startMainTime {
		@Override
		public int getStartOffset(int amount) {
			return 0;
		}

		@Override
		public int getEndOffset(int amount) {
			return amount - 1;
		}
	},
	endMainTime {
		@Override
		public int getStartOffset(int amount) {
			return 1 - amount;
		}

		@Override
		public int getEndOffset(int amount) {
			return 0;
		}
	},
	foreMiddleMainTime {
		@Override
		public int getStartOffset(int amount) {
			return -(int) (amount / 2.0 - 0.5);
		}

		@Override
		public int getEndOffset(int amount) {
			return amount / 2;
		}
	},
	latterMiddleMainTime {
		@Override
		public int getStartOffset(int amount) {
			return -amount / 2;
		}

		@Override
		public int getEndOffset(int amount) {
			return (int) (amount / 2.0 - 0.5);
		}
	};

	public abstract int getStartOffset(int amount);

	public abstract int getEndOffset(int amount);
}
