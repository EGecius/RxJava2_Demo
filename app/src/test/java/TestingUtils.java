import com.egecius.rxjava2_demo_2.rx.utils.UncaughtException;

public class TestingUtils {

    /**
     * Fails tests in case of uncaught errors, which mimics Android's policy.
     * This is especially useful with RxJava, which forwards unhandled errors to it.
     * */
    public static void setUncaughtExceptionsHandlerAsStrict() {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            throw new UncaughtException(throwable);
        });
    }

    /**
     * Allows passing tests in case of uncaught errors.
     *
     * Using this is discouraged and only to be used for legacy tests since on Android
     * application would crash in case of uncaught error.
     * */
    public static void setUncaughtExceptionsHandlerAsLenient() {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            //do nothing to allow tests pass
        });
    }
}
