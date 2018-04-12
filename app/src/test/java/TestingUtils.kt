import com.egecius.rxjava2_demo_2.rx.utils.UncaughtException

object TestingUtils {

    /**
     * Fails tests in case of uncaught errors, which mimics Android's policy.
     * This is especially useful with RxJava, which forwards unhandled errors to it.
     */
    fun setUncaughtExceptionsHandlerAsStrict() {
        Thread.currentThread().setUncaughtExceptionHandler { thread, throwable -> throw UncaughtException(throwable) }
    }

    /**
     * Allows passing tests in case of uncaught errors.
     *
     * Using this is discouraged and only to be used for legacy tests since on Android
     * application would crash in case of uncaught error.
     */
    fun setUncaughtExceptionsHandlerAsLenient() {
        Thread.currentThread().setUncaughtExceptionHandler { thread, throwable ->
            //do nothing to allow tests pass
        }
    }
}
