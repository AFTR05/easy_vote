package co.edu.cue.easy_vote.service;

public interface LoginService<T, S>{
    T login(S s);
}
