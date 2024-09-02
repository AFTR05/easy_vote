package co.edu.cue.easy_vote.infrastructure.exception;

public class VoteException extends NullPointerException{
    public VoteException(String message) {
        super(message);
    }
}
