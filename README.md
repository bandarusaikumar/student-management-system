# Student management system

<ol>
    <li><a href="#design">Design choices</a></li>
    <li><a href="#requirements">Requirements</a></li>
    <li><a href="#instructions">Running instructions</a></li>
</ol>

**<a name="design"><h2>Design choices</h2></a>**
<ul>
    <li>An <b>MVC architecture</b> is used for the project, exposing a <b>RESTful API</b></li>
    <li>
        <b>Spring Boot</b> was the framework chosen for this project, this is because it greatly simplifies the process of configuring Spring itself, the following Maven starter dependencies where included:
        <ul>
            <li><b>spring-boot-starter-web:</b> This allows to use Spring MVC for the REST API</li>
            <li><b>spring-boot-starter-data-jpa:</b> This allows to use Spring Data automatic repositories with Hibernate as ORM</li>
            <li><b>spring-boot-starter-cache:</b> This allows to enable caching support</li>
            <li><b>spring-boot-starter-test:</b> This provides support for integration tests with MockMvc</li>
        </ul>
    </li>
    <li><b>Maven</b> is used as the build and dependencies management tool for this project</li>
    <li>For storing information about students, an <b>H2 in-memory database</b> is used</li>
    <li>For better performance, results for search request are cached, using a simple cache provider based on <b>ConcurrentHashMap</b>
    <li>Searches are case insensitive</li>
</ul>

**<a name="requirements"><h2>Requirements</h2></a>**
<ul>
    <li>JDK 8, you can get it from <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html" target="_blank">here</a></li>
    <li>Maven, you can find installation instructions <a href="https://maven.apache.org/install.html" target="_blank">here</a></li>
</ul>

**<a name="instructions"><h2>Running instructions</h2></a>**
<ul>
    <li>For running the application from the source code, just issue the command <code>mvn spring-boot:run</code> in the project's root directory, where the pom.xml file resides</li>
    <li>For running the application from the jar, just issue the command <code>java -jar student-management-system-1.0.jar</code> in the project's dist directory, this will start the application with an embedded Tomcat server</li>
    <li>For running tests for the application, just issue the command <code>mvn test</code> in the project's root directory</li>
    <li>Once the application is started, it listens on port 8080 expecting for requests</li>
    <li>The API documentation can be found at the doc directory</li>
</ul>