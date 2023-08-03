package org.insider.enums;

// Enum representing the different locations associated with the organization.
// Each location is represented by a string value that can be used elsewhere in the code.
public enum Location {
    ISTANBUL("Istanbul, Turkey");

    private final String value;

    // Constructor to initialize the string value for a location.
    Location(String value) {
        this.value = value;
    }

    // Method to get the string value of a location.
    public String getValue() {
        return value;
    }
}
