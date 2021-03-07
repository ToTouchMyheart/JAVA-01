package io.zero.db.annotations;

public enum ActivityType {
    GROUP("group", "group"),
    /** */
    GROUP_USER("groupUser", "groupUser"),
    /** */
    PROJECT("project", "project"),
    /** */
    PROJECT_USER("projectUser", "projectUser"),
    /** */
    FLOW("testFlow", "testFlow"),
    /** */
    CASE("testCase", "testCase"),
    /** */
    TASK_PLAN("taskPlan", "taskPlan");

    private String type;
    private String name;

    private ActivityType(String type, String name) {
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
