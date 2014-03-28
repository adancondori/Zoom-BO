package com.vista.zoonv1;

import com.facebook.SessionDefaultAudience;
import com.sromku.simple.fb.Permissions;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.utils.Logger;

import android.app.Application;

public class MyApliccion extends Application {
	/**
	 * Variables para twitter
	 */
	static String TWITTER_CONSUMER_KEY = "gpUjRToNihrM2jz9NgzQlQ";
	static String TWITTER_CONSUMER_SECRET = "Wq7HN7kienLFStuPXAy6KdI6pRPuh5GNVxfcFZKcBos";
	// Preference Constants btnLoginTwitter
	static String PREFERENCE_NAME = "twitter_oauth";
	static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
	static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

	static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

	// Twitter oauth urls
	static final String URL_TWITTER_AUTH = "auth_url";
	static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
	static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

	/**
	 * Variables para Facebook
	 */
	private static final String APP_ID = "599054180142916";
	private static final String APP_NAMESPACE = "zoomacc";

	@Override
	public void onCreate() {
		super.onCreate();
		// set log to true
		Logger.DEBUG_WITH_STACKTRACE = true;

		// initialize facebook configuration
		Permissions[] permissions = new Permissions[] { Permissions.BASIC_INFO,
				Permissions.EMAIL, Permissions.USER_PHOTOS,
				Permissions.USER_BIRTHDAY, Permissions.USER_LOCATION,
				Permissions.PUBLISH_ACTION, Permissions.PUBLISH_STREAM };

		SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
				.setAppId(APP_ID).setNamespace(APP_NAMESPACE)
				.setPermissions(permissions)
				.setDefaultAudience(SessionDefaultAudience.FRIENDS).build();

		SimpleFacebook.setConfiguration(configuration);
	}
}
