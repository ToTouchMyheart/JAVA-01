package io.zero.db.annotations;

public enum ActionType {
    DELETE("delete", "delete"),
    /** */
    UPDATE("update", "update"),
    /** */
    ADD("add", "add"),
    /** */
    AUTH("auth", "auth"),
    /** */
    RELEASE("release", "release");


    private String type;
    private String name;

    private ActionType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
