package de.zhao.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "This question doesn't exist."),
    TARGET_PARAM_NOT_FOUND(2002, "No question or comment is selected"),
    UN_LOGIN(2003, "Sorry, you cannot comment. Please login first."),
    SYS_ERROR(2004, "Sorry, server problem"),
    TYPE_PARAM_WRONG(2005, "This comment type doesn't exist."),
    COMMENT_NOT_FOUND(2006, "This comment doesn't exist."),
    CONTENT_IS_EMPTY(2007, "Content cannot be empty.")
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
