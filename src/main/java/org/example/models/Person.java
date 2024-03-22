package org.example.models;

import java.util.List;

public record Person(String name, int age, List<Address> addresses) {
}
