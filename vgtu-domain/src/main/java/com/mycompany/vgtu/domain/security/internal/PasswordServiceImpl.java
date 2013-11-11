package com.mycompany.vgtu.domain.security.internal;

import com.mycompany.vgtu.domain.security.PasswordService;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

public class PasswordServiceImpl implements PasswordService {

    @Override
    public boolean passwordsMatch(String dbStoredHashedPassword, String dbStoredSalt, String clearTextPassword) {
        ByteSource salt = ByteSource.Util.bytes(Hex.decode(dbStoredSalt));
        String hashedPassword = hashAndSaltPassword(clearTextPassword, salt);
        return hashedPassword.equals(dbStoredHashedPassword);
    }

    @Override
    public String hashAndSaltPassword(String clearTextPassword, ByteSource salt) {
        return new Sha512Hash(clearTextPassword, salt, hashIterations).toHex();
    }

    @Override
    public ByteSource generateSalt() {
        return new SecureRandomNumberGenerator().nextBytes();
    }

    @Override
    public String saltoToString(ByteSource salt) {
        return salt.toHex();
    }

    @Override
    public ByteSource saltToByte(String dbStoredSalt) {
        ByteSource salt = ByteSource.Util.bytes(Hex.decode(dbStoredSalt));
        return salt;
    }
}
