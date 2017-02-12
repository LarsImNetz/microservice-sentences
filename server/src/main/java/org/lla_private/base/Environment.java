package org.lla_private.base;

public enum Environment {

	LIVE {
		@Override
		public String getConfiguration() {
			return "live";
		}

	},
	QA {
		@Override
		public String getConfiguration() {
			return "qa";
		}

	},
	LOCAL {
		@Override
		public String getConfiguration() {
			return "local";
		}

	},
	HOME {
		@Override
		public String getConfiguration() {
			return "home";
		}

	},	
	MOON {
		@Override
		public String getConfiguration() {
			return "moon";
		}

	};

	private static final String ENVIRONMENT_VARIABLE = "lla.environment";

	public abstract String getConfiguration();

	public static Environment get() {
		final String environmentVariable = System.getProperty(ENVIRONMENT_VARIABLE, "HOME");
		return Environment.valueOf(environmentVariable.toUpperCase());
	}

}
