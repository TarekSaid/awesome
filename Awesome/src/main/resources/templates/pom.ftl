[#ftl]
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
[#if pom.persistence]

    <dependency>
      <groupId>org.primefaces</groupId>
      <artifactId>primefaces</artifactId>
      <version>4.0</version>
    </dependency>

    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-entitymanager</artifactId>
      <version>4.3.0.Final</version>
    </dependency>

    <dependency>
      <groupId>com.googlecode.flyway</groupId>
      <artifactId>flyway-core</artifactId>
      <version>2.3.1</version>
    </dependency>

    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <version>1.3.174</version>
    </dependency>
[/#if]
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
[#if pom.persistence]

      <plugin>
        <groupId>com.googlecode.flyway</groupId>
        <artifactId>flyway-maven-plugin</artifactId>
        <version>2.3.1</version>
        <configuration>
          <url>jdbc:h2:~/db/${pom.artifactId}</url>
          <user>sa</user>
        </configuration>
      </plugin>
[/#if]

      <plugin>
        <groupId>org.codehaus.cargo</groupId>
        <artifactId>cargo-maven2-plugin</artifactId>
        <version>1.4.6</version>
        <configuration>
          <container>
            <containerId>tomcat7x</containerId>
            <zipUrlInstaller>
              <url>http://ftp.unicamp.br/pub/apache/tomcat/tomcat-7/v7.0.50/bin/apache-tomcat-7.0.50.zip</url>
            </zipUrlInstaller>
          </container>
          <configuration>
            <home>${r"${project.build.directory}/catalina-base"}</home>
            <properties>
              <cargo.servlet.port>8080</cargo.servlet.port>
              <cargo.rmi.port>$8005</cargo.rmi.port>
              <cargo.tomcat.ajp.port>8009</cargo.tomcat.ajp.port>
            </properties>
          </configuration>
          <deployables>
            <deployable>
              <groupId>${pom.groupId}</groupId>
              <artifactId>${pom.artifactId?lower_case}</artifactId>
              <type>war</type>
            </deployable>
          </deployables>
          <packager>
            <outputLocation>${r"${project.build.directory}/tomcat7"}</outputLocation>
          </packager>
        </configuration>
        <executions>
          <execution>
            <id>start</id>
            <phase>pre-integration-test</phase>
            <goals>
              <goal>start</goal>
            </goals>
          </execution>
          <execution>
            <id>stop</id>
            <phase>post-integration-test</phase>
            <goals>
              <goal>stop</goal>
            </goals>
          </execution>
          <execution>
            <id>package</id>
            <phase>post-integration-test</phase>
            <goals>
              <goal>package</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>