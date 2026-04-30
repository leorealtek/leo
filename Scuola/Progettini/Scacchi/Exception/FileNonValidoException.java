package Scuola.Progettini.Scacchi.Exception;

public class FileNonValidoException extends RuntimeException {

    public FileNonValidoException() {
        super("FIle non valido");
    }

    public FileNonValidoException(String message) {
        super(message);
    }
}