package V1.Frontend;

import V1.Library.TofuError;

public class Frontend {
	public static void main(String[] args) {
		init();
		FrontendServer server = new FrontendServer();
		server.start();
		HashServer hashServer = new HashServer();
		hashServer.start();
		DataManager dataManager = new DataManager();
		dataManager.start();
		MiningManager miningManager = new MiningManager();
		miningManager.start();
	}
	static void init() {
		Setting.init();
		MiningManager.init();
		HashServer.init();
		DataManager.init();
		try {
			FrontendServer.init();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TofuError.SettingError("Server init failed.");
		}
	}
}
