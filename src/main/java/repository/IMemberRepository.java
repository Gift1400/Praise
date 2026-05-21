package repository;

import domain.Member;

public interface MemberRepo {
    void create(Member member);
    Member read(String memberId);
    void update(Member member);
    void delete(String memberId);
}
