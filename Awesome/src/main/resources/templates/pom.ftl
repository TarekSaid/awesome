<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>${pom.groupId}</groupId>
  <artifactId>${pom.artifactId?lower_case}</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <name>${pom.artifactId}</name>
  <description>${pom.description}</description>

  <dependencies>
    <dependency>
      <groupId>com.sun.faces</groupId>
      <artifactId>jsf-api</artifactId>
      <version>2.2.4</version>
    </dependency>

    <dependency>
      <groupId>com.sun.faces</groupId>
      <artifactId>jsf-impl</artifactId>
      <version>2.2.4</version>
    </dependency>
<#if pom.dependencies??>
<#list pom.dependencies as dependency>

    <dependency>
      <groupId>${dependency.groupId}</groupId>
      <artifactId>${dependency.artifactId}</artifactId>
      <version>${dependency.version}</version>
<#if dependency.scope?? && dependency.scope.description != "compile">
      <scope>${dependency.scope.description!}</scope>
</#if>
    </dependency>
</#list>
</#if>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>${pom.javaVersion}</source>
          <target>${pom.javaVersion}</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>