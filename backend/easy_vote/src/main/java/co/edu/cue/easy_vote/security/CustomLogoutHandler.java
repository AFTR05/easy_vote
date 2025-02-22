package co.edu.cue.easy_vote.security;

import co.edu.cue.easy_vote.domain.entities.Token;
import co.edu.cue.easy_vote.infrastructure.repository.TokenRepository;
import co.edu.cue.easy_vote.infrastructure.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader(Constants.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
            return;
        }
        String token = authHeader.substring(Constants.SUB_STRING_HEADER_HTTP);
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken != null) {
            storedToken.setIsLogOut(true);
            tokenRepository.save(storedToken);
        }
    }


}
