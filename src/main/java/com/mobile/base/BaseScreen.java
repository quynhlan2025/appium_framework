package com.mobile.base;

import com.jayway.jsonpath.JsonPath;
import com.mobile.TestObject;
import com.mobile.helper.FileHelper;
import com.mobile.helper.LogHelper;
import com.mobile.keywords.MobileKeywords;

import org.slf4j.Logger;

import java.io.File;
import java.util.HashMap;

public class BaseScreen {
    protected static final Logger logger = LogHelper.getLogger();
    protected MobileKeywords mobileAction;
    protected HashMap<String, String> repoFile;

    protected static String OBJECT_REPO = "object_repo";

//  protected BaseScreen() {
//    mobileAction = new MobileKeywords();
//  }

    protected BaseScreen(MobileKeywords action) {
        this.mobileAction = action;
    }

    public String setRepoFile(String strRepoFile) {
        if (this.repoFile == null) {
            this.repoFile = new HashMap<>();
        }
        // String strClassName = Reflection.getCallerClass(2).getSimpleName();
        String strClassName = getCallerClass(2).getSimpleName();
        logger.info("Set Repo File - Get caller class 2: " + strClassName); //Set Repo File - Get caller class 2: W3SchoolSignUp

//      Json file
        strRepoFile = strRepoFile + File.separator + strClassName + ".json"; // Repo file relative path: object_repository\W3SchoolSignUp.json
//    logger.info("Repo file relative path: " + strRepoFile);
        strRepoFile = FileHelper.getCorrectJsonFilePath(strRepoFile);
        return this.repoFile.put(strClassName, strRepoFile);
    }

    private static Class<?> getCallerClass(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        try {
            return getCallerClassFromStackTrace(index + 1);
        } catch (final ClassNotFoundException e) {
            logger.error("Could not find class in ReflectionUtil.getCallerClass({}), index<" + index + ">"
                    + ", exception< " + e + ">");
        }
        return null;
    }

    private static Class<?> getCallerClassFromStackTrace(final int index) throws ClassNotFoundException {

        final StackTraceElement[] elements = new Throwable().getStackTrace();
        int i = 0;
        for (final StackTraceElement element : elements) {
            if (isValidlMethod(element)) {
                if (i == index) {
                    return Class.forName(element.getClassName());
                }
                ++i;
            }
        }
        throw new IndexOutOfBoundsException(Integer.toString(index));
    }

    private static boolean isValidlMethod(final StackTraceElement element) {
        if (element.isNativeMethod()) {
            return false;
        }
        final String cn = element.getClassName();
        if (cn.startsWith("sun.reflect.")) {
            return false;
        }
        final String mn = element.getMethodName();
        if (cn.startsWith("java.lang.reflect.") && (mn.equals("invoke") || mn.equals("newInstance"))) {
            return false;
        }
        if (cn.equals("java.lang.Class") && mn.equals("newInstance")) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public String getRepoFile() {
        String strClassName = getCallerClass(3).getSimpleName();
        logger.info("Class name: " + strClassName);
        return this.repoFile.get(strClassName);
    }

    /**
     * @param name
     * @return
     */
    public TestObject findTestObject(String name) {
        String repoFile = this.getRepoFile();
        logger.info("Repo file: " + repoFile);
        try {
            File jsonRepoFile = new File(repoFile);

            String locatorValue = JsonPath.read(jsonRepoFile, "$." + name);
            return new TestObject(name, locatorValue);
        } catch (Exception e) {
            logger.error("Error message: " + e.getMessage());
        }
        return null;
    }

}
