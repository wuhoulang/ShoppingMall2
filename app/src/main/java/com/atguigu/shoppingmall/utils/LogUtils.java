package com.atguigu.shoppingmall.utils;


import com.atguigu.shoppingmall.frame.logger.AndroidLogAdapter;
import com.atguigu.shoppingmall.frame.logger.FormatStrategy;
import com.atguigu.shoppingmall.frame.logger.Logger;
import com.atguigu.shoppingmall.frame.logger.PrettyFormatStrategy;

/**
 * @Author: 萧唐
 * @CreateDate: 2020/4/3 15:59
 * @UpdateDate: 2020/4/3 15:59
 * @UpdateUser: 更新者
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description: 作用描述
 */
public class LogUtils {
    public static final String LOG_TAG = "SYSDK";

    public static boolean DEBUG_VERSION = false;

    /**
     * 设置日志开关模式
     *
     * @param isDebugLog
     */
    public static void setDebugLogModel(boolean isDebugLog) {
        DEBUG_VERSION = isDebugLog;
    }

    static {
        FormatStrategy formatStrategy = null;
        if (DEBUG_VERSION) {
            formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(true) // 是否显示线程信息，默认为ture
                    .methodCount(5) // 显示的方法行数，默认为2
//                    .logStrategy() // 更改要打印的日志策略。
                    .tag(LOG_TAG) // 每个日志的全局标记。默认PRETTY_LOGGER
                    .build();
        } else {
            formatStrategy = PrettyFormatStrategy.newBuilder()
                    .methodCount(5) // 显示的方法行数，默认为2
                    .methodOffset(2) // 隐藏内部方法调用到偏移量，默认为5
//                    .logStrategy() // 更改要打印的日志策略。
                    .showThreadInfo(false)//是否显示线程信息，默认为ture
                    .tag(LOG_TAG)//每个日志的全局标记。
                    .build();
        }
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
//            @Override
//            public boolean isLoggable(int priority, @Nullable String tag) {
//                return super.isLoggable(priority, tag);
//            }
//        });
    }

    private LogUtils() {

    }

    // --------------------------------------------以下事件输出不区分环境------------------------------------------

    public static void i(Object o) {
        i(null, String.valueOf(o));
    }

    public static void i(String tag, Object o) {
        if (tag == null)
            Logger.i(String.valueOf(o));
        else
            Logger.t(tag).i(String.valueOf(o));
    }

    public static void d(Object o) {
        d(null, o);
    }

    public static void d(String tag, Object o) {
        if (tag == null)
            Logger.d(o);
        else
            Logger.t(tag).d(o);
    }

    public static void w(Object o) {
        w(null, String.valueOf(o));
    }

    public static void w(String tag, Object o) {
        if (tag == null)
            Logger.w(String.valueOf(o));
        else
            Logger.t(tag).w(String.valueOf(o));
    }

    public static void e(Object o) {
        e(null, String.valueOf(o));
    }

    public static void e(String tag, Object o) {
        if (tag == null)
            Logger.e(String.valueOf(o));
        else
            Logger.t(tag).e(String.valueOf(o));
    }

    public static void json(String json) {
        json(null, json);
    }

    public static void json(String tag, String json) {
        if (tag == null)
            Logger.json(json);
        else
            Logger.t(tag).json(json);
    }

    // --------------------------------------------以下事件仅在debug模式下进行输出------------------------------------------

    public static void debug_i(Object o) {
        if (DEBUG_VERSION && o != null) {
            debug_i(null, String.valueOf(o));
        }
    }

    public static void debug_i(String tag, Object o) {
        if (DEBUG_VERSION && o != null) {
            if (tag == null)
                Logger.i(String.valueOf(o));
            else
                Logger.t(tag).i(String.valueOf(o));
        }
    }

    public static void debug_d(Object o) {
        if (DEBUG_VERSION && o != null) {
            debug_d(null, o);
        }
    }

    public static void debug_d(String tag, Object o) {
        if (DEBUG_VERSION && o != null) {
            if (tag == null)
                Logger.d(o);
            else
                Logger.t(tag).d(o);
        }
    }

    public static void debug_w(Object o) {
        if (DEBUG_VERSION && o != null) {
            debug_w(null, String.valueOf(o));
        }
    }

    public static void debug_w(String tag, Object o) {
        if (DEBUG_VERSION && o != null) {
            if (tag == null)
                Logger.w(String.valueOf(o));
            else
                Logger.t(tag).w(String.valueOf(o));
        }
    }

    public static void debug_e(Object o) {
        if (DEBUG_VERSION && o != null) {
            debug_e(null, String.valueOf(o));
        }
    }

    public static void debug_e(String tag, Object o) {
        if (DEBUG_VERSION && o != null) {
            if (tag == null)
                Logger.e(String.valueOf(o));
            else
                Logger.t(tag).e(String.valueOf(o));
        }
    }

    public static void debug_json(String json) {
        if (DEBUG_VERSION && json != null) {
            debug_json(null, json);
        }
    }

    public static void debug_json(String tag, String json) {
        if (DEBUG_VERSION && json != null) {
            if (tag == null)
                Logger.json(json);
            else
                Logger.t(tag).json(json);
        }
    }
}
