package com.dbr.generator.springboot.ws.system.enumeration;

public enum BuildEnvironment {
    GREEN_MAIL("green-mail"), DISABLE_SECURITY("disable-security"), DEV("dev"), TEST("test"), PROD("prod");

    private String environment;

    BuildEnvironment(String environment) {
        this.environment = environment;
    }

    public static BuildEnvironment get(String environment) {
        switch (environment) {
            case "green-mail":
                return GREEN_MAIL;
            case "disable-security":
                return DISABLE_SECURITY;
            case "test":
                return TEST;
            case "prod":
                return PROD;
            default:
            case "dev":
                return DEV;
        }
    }

    public String getEnvironment() {
        return environment;
    }
}
