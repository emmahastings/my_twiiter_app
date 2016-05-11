package dashboard.controller.twitter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by emmakhastings on 08/05/2016.
 *
 * @author emmakhastings
 */
@RunWith(MockitoJUnitRunner.class)
public class TwitterConnectionControllerTest {

    private MockMvc mockMvc;

    private TwitterConnectionController twitterConnectionController;

    @Mock
    private Twitter twitter;

    @Mock
    private ConnectionRepository connectionRepository;

    @Mock
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        twitterConnectionController = new TwitterConnectionController(twitter, connectionRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(twitterConnectionController).build();
    }

    @Test
    public void connectTwitter() throws Exception {
        // Stub connection behaviour
        when(connectionRepository.findPrimaryConnection(Twitter.class)).thenReturn(connection);

        mockMvc.perform(get("/twitter_connect"))
                .andExpect(status().is3xxRedirection());
    }
}