package com.example.practicewithspring.eroles;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {
    USER(0),GUIDE(1),ADMIN(2);

    private final int value;
}
