package de.zhao.community.enums;

public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer parentType) {
        for(CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
            if(commentTypeEnum.getType()==parentType){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
