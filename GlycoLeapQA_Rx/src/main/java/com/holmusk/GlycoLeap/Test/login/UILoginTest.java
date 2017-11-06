package com.holmusk.GlycoLeap.Test.login;

import com.holmusk.GlycoLeap.Test.base.UIBaseTest;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Factory;

public class UILoginTest extends UIBaseTest implements UILoginTestType{
    @Factory(
            dataProviderClass = UIBaseTestType.class,
            dataProvider = "engineProvider"
    )
    public UILoginTest(@NotNull Engine<?> engine) {
        super(engine);
    }
}
