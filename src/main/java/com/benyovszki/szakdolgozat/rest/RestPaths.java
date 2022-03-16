package com.benyovszki.szakdolgozat.rest;

public final class RestPaths {

    private RestPaths() {
    }

    public static final String PATH_PARAM = "/{id}";

    public static final String BASE_PATH = "/api";

    public static final String QUERY = "/query";

    public static final String USER_MODIFICATION_PATH = BASE_PATH + "/mod_user";

    public static final String BASIC_USER_PATH = BASE_PATH + "/user";

    public static final String TRANSACTION_PATH = BASE_PATH + "/transaction";

    public static final String CATEGORY_PATH = BASE_PATH +"/category";

}
