package com.holmusk.GlycoLeap.model;

import com.holmusk.HMUITestKit.model.HMTextType;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.collection.HPIterables;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.javautilities.number.HPNumbers;
import org.swiften.javautilities.string.HPStrings;
import org.swiften.javautilities.util.HPLog;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.base.model.InputType;
import org.swiften.xtestkit.ios.IOSView;
import org.swiften.xtestkit.mobile.Platform;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.xpath.*;

import java.util.List;
import java.util.stream.Collectors;

public enum TextInput implements ErrorProviderType, HMTextType {
    USER_NAME,
    EMAIL,
    MEAL_COMMENT,
    MEAL_DESCRIPTION,

         PASSWORD;



    @NotNull
    @Override
    public String randomInput(@NotNull InputHelperType helper) {
        switch (this) {
            case USER_NAME:
            case PASSWORD:
                return "testQA-" + HPStrings.randomString(10);



            case EMAIL:

                return "testQA-" + HPStrings.randomString(10) + "@gmail.com";



            case MEAL_COMMENT:
            case MEAL_DESCRIPTION:
                return HPStrings.randomString(100);

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }


    /**
     * Override this method to provide default implementation.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} value.
     * @see InputType#inputViewXP(InputHelperType)
     * @see #androidInputViewXP(InputHelperType)
     * @see #iOSInputViewXP(InputHelperType)
     */
    @NotNull
    @Override
    public XPath inputViewXP(@NotNull InputHelperType helper) {
        PlatformType platform = helper.platform();

        switch ((Platform)platform) {
            case ANDROID:
                return androidInputViewXP(helper);

            case IOS:
                return iOSInputViewXP(helper);

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

    /**
     * Get {@link XPath} for the input view for {@link Platform#ANDROID}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsID(String)
     */
    @NotNull
    private XPath androidInputViewXP(@NotNull InputHelperType helper) {
        Attributes attrs = Attributes.of(Platform.ANDROID);

        final String ID;

        switch (this) {
            case USER_NAME:
                ID = "et_name";
                break;

            case EMAIL:
               ID = "et_email";
                break;



            case PASSWORD:
                ID = "et_password";
                break;



            case MEAL_DESCRIPTION:
                ID = "ac_logfood_desc";
                break;

            case MEAL_COMMENT:
                ID = "et_comment";
                break;

            default:
                HPLog.printlnt(this);
                throw new RuntimeException(NOT_AVAILABLE);
        }

        Attribute attribute = attrs.containsID(ID);
        return XPath.builder().addAttribute(attribute).build();
    }

    /**
     * Get {@link XPath} for the input view for {@link Platform#IOS}.
     * @param helper {@link InputHelperType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsText(String)
     * @see Axes#followingSibling(AttributeType)
     * @see #iOSShortDescription()
     */
    @NotNull
    private XPath iOSInputViewXP(@NotNull InputHelperType helper) {
        final PlatformType PLATFORM = helper.platform();
        Attributes attrs = Attributes.of(PLATFORM);
        LocalizerType localizer = helper.localizer();
        String shortDsc = iOSShortDescription();
        String localized = localizer.localize(shortDsc);
        Attribute ctText = attrs.containsText(localized);

        switch (this) {
            /* For these cases, the short texts are displayed by a neighboring
             * static text label */

            default:
                List<AttributeType> clsAttrs = HPIterables
                        .asList(
                                IOSView.Type.UI_SECURE_TEXT_FIELD,
                                IOSView.Type.UI_TEXT_VIEW,
                                IOSView.Type.UI_TEXT_FIELD
                        )
                        .stream()
                        .map(a -> Attribute.<String>builder()
                                .addAttribute(PLATFORM.classAttribute())
                                .withValue(a.className())
                                .withFormatible(Formatibles.containsString())
                                .withJoiner(Joiner.OR)
                                .build())
                        .collect(Collectors.toList());

                AttributeBlock clsBlock = AttributeBlock.builder()
                        .addAttribute(clsAttrs)
                        .withJoiner(Joiner.OR)
                        .build();

                CompoundAttribute cAttr = CompoundAttribute.builder()
                        .addAttribute(clsBlock)
                        .addAttribute(ctText)
                        .build();

                return XPath.builder().addAttribute(cAttr).build();
        }
    }
    /**
     * Get a short description of the input field, to use with
     * {@link #iOSInputViewXP(InputHelperType)}. This is done so we do not
     * have to search for the input fields by their indexes.
     * @return {@link String} value.
     */
    @NotNull
    private String iOSShortDescription() {
        switch (this) {
            case USER_NAME:
                     return "user_title_abbv_name";


            case EMAIL:
                    return "user_title_abbv_email";



            case PASSWORD:
                return "user_title_abbv_password";



            case MEAL_DESCRIPTION:
                return "mealLog_title_abbv_description";

            case MEAL_COMMENT:
                return "mealLog_title_abbv_comment";

            default:
                HPLog.println(this);
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }

}
