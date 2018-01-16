package dao;

/**
 * Created by lenovo on 2018/1/16.
 */
public class User {
    private String locationInfo;
    private int userId;
    private int authStatus;
    private String remarkName;
    private String avatarUrl;
    private String experts;
    private String expertTags;
    private String nickname;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int vipType;
    private int userType;

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public String getExpertTags() {
        return expertTags;
    }

    public void setExpertTags(String expertTags) {
        this.expertTags = expertTags;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "locationInfo='" + locationInfo + '\'' +
                ", userId=" + userId +
                ", authStatus=" + authStatus +
                ", remarkName='" + remarkName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", experts='" + experts + '\'' +
                ", expertTags='" + expertTags + '\'' +
                ", nickname='" + nickname + '\'' +
                ", vipType=" + vipType +
                ", userType=" + userType +
                '}';
    }
}
