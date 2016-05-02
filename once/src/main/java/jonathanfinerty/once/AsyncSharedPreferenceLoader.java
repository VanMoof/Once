package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

class AsyncSharedPreferenceLoader {

    interface Listener {
        void onLoaded();
    }

    private final Context context;
    private final Listener listener;

    private final AsyncTask<String, Void, SharedPreferences> asyncTask = new AsyncTask<String, Void, SharedPreferences>() {
        @Override
        protected SharedPreferences doInBackground(String... names) {
            return context.getSharedPreferences(names[0], Context.MODE_PRIVATE);
        }

        @Override
        protected void onPostExecute(SharedPreferences sharedPreferences) {
            listener.onLoaded();
        }
    };

    public AsyncSharedPreferenceLoader(Context context, String name, Listener listener) {
        this.context = context;
        this.listener = listener;
        asyncTask.execute(name);
    }

    public SharedPreferences get() {
        try {
            return asyncTask.get();
        } catch (InterruptedException | ExecutionException ignored) {
            return null;
        }
    }
}
