package PaooGame.CustomExceptions;

///Aceasta exceptie este aruncata atunci cand o animatie nu are niciun frame
public class EmptyAnimationException extends RuntimeException {
    public EmptyAnimationException(String message) {
        super(message);
    }
}
