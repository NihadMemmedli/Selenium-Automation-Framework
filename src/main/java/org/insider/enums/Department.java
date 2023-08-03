package org.insider.enums;

// Enum representing the different departments within the organization.
// Each department is represented by a string value that can be used elsewhere in the code.
public enum Department {
    QUALITY_ASSURANCE("Quality Assurance");

    private final String value;

    // Constructor to initialize the string value for a department.
    Department(String value) {
        this.value = value;
    }

    // Method to get the string value of a department.
    public String getValue() {
        return value;
    }
}
