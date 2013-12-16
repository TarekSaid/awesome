Feature: Default MVC Generator
    As a developer
    In order to create an app
    I want to generate the code that was parsed by the Parser

  Scenario Outline: Simple HelloWorld Generator
    Given that I have parsed <app-mapping>
    When I generate the app
    Then I should see the following files: <files> from the source folder <folder>

    Examples: 
      | app-mapping        | files           | folder           |
      | "hello-world.json" | "Hello/pom.xml" | ".java/AWEsome/" |
