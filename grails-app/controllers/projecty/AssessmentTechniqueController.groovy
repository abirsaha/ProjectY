package projecty

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AssessmentTechniqueController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AssessmentTechnique.list(params), model:[assessmentTechniqueCount: AssessmentTechnique.count()]
    }

    def show(AssessmentTechnique assessmentTechnique) {
        respond assessmentTechnique
    }

    def create() {
        respond new AssessmentTechnique(params)
    }

    @Transactional
    def save(AssessmentTechnique assessmentTechnique) {
        if (assessmentTechnique == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (assessmentTechnique.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond assessmentTechnique.errors, view:'create'
            return
        }

        assessmentTechnique.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assessmentTechnique.label', default: 'AssessmentTechnique'), assessmentTechnique.id])
                redirect assessmentTechnique
            }
            '*' { respond assessmentTechnique, [status: CREATED] }
        }
    }

    def edit(AssessmentTechnique assessmentTechnique) {
        respond assessmentTechnique
    }

    @Transactional
    def update(AssessmentTechnique assessmentTechnique) {
        if (assessmentTechnique == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (assessmentTechnique.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond assessmentTechnique.errors, view:'edit'
            return
        }

        assessmentTechnique.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assessmentTechnique.label', default: 'AssessmentTechnique'), assessmentTechnique.id])
                redirect assessmentTechnique
            }
            '*'{ respond assessmentTechnique, [status: OK] }
        }
    }

    @Transactional
    def delete(AssessmentTechnique assessmentTechnique) {

        if (assessmentTechnique == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        assessmentTechnique.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assessmentTechnique.label', default: 'AssessmentTechnique'), assessmentTechnique.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assessmentTechnique.label', default: 'AssessmentTechnique'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
