package projecty

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LearningObjectiveController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LearningObjective.list(params), model:[learningObjectiveCount: LearningObjective.count()]
    }

    def show(LearningObjective learningObjective) {
        respond learningObjective
    }

    def create() {
        respond new LearningObjective(params)
    }

    @Transactional
    def save(LearningObjective learningObjective) {
        if (learningObjective == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (learningObjective.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond learningObjective.errors, view:'create'
            return
        }

//        learningObjective.save flush:true

        def query =
                "MERGE (id:UniqueId{name:'LearningObjective'})\n" +
                        "ON CREATE SET id.count = 1\n" +
                        "ON MATCH SET id.count = id.count + 1\n" +
                        "WITH id.count AS uid\n" +
                        "CREATE (p:LearningObjective{__id__:uid,condition:"+"'"+learningObjective.condition+"'"+",customCondition:"+"'"+learningObjective.customCondition+"'"+",indicator:"+"'"+learningObjective.indicator+"'"+",,performance:"+"'"+learningObjective.performance+"'"+",,hideFromLearningObjectiveCondition:"+"'"+learningObjective.hideFromLearningObjectiveCondition+"'"+",criteriaAccuracy:"+"'"+learningObjective.criteriaAccuracy+"'"+",criteriaQuality:"+"'"+learningObjective.criteriaQuality+"'"+",criteriaQuantity:"+"'"+learningObjective.criteriaQuantity+"'"+",criteriaSpeed:"+"'"+learningObjective.criteriaSpeed+"'"+",actionWord:"+"'"+learningObjective.actionWord+"'"+",definition:"+"'"+learningObjective.definition+"'"+"})\n"

        learningObjective.cypher(query)

        redirect(
                action:'index'
        )

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), learningObjective.id])
//                redirect learningObjective
//            }
//            '*' { respond learningObjective, [status: CREATED] }
//        }
    }

    def edit(LearningObjective learningObjective) {
        respond learningObjective
    }

    @Transactional
    def update(LearningObjective learningObjective) {
        if (learningObjective == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (learningObjective.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond learningObjective.errors, view:'edit'
            return
        }

        learningObjective.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), learningObjective.id])
                redirect learningObjective
            }
            '*'{ respond learningObjective, [status: OK] }
        }
    }

    @Transactional
    def delete(LearningObjective learningObjective) {

        if (learningObjective == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        learningObjective.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), learningObjective.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'learningObjective.label', default: 'LearningObjective'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
