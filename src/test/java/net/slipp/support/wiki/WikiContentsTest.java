package net.slipp.support.wiki;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.slipp.domain.user.SocialUser;
import net.slipp.domain.user.SocialUserBuilder;
import net.slipp.service.user.SocialUserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class WikiContentsTest {
    private static final Logger logger = LoggerFactory.getLogger(WikiContentsTest.class);

    @Mock
    private SocialUserService socialUserService;
    
    private SlippLanguage slippLanguage;
    
    @Before
    public void setup() {
        slippLanguage = new SlippLanguage(socialUserService);
    }
    

    @Test
    public void parse_bullet() throws Exception {
        String source = "* An item in a bulleted (unordered) list \n* Another item in a bulleted list"
                + "\n** Second Level\n** Second Level Items\n*** Third level";
        logger.debug("html : {}", WikiContents.parse(source, slippLanguage));
    }

    @Test
    public void parse_code() throws Exception {
        String source = "{code:title=java}\n WikiContents wikiContents = new WikiContents(); {code}";
        logger.debug("html : {}", WikiContents.parse(source, slippLanguage));
    }

    @Test
    public void mention() throws Exception {
        String source = "나는 @자바지기 가 쓴 글이 참 좋더라.";
        logger.debug("html : {}", WikiContents.parse(source, slippLanguage));
        
        SocialUser socialUser = SocialUserBuilder.aSocialUser().withProfileUrl("http://www.facebook.com/javajigi").build();
        when(socialUserService.findByUserId("자바지기")).thenReturn(socialUser);
        logger.debug("html : {}", WikiContents.parse(source, slippLanguage));
    }
    
    @Test
    public void mentionRegExp() throws Exception {
        String regExp = "@([@a-zA-Z가-힣.]*)";
        String source = "나는 @자바.지기 가 쓴 글이 참 좋더라.";
        Pattern p =Pattern.compile(regExp);
        Matcher m =p.matcher(source);
        assertThat(m.find(), is(true));
        assertThat(m.group(1), is("자바.지기"));
        
        source ="진달이 아이디는 @jhindhal.jhang 이다.";
        m =p.matcher(source);
        assertThat(m.find(), is(true));
        assertThat(m.group(1), is("jhindhal.jhang"));
        
        source ="진달이 아이디는 @@jhindhal.jhang 이다.";
        m =p.matcher(source);
        assertThat(m.find(), is(true));
        assertThat(m.group(1), is("@jhindhal.jhang"));
    }
}