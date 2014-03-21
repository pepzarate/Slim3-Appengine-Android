package tutorial.controller.twitter;

import org.junit.Test;  

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

import tutorial.model.Tweet;

public class TweetControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.param("content", "Hello");
        tester.start("/twitter/tweet");
        TweetController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/twitter/"));
        Tweet stored = Datastore.query(Tweet.class).asSingle();
        assertThat(stored, is(notNullValue()));
        assertThat(stored.getContent(), is("Hello"));
    }
}