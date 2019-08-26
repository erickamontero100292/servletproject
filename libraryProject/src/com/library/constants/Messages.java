package com.library.constants;

public enum Messages {

    BOOK_SAVE("El libro fue guardado correctamente."),
    BOOK_SAVE_ERROR("Ocurrio un error. El libro no fue guardado."),
    ACCESS_DENIED( "Acceso Denegado."),
    USER_PASSWORD_ERROR("Usuario y/o password incorrectos"),
    BOOK_DELETE("El libro fue eliminado correctamente."),
    BOOK_WITH_REQUEST("El libro tiene solicitudes asociadas"),
    FILE_PDF("Solo se permiten archivos de tipo PDF");

    Messages(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    private String message;



}
