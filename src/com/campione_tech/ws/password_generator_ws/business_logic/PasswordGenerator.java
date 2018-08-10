package com.campione_tech.ws.password_generator_ws.business_logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Generates password of desired length. See {@link #usage} method
 * for instructions and command line parameters. This algorithm shows usages of:
 * <ul>
 * <li>Method references.</li>
 * <li>Lambda and bulk operations. A stream of random integers is mapped to
 * chars, limited by desired length and printed in standard output as password
 * string.</li>
 * </ul>
 * 
 * @author D. Campione
 * 
 */
/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation, and proper error handling, might not be present in
 * this sample code.
 */
public class PasswordGenerator {

    private final List<Integer> PASSWORD_CHARS = new ArrayList<>();

    private String password;

    private int characterToSet;

    // Valid symbols
    public PasswordGenerator() {
        IntStream.rangeClosed('0', '9').forEach(PASSWORD_CHARS::add); // 0..9
        IntStream.rangeClosed('A', 'Z').forEach(PASSWORD_CHARS::add); // A..Z
        IntStream.rangeClosed('a', 'z').forEach(PASSWORD_CHARS::add); // a..z
    }

    /**
     * The main method for the PasswordGenerator program.
     *
     * @param args the argument list for PasswordGenerator.
     */
    public void initPassword(int passwordLength) {
        if (passwordLength < 1) {
            return;
        }

        /*
         * Stream of random integers is created containing Integer values
         * in range from 0 to PASSWORD_CHARS.size().
         * The stream is limited by passwordLength.
         * Valid chars are selected by generated index.
         */
        char[] passwordCharacters = new char[passwordLength];
        characterToSet = 0;
        new SecureRandom().ints(passwordLength, 0, PASSWORD_CHARS.size()).map(PASSWORD_CHARS::get).forEach(i -> {
            passwordCharacters[characterToSet++] = (char) i;
        });
        password = new String(passwordCharacters);
    }

    public String getPassword() {
        return password;
    }
}