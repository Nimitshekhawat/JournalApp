package net.nimitswebsite.journalApp.Service;

import net.nimitswebsite.journalApp.Repsository.UserRepository;
import net.nimitswebsite.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;


    @ParameterizedTest
    @CsvSource({
            "nimit",
            "sumit",
            "ruchi",
            "divya"
    })
    public void testFindByUserName(String name) {
//        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUsername(name),"for username "+name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,1,3",
            "3,1,3",
            "1,1,4"
    })
    public void test(int a , int b, int expected) {
        assertEquals(expected,a+b);
    }
}
