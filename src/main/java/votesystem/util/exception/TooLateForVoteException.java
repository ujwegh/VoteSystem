package votesystem.util.exception;

public class TooLateForVoteException extends RuntimeException {
    public TooLateForVoteException(String message) {
        super(message);
    }
}
