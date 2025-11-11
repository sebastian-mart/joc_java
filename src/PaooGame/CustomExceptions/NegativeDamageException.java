package PaooGame.CustomExceptions;

/// Este aruncata atunci cand atacatorul acorda damage negativ
public class NegativeDamageException extends IllegalArgumentException {
    public NegativeDamageException(int damage) {
        super("Damage cannot be negative: " + damage);
    }
}
