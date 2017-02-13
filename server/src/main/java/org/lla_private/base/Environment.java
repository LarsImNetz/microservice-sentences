package org.lla_private.base;

public enum Environment {

	CORS {
		@Override
		public String getConfiguration() {
			return "cors";
		}
	},
	DEFAULT {
		@Override
		public String getConfiguration() {
			return "";
		}
	};

	private static final String ENVIRONMENT_VARIABLE = "lla.environment";

	public abstract String getConfiguration();

	public static Environment get() {
		final String environmentVariable = System.getProperty(ENVIRONMENT_VARIABLE, "cors");
		return Environment.valueOf(environmentVariable.toUpperCase());
	}

}
