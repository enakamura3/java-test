import org.example.models.Address;
import org.example.models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StreamTestTest {

    private static List<Person> persons = null;

    @BeforeAll
    public static void createPersons() {
        var a1 = new Address("Rua A", "Sao Paulo", "SP");
        var a2 = new Address("Rua B", "Sao Paulo", "SP");
        var a3 = new Address("Rua C", "Rio de Janeiro", "RJ");
        var a4 = new Address("Rua D", "Rio de Janeiro", "RJ");
        var a5 = new Address("Rua E", "Curitiba", "PR");

        var p1 = new Person("John", 30, List.of(a1));
        var p2 = new Person("Bob", 35, List.of(a2, a5));
        var p3 = new Person("Ada", 19, List.of(a3));
        var p4 = new Person("Michael", 40, List.of(a4));
        var p5 = new Person("Jim", 36, List.of(a5));
        var p6 = new Person("Pam", 33, List.of(a1, a3));
        var p7 = new Person("Dwight", 38, List.of(a2, a5));

        persons = List.of(p1, p2, p3, p4, p5, p6, p7);
    }

    @Test
    public void shouldFilterPersonsInSP() {
        var filter = persons.stream()
                .filter(p -> p.addresses().stream()
                        .anyMatch(a -> a.state().equals("SP")))
                .collect(Collectors.toList());
        assertEquals(4, filter.size());
    }

    @Test
    public void shouldFilterPersonsOnlyInSP() {
        var filter = persons.stream()
                .filter(p -> p.addresses().size() == 1)
                .filter(p -> p.addresses().stream()
                        .anyMatch(a -> a.state().equals("SP")))
                .collect(Collectors.toList());

        assertEquals(1, filter.size());
    }

    @Test
    public void shouldFilterPersonsWithAgeGreaterThan30() {
        var filter = persons.stream()
                .filter(p -> p.age() > 30)
                .collect(Collectors.toList());

        assertEquals(5, filter.size());
    }

    @Test
    public void shouldFindTheOldestPerson() {
        var filter = persons.stream()
                .sorted(Comparator.comparing(Person::age).reversed())
                .findFirst();

        assertEquals(40, filter.get().age());
    }

    @Test
    public void shouldFindAllAddressFromACity() {
        var city = "Rio de Janeiro";
        var filter = persons.stream()
                .flatMap(p -> p.addresses().stream())
                .filter(a -> a.city().equalsIgnoreCase(city))
                .distinct()
                .collect(Collectors.toList());
        assertEquals(2, filter.size());
    }

    @Test
    public void shouldConcatAllPersonsNamesIntoAString() {
        var size = 29;
        var str = persons.stream()
                .map(p -> p.name())
                .collect(Collectors.joining());

        assertEquals(size, str.length());
    }

}