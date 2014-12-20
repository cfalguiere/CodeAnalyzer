package demo.codeanalyzer.helper;

import javax.lang.model.element.Modifier;

import demo.codeanalyzer.model.BaseJavaClassModelInfo;

/**
 * Util class to set modifier details
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class DataSetterUtil {

    public static void setModifiers(String modifiers, BaseJavaClassModelInfo elementInfo) {
        if (modifiers.contains(Modifier.PUBLIC.toString())) {
            elementInfo.setPublicFlag(true);
        } else if (modifiers.contains(Modifier.PROTECTED.toString())) {
            elementInfo.setProtectedFlag(true);
        } else if (modifiers.contains(Modifier.PRIVATE.toString())) {
            elementInfo.setPrivateFlag(true);
        }

        if (modifiers.contains(Modifier.FINAL.toString())) {
            elementInfo.setFinalFlag(true);
        }

        if (modifiers.contains(Modifier.ABSTRACT.toString())) {
            elementInfo.setAbstractFlag(true);
        }

        if (modifiers.contains(Modifier.NATIVE.toString())) {
            elementInfo.setNativeFlag(true);
        }

        if (modifiers.contains(Modifier.STATIC.toString())) {
            elementInfo.setStaticFlag(true);
        }

    }
}
