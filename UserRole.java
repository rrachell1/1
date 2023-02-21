/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.main;

/**
 *
 * @author imnvi
 */
public enum UserRole {
    ADMIN(0),
    USER(2),
    INVALID(-1);

    private final int role;

    public static UserRole valueOf(int role) {
        if (role < 0 || role >= UserRole.values().length) {
            return UserRole.INVALID;
        }
        return UserRole.values()[role];
    }

    private UserRole(int role) {
        this.role = role;
    }

    public int intValue() {
        return this.role;
    }
}
