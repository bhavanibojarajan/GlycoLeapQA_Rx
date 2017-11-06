package com.holmusk.GlycoLeap.Test.TestStart;

import com.holmusk.GlycoLeap.Test.base.UIBaseTest;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Factory;

public class UILogicValidation extends UIBaseTest implements UIBaseTestType,
        UILogWeightTestType,
        UILoginTestType

{
    @Factory(
            dataProviderClass = UIBaseTestType.class,
            dataProvider = "engineProvider"
    )
    public UILogicValidation(@NotNull Engine<?> engine) {
        super(engine);
    }
}
