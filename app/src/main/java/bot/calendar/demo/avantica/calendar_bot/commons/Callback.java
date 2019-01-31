package bot.calendar.demo.avantica.calendar_bot.commons;

/**
 * Created by juanjose on 3/14/18.
 */

public interface Callback<Result> {
    void success(final Result result);

    void failure(final Exception ex);
}
