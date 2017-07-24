import projecty.ImodUser
import projecty.Imod
import projecty.LearningDomain
import projecty.LearningObjective
import projecty.Content
import projecty.LearningObjectiveCriteriaType
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
//       if (grails.util.Environment.current.name == "development"){
//        if (ImodUser.list().size()==0) {
//            def learningDomainCognitive = new LearningDomain(
//                    name: 'Cognitive'
//            )
//            if (learningDomainCognitive.validate()) {
//                learningDomainCognitive.save()
//            } else {
//                print "not validated"
//            }
//
//            def learningDomainAffective = new LearningDomain(
//                    name: 'Affective'
//            )
//            if (learningDomainAffective.validate()) {
//                learningDomainAffective.save()
//            } else {
//                print "not validated"
//            }
//
//            def learningDomainPsychomotor = new LearningDomain(
//                    name: 'Psychomotor'
//            )
//            if (learningDomainPsychomotor.validate()) {
//                learningDomainPsychomotor.save()
//            } else {
//                print "not validated"
//            }
//        }
//       }
//
//        def developer = new ImodUser(
//                username: 'postgres',
//                password: 'postgres',
//                firstName: 'Jane',
//                lastName: 'Doe ',
//                email: 'imod.grails@gmail.com'
//        ).save()

        /**
         * Generate Learning Domains, Domain Categories and Action Words
         */
//        def learningDomainCognitive = new LearningDomain(
//                name: 'Cognitive'
//        )
//        if (learningDomainCognitive.validate()){learningDomainCognitive.save( flush:true)}
//        else{
//            print "not validated"
//        }

//        def learningDomainAffective = new LearningDomain(
//                name: 'Affective'
//        ).save (flush:true)
//
//        def learningDomainPsychomotor = new LearningDomain(
//                name: 'Psychomotor'
//        ).save (flush:true)
    }
    def destroy = {
    }
}
