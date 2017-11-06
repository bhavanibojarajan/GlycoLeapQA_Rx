package com.holmusk.GlycoLeap.Test.dashboard;

import com.holmusk.GlycoLeap.Test.base.UIBaseTest;

import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Factory;

public final class UIDashboardTest extends UIBaseTest implements UIDashboardTestType{

    @Factory(
            dataProviderClass = UIBaseTestType.class,
            dataProvider = "engineProvider"
    )
    public UIDashboardTest(@NotNull Engine<?> engine) {
        super(engine);
    }

}
