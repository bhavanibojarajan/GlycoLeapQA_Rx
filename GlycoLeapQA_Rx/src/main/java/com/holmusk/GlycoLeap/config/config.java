package com.holmusk.GlycoLeap.config;

import com.holmusk.HMUITestKit.android.HMAndroidEngine;
import org.swiften.xtestkit.android.type.AndroidSDK;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.base.TestMode;
import org.swiften.xtestkit.kit.TestKit;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public final class config {

    @NotNull private static final String ANDROID_APP_PACKAGE;
    @NotNull private static final String ANDROID_APP_ACTIVITY;
    @NotNull private static final String ANDROID_APP_PATH;
    @NotNull private static final String IOS_APP_PACKAGE;
    @NotNull private static final String IOS_APP_PATH;
    @NotNull
    private static final List<Engine> ENGINES;
    @NotNull public static final TestKit TEST_KIT;




    public static final int MAX_PHOTO_COUNT = 4;
    public static final int STEP_PER_MIN = 130;


    static {
        ANDROID_APP_PACKAGE = "com.holmusk.glycoleap";
        ANDROID_APP_ACTIVITY = ".ui.activity.SplashActivity";
        ANDROID_APP_PATH = Paths.get("app\\Android", "Glyco.apk").toString();
        IOS_APP_PACKAGE = "com.holmusk.glycoleap";
        IOS_APP_PATH = Paths.get("app", "glyco.app").toString();

        ENGINES = new LinkedList<>();

        ENGINES.add(HMAndroidEngine.builder()
                .withSDK(AndroidSDK.SDK_22)
                .withAppActivity(ANDROID_APP_ACTIVITY)
                .withAppPackage(ANDROID_APP_PACKAGE)
                .withDeviceName("Nexus_4_API_22")
                .withTestMode(TestMode.SIMULATED)
                .build());

        TEST_KIT = TestKit.builder()
                .withEngines(ENGINES)
                .addResourceBundle("GlycoLeap", Locale.US)
                .addResourceBundle("HMUITestKit", Locale.US)
                .build();
    }

    public static int runCount() {
        return ENGINES.size();
    }
}
