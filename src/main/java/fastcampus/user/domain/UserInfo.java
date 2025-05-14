package fastcampus.user.domain;

public class UserInfo {

    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) throws IllegalAccessException {

        if(name==null || name.isEmpty()){
            throw new IllegalAccessException();
        }

        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

}
