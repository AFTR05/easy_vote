package co.edu.cue.easy_vote.infrastructure.util;


import co.edu.cue.easy_vote.domain.entities.Administrator;
import co.edu.cue.easy_vote.domain.entities.Token;

public class TokenGenerator {

    public static Token generateToken(String jwt, Administrator user){
        Token token = new Token();
        token.setToken(jwt);
        token.setAdministrator(user);
        token.setIsLogOut(false);
        return token;
    }
}
