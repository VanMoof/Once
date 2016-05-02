package jonathanfinerty.once;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(TestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class InitialisationTests {

    @Test()
    public void initialisationCallback() {

        Once.Listener listener = mock(Once.Listener.class);

        Once.initialise(RuntimeEnvironment.application, listener);

        verify(listener).onInitialised();
    }

}
