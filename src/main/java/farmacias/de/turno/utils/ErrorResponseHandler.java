package farmacias.de.turno.utils;

/**
 * Prototipo para manipular respuestas de Error
 */
public class ErrorResponseHandler {
    /**
     * ejemplo 404 , no fue encontrado
     */
    private int code;
    /**
     * ejemplo : no se encontro el recurso
     */
    private String message;

    public ErrorResponseHandler(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
