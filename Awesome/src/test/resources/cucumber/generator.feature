Feature: Default MVC Generator
    As a developer
    In order to create an app
    I want to generate the code that was parsed by the Parser

  Scenario Outline: Simple HelloWorld Generator
    Given that I have parsed <app-mapping>
    When I generate the app
    Then I should see the following files: <files> from the source folder <folder>

    Examples: 
      | app-mapping         | files                                                                                                                                                 | folder        |
      | "simple-hello.json" | "pom.xml, src/main/java/controllers/HelloBean.java, src/main/webapp/WEB-INF/web.xml, src/main/webapp/hello.xhtml"                                     | "simplehello" |
      | "hello-world.json"  | "pom.xml, src/main/java/controllers/HelloBean.java, src/main/webapp/WEB-INF/web.xml, src/main/webapp/hello.xhtml, src/main/webapp/hello-result.xhtml" | "HelloWorld"  |
