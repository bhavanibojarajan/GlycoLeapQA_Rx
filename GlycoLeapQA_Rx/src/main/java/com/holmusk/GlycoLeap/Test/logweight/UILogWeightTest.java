package com.holmusk.GlycoLeap.Test.logweight;

import com.holmusk.GlycoLeap.Test.base.UIBaseTest;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.Factory;

public class UILogWeightTest  extends UIBaseTest implements UILogWeightTestType {
    @Factory(
            dataProviderClass = UIBaseTestType.class,
            dataProvider = "engineProvider"
    )
    public UILogWeightTest(@NotNull Engine<?> engine) {
        super(engine);
    }
}{
}
