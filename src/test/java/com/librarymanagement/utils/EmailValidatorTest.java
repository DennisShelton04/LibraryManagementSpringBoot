package com.librarymanagement.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EmailValidatorTest {
    private EmailValidator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new EmailValidator();
    }

    @Test
    @DisplayName("Basic Email Test")
    public void testEmailValidator() {
        // Given
        String email = "@gmail.com";
        // When
        boolean isValid = underTest.test(email);
        // Then
        assertThat(isValid).isTrue();
    }

    @ParameterizedTest
    @CsvSource({ "dennis@gmail.com,true", "rahul@mail.com,false" })
    public void testEmailValidatorWithInvalidEmail(String input, boolean expected) {
        // Given
        // When
        boolean isValid = underTest.test(input);
        // Then
        assertThat(isValid).isEqualTo(expected);
    }
}
