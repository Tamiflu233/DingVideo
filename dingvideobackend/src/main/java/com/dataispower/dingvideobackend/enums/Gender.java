package com.dataispower.dingvideobackend.enums;

public enum Gender {
    FEMALE("female"),
    MALE("male"),
    UNKNOWN("unknown");
    private String gender;
    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static Gender fromString(String str) {
        for (Gender g : Gender.values()) {
            if (g.gender.equalsIgnoreCase(str)) {
                return g;
            }
        }
        throw new IllegalArgumentException(str + " not found or match");
    }
}
