package co.edu.cue.easy_vote.infrastructure.exception;


public class VoterException extends NullPointerException{
    public VoterException(String message) {
        super(message);
    }
}