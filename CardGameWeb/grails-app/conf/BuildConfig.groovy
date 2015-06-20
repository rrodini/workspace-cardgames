grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.server.port.http=8090

//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
// Don't really need mysql running
//        runtime 'mysql:mysql-connector-java:5.1.26'
// 2.2.1 get the jars from the lib folder
		runtime 'mylib:cardcore:1.0'
		runtime 'mylib:cardgame:1.0'
// added due to errors during grails run-app
		runtime 'org.springframework:spring-expression:4.0.8.RELEASE'
		runtime 'org.springframework:spring-aop:4.0.8.RELEASE'
    }

    plugins {
        runtime ":hibernate:3.6.10.16"  // updated for Grails 2.4.2
        runtime ":jquery:1.11.0.2"      // commented out due to syntax errors
        //runtime ":resources:1.2.14"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"

        build ":tomcat:7.0.54" // updated for Grails 2.4.2

        //runtime ":database-migration:1.3.2" // commented out for Grails 2.4.2

        //compile ':cache:1.1.8'   // commented out due to compiler errors regarding aop
		compile ':scaffolding:2.1.2'
    }
}
