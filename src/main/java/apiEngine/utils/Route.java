package apiEngine.utils;

public class Route {

    private static final String USERS = "/users";
    private static final String COMMENTS = "/comments";
    private static final String POSTS = "/posts";


    public static String generateToken() {
        return null;
    }

    public static String posts() {
        return POSTS;
    }

    public static String post(String id) {
        return POSTS + "/" + id;
    }

    public static String postByUser(String id) {
        return POSTS + "?" + "userId" + "=" +id;
    }

    public static String commentsByPost(String id) {
        return POSTS + "/" +id  + COMMENTS;
    }

    public static String comments() {
        return COMMENTS;
    }

    public static String comment(String id) {
        return COMMENTS + "/" + id;
    }

    public static String users() {
        return USERS;
    }

    public static String user(String id) {
        return USERS + "/" + id;
    }

}
