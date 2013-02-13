package net.slipp.web.qna;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import net.slipp.domain.user.SocialUser;
import net.slipp.service.user.SocialUserService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/users")
public class SocialUserController {
    private static Logger log = LoggerFactory.getLogger(SocialUserController.class);
    
    @Resource(name="socialUserService")
    private SocialUserService socialUserService;
    
    @RequestMapping("/search")
    public @ResponseBody Collection<String> searchByUserId(String keyword) {
        log.debug("search SocialUser by userId : {}", keyword);
        if (StringUtils.isBlank(keyword)) {
            return Lists.newArrayList();
        }
        
        List<SocialUser> searchedUsers = socialUserService.findsBySearch(keyword);
        if (searchedUsers.isEmpty()) {
            return Lists.newArrayList();
        }
        
        Function<SocialUser, String> userToString = new Function<SocialUser, String>() {
            @Override
            public String apply(SocialUser user) {
                return user.getUserId();
            }
        };
        
        return Collections2.transform(searchedUsers, userToString);
    }
}
