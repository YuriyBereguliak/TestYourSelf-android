package ua.edu.nulp.testyourself.utils;

import android.util.Log;

import ua.edu.nulp.testyourself.BuildConfig;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class L {
    private L() {
    }

    /**
     * Prints error message.
     *
     * @param msg message value
     */
    public static void e(final String msg) {
        if (BuildConfig.DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.e(callerClassName, callerMethodName + " :: " + msg);
        }
    }

    /**
     * Prints error message.
     *
     * @param msg message value
     */
    public static void e(final String msg, Throwable error) {
        if (BuildConfig.DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.e(callerClassName, callerMethodName + " :: " + msg, error);
        }
    }

    /**
     * Prints debug message.
     *
     * @param msg message value
     */
    public static void d(final String msg) {
        if (BuildConfig.DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.d(callerClassName, callerMethodName + " :: " + msg);
        }
    }

    /**
     * Prints warninga message.
     *
     * @param msg message value
     */
    public static void w(final String msg) {
        if (BuildConfig.DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.w(callerClassName, callerMethodName + " :: " + msg);
        }
    }
}
