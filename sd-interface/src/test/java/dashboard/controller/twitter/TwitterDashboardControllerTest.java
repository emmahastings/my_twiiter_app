package dashboard.controller.twitter;

import dashboard.service.AccountDetailsService;
import dashboard.service.TwitterFollowersService;
import dashboard.service.TwitterSearchService;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by emmakhastings on 08/05/2016.
 *
 * @author emmakhastings
 */
@RunWith(MockitoJUnitRunner.class)
public class TwitterDashboardControllerTest {

    private static final UserDetails USER = new TwitterUserDetailsBuilder()
            .setLanguage("en")
            .setUrl("https://test")
            .setLocation("Dublin")
            .setId(123L).build();

    private static final TweetDetails TEST_TWEET_1 = new TweetDetailsBuilder()
            .setTweet("test")
            .setUser(USER).build();

    private static final TweetDetails TEST_TWEET_2 = new TweetDetailsBuilder()
            .setTweet("test again")
            .setUser(USER).build();

    private static final TwitterFollower FOLLOWER = new TwitterFollowerBuilder()
            .setDescription("follower")
            .setLocation("spain")
            .setScreenName("follower_01")
            .setName("J Smith")
            .setProfileUrl("https://test").build();

    private MockMvc mockMvc;

    private TwitterDashboardController twitterDashboardController;

    @Mock
    private AccountDetailsService accountDetailsService;

    @Mock
    private TwitterSearchService twitterSearchService;

    @Mock
    private TwitterFollowersService twitterFollowersService;

    @Before
    public void setUp() throws Exception {
        twitterDashboardController = new TwitterDashboardController(accountDetailsService, twitterSearchService, twitterFollowersService);
        mockMvc = MockMvcBuilders.standaloneSetup(twitterDashboardController).build();
    }

    @Test
    public void getDashboard() throws Exception {
        mockMvc.perform(get("/twitter_dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("twitter/twitter_dashboard"))
                .andExpect(model().attribute("searchForm", instanceOf(SearchForm.class)));

        verifyZeroInteractions(accountDetailsService);
        verifyZeroInteractions(twitterSearchService);
        verifyZeroInteractions(twitterFollowersService);
    }

    @Test
    public void searchTwitter() throws Exception {
        when(twitterSearchService.search(Matchers.anyString())).thenReturn(Arrays.asList(TEST_TWEET_1, TEST_TWEET_2));

        mockMvc.perform(post("/twitter_dashboard")
                .param("query", "test query"))
                .andExpect(status().isOk())
                .andExpect(view().name("twitter/twitter_dashboard"))
                .andExpect(model().attribute("searchForm", instanceOf(SearchForm.class)))
                .andExpect(model().attribute("searchForm", hasProperty("query", is("test query"))))
                .andExpect(model().attribute("tweets", hasSize(2)))
                .andExpect(model().attribute("tweets", hasItems(TEST_TWEET_1, TEST_TWEET_2)));

        verify(twitterSearchService, times(1)).search("test query");
        verifyZeroInteractions(accountDetailsService);
        verifyZeroInteractions(twitterFollowersService);
    }

    @Test
    public void getUserDetails() throws Exception {
        when(accountDetailsService.getUserDetails()).thenReturn(USER);

        mockMvc.perform(get("/twitter_dashboard/user_details"))
                .andExpect(status().isOk())
                .andExpect(view().name("twitter/user_details"))
                .andExpect(model().attribute("twitterUserDetails", instanceOf(UserDetails.class)))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("language", is("en"))))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("location", is("Dublin"))))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("url", is("https://test"))))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("id", is(123L))));

        verify(accountDetailsService, times(1)).getUserDetails();
        verifyZeroInteractions(twitterSearchService);
        verifyZeroInteractions(twitterFollowersService);
    }

    @Test
    public void getUserDetailsFromId() throws Exception {

        when(accountDetailsService.getUserDetailsById(Matchers.anyLong())).thenReturn(USER);

        mockMvc.perform(get("/twitter_dashboard/user_details/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("twitter/user_details"))
                .andExpect(model().attribute("twitterUserDetails", instanceOf(UserDetails.class)))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("language", is("en"))))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("location", is("Dublin"))))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("url", is("https://test"))))
                .andExpect(model().attribute("twitterUserDetails", hasProperty("id", is(123L))));

        verify(accountDetailsService, times(1)).getUserDetailsById((long) 123);
        verifyZeroInteractions(twitterSearchService);
        verifyZeroInteractions(twitterFollowersService);
    }

    @Test
    public void getFollowers() throws Exception {
        when(twitterFollowersService.getFollowers()).thenReturn(Collections.singletonList(FOLLOWER));

        mockMvc.perform(get("/twitter_dashboard/followers"))
                .andExpect(status().isOk())
                .andExpect(view().name("twitter/followers"))
                .andExpect(model().attribute("twitterFollowers", instanceOf(List.class)))
                .andExpect(model().attribute("twitterFollowers", hasSize(1)))
                .andExpect(model().attribute("twitterFollowers", hasItems(FOLLOWER)));

        verify(twitterFollowersService, times(1)).getFollowers();
        verifyZeroInteractions(accountDetailsService);
        verifyZeroInteractions(twitterSearchService);
    }
}