package com.mycompany.vgtu.domain.security;

import org.apache.shiro.util.ByteSource;

public interface PasswordService {
    static final int hashIterations = 50000;

    String hashAndSaltPassword(String clearTextPassword, ByteSource salt);

    boolean passwordsMatch(String dbStoredHashedPassword, String dbStoredSalt, String clearTextPassword);

    String saltoToString(ByteSource salt);

    ByteSource generateSalt();

    ByteSource saltToByte(String dbStoredSalt);
}
