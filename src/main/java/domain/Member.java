package domain;

public class Member {
    private String memberId;
    private String userName;
    private String email;
    private String contactNumber;

    private Member(){}

    public Member(Builder builder){
        this.memberId = builder.memberId;
        this.userName = builder.userName;
        this.email = builder.email;
        this.contactNumber = builder.contactNumber;
    }

    public String getMemberId(){
        return memberId;
    }

    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public String toString(){
        return "Member ID: " + memberId + "\n" +
                "Username: " + userName + "\n" +
                "Email: " + email + "\n" +
                "Contact Number: " + contactNumber ;
    }

    public static class Builder{
        private String memberId;
        private String userName;
        private String email;
        private String contactNumber;

        public Builder copy(Member member){
            this.memberId = member.memberId;
            this.userName = member.userName;
            this.email = member.email;
            this.contactNumber = member.contactNumber;
            return this;
        }

        public Builder setMemberId(String memberId){
            this.memberId = memberId;
            return this;
        }

        public Builder setUserName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setContactNumber(String contactNumber){
            this.contactNumber = contactNumber;
            return this;
        }

        public Member build(){
            return new Member(this);
        }
    }
}
