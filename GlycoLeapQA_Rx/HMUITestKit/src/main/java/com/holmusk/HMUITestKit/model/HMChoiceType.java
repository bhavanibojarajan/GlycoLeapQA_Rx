package com.holmusk.HMUITestKit.model;

/**
 * Created by haipham on 5/19/17.
 */

import org.swiften.xtestkit.base.model.ChoiceInputType;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.xtestkit.android.model.AndroidChoiceInputType;
import org.swiften.xtestkit.ios.model.IOSChoiceInputType;

/**
 * {@link ChoiceInputType} for Holmusk.
 */
public interface HMChoiceType extends
    AndroidChoiceInputType,
    IOSChoiceInputType,
    ErrorProviderType,
    HMInputType {}
