package com.holmusk.GlycoLeap.Test.css;

import com.holmusk.GlycoLeap.Test.base.UIBaseTest;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import com.holmusk.GlycoLeap.Test.css.css.UICSSValueTestType;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Factory;

public class UICSSValueTest extends UIBaseTest implements UICSSValueTestType {

    @Factory(
            dataProviderClass = UIBaseTestType.class,
            dataProvider = "engineProvider"
    )
    public UICSSValueTest(@NotNull Engine<?> engine) {
        super(engine);
    }
}
