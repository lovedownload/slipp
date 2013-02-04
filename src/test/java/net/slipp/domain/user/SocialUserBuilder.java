package net.slipp.domain.user;

import net.slipp.domain.user.SocialUser;



public class SocialUserBuilder {
    private String userId;

    private String providerId;

    private String providerUserId;

    private int rank;

    private String displayName;

    private String profileUrl;

    private String imageUrl;
    
    public static SocialUserBuilder aSocialUser() {
    	return new SocialUserBuilder();
    }

    public SocialUserBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    public SocialUserBuilder withProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }
    
    public SocialUser build() {
        SocialUser socialUser = new SocialUser();
        socialUser.setUserId(userId);
        socialUser.setProviderId(providerId);
        socialUser.setProviderUserId(providerUserId);
        socialUser.setRank(rank);
        socialUser.setDisplayName(displayName);
        socialUser.setProfileUrl(profileUrl);
        socialUser.setImageUrl(imageUrl);
        return socialUser;
    }
}
