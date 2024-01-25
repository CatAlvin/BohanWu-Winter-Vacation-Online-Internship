package P2PMultithreadingFramework;
public class User {
    private String nickname;
    private int userPort;
    private String userIP;

    public User(String nickname, int userPort, String userIP) {
        this.nickname = nickname;
        this.userPort = userPort;
        this.userIP = userIP;
    }

    public String getNickname() {
        return nickname;
    }

    public int getUserPort() {
        return userPort;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}