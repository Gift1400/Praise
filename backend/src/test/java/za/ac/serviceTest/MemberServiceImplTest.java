package za.ac.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.domain.Member;
import za.ac.repository.IMemberRepository;
import za.ac.service.memberService.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private IMemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member.Builder()
                .setMemberId(6L)
                .setUserName("john_doe")
                .build();
    }

    @Test
    void testCreate() {
        when(memberRepository.save(member)).thenReturn(member);

        Member created = memberService.create(member);

        assertNotNull(created);
        assertEquals("MEM001", created.getMemberId());
        assertEquals("john_doe", created.getUserName());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    void testRead_Found() {
        when(memberRepository.findById("MEM001")).thenReturn(Optional.of(member));

        Member found = memberService.read("MEM001");

        assertNotNull(found);
        assertEquals("MEM001", found.getMemberId());
        verify(memberRepository, times(1)).findById("MEM001");
    }

    @Test
    void testRead_NotFound() {
        when(memberRepository.findById("MEM999")).thenReturn(Optional.empty());

        Member found = memberService.read("MEM999");

        assertNull(found);
        verify(memberRepository, times(1)).findById("MEM999");
    }

    @Test
    void testUpdate() {
        when(memberRepository.save(member)).thenReturn(member);

        Member updated = memberService.update(member);

        assertNotNull(updated);
        assertEquals("MEM001", updated.getMemberId());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    void testDelete_WhenExists() {
        when(memberRepository.existsById("MEM001")).thenReturn(true);

        boolean deleted = memberService.delete("MEM001");

        assertTrue(deleted);
        verify(memberRepository, times(1)).existsById("MEM001");
        verify(memberRepository, times(1)).deleteById("MEM001");
    }

    @Test
    void testDelete_WhenNotExists() {
        when(memberRepository.existsById("MEM999")).thenReturn(false);

        boolean deleted = memberService.delete("MEM999");

        assertFalse(deleted);
        verify(memberRepository, times(1)).existsById("MEM999");
        verify(memberRepository, never()).deleteById(anyString());
    }

    @Test
    void testGetAll() {
        when(memberRepository.findAll()).thenReturn(List.of(member));

        List<Member> result = memberService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(member, result.get(0));
        verify(memberRepository, times(1)).findAll();
    }
}