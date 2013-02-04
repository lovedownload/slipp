package net.slipp.support.web.tags;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.slipp.domain.user.SocialUser;

import org.junit.Test;

public class SlippFunctionsTest {
	@Test
	public void isWriter() {
		boolean actual = SlippFunctions.isWriter(new SocialUser(10), new SocialUser(10));
		assertThat(actual, is(true));
	}
	
	@Test
	public void isNotWriter() throws Exception {
		boolean actual = SlippFunctions.isWriter(new SocialUser(10), new SocialUser(11));
		assertThat(actual, is(false));
	}

	@Test
	public void stripHttp() throws Exception {
		String url = "http://localhost:8080";
		String actual = SlippFunctions.stripHttp(url);
		assertThat(actual, is("//localhost:8080"));
	}
}
