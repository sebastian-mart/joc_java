package PaooGame.CustomExceptions;

/// Aceasta exceptie este aruncata atunci cand indexul armei selectate depaseste range-ul index-urilor de arme
public class InvalidWeaponIndexException extends RuntimeException {
    public InvalidWeaponIndexException(int index) {
        super("Invalid weapon index: " + index);
    }
}
