# java-test

Simple project using java

- Java 21
- Gradle 8.6

Using asdf to manage versions
```
asdf current
gradle          8.6
java            openjdk-21.0.2 
```

## Creating jar

In arquivo build.gradle.kts file, create new task named jar

```kotlin
tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
}
```

Using gradle on CLI, execute the task

```shell
gradle jar
```

I executed in WSL

```sh
onigiri@OnigiriB2360:/mnt/d/code/java/java-test$ gradle jar

BUILD SUCCESSFUL in 1s
2 actionable tasks: 2 executed
```

```sh
onigiri@OnigiriB2360:/mnt/d/code/java/java-test$ java -jar build/libs/java-test-1.0-SNAPSHOT.jar
Hello and welcome!
```