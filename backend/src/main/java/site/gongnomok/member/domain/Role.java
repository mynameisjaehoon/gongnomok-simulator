package site.gongnomok.member.domain;

public enum Role {
    GUEST,USER,ADMIN;

    public String makeLowerString() {
        return this.toString().toLowerCase();
    }
}
