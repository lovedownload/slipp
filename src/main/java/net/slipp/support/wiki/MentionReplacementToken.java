package net.slipp.support.wiki;

import net.slipp.domain.user.SocialUser;
import net.slipp.service.user.SocialUserService;

import org.eclipse.mylyn.wikitext.core.parser.Attributes;
import org.eclipse.mylyn.wikitext.core.parser.LinkAttributes;
import org.eclipse.mylyn.wikitext.core.parser.markup.PatternBasedElement;
import org.eclipse.mylyn.wikitext.core.parser.markup.PatternBasedElementProcessor;

public class MentionReplacementToken extends PatternBasedElement {
    private SocialUserService socialUserService;
    
    public MentionReplacementToken(SocialUserService socialUserService) {
        this.socialUserService = socialUserService;
    }

    @Override
    protected String getPattern(int groupOffset) {
        return "@([a-zA-Z가-힣.@]*)";
    }

    @Override
    protected int getPatternGroupCount() {
        return 1;
    }

    @Override
    protected PatternBasedElementProcessor newProcessor() {
        return new MentionReplacementTokenProcessor(socialUserService);
    }

    private static class MentionReplacementTokenProcessor extends PatternBasedElementProcessor {
        private static final String DEFAULT_MENTION_PREFIX = "@";
        
        private SocialUserService socialUserService;
        
        public MentionReplacementTokenProcessor(SocialUserService socialUserService) {
            this.socialUserService = socialUserService;
        }

        @Override
        public void emit() {
            String name = group(1);
            String profileUrl = getProfileUrl(name);
            
            if (profileUrl != null) {
                Attributes attributes = new LinkAttributes();
                attributes.setCssClass("author-name");
                getBuilder().link(attributes, profileUrl, DEFAULT_MENTION_PREFIX + name);
            } else {
                getBuilder().characters(DEFAULT_MENTION_PREFIX + name);
            }
        }
        
        private String getProfileUrl(String name) {
            SocialUser socialUser = socialUserService.findByUserId(name);
            if (socialUser == null) {
                return null;
            }
            return socialUser.getProfileUrl();
        }
    }
}
