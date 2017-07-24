package projecty

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }


}

//import org.springframework.boot.SpringApplication
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration
//import org.springframework.context.annotation.*
//
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//class Application {
//    static void main(String[] args) {
//        SpringApplication.run Application, args
//    }
//}