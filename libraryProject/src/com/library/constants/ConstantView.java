package com.library.constants;

public enum ConstantView {
    LOGIN("/login.jsp"),
    ADMIN("/admin.jsp"),
    FORM_BOOK("/frmbook.jsp"),
    MESSAGE_ADMIN("/message_admin.jsp"),
    DETAIL("/detalle.jsp"),
    BOOKS("/books.jsp"),
    REQUEST_FORM("/requestForm.jsp"),
    HOME_PAGE("/homepage"),
    REQUEST("/requests.jsp"),
    INDEX("/index.jsp");


    private String name;

    ConstantView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
