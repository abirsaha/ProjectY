package projecty

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ImodController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Imod.list(params), model:[imodCount: Imod.count()]
    }

    def show(Imod imod) {
        respond imod
    }

    def create() {
        respond new Imod(params)
    }

    @Transactional
    def save(Imod imod) {
        if (imod == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (imod.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond imod.errors, view:'create'
            return
        }

        //imod.save flush:true


                def query =
                "MERGE (id:UniqueId{name:'Imod'})\n" +
                        "ON CREATE SET id.count = 1\n" +
                        "ON MATCH SET id.count = id.count + 1\n" +
                        "WITH id.count AS uid\n" +
                        "CREATE (p:Imod{__id__:uid,name:"+"'"+imod.name+"'"+",subjectArea:"+"'"+imod.subjectArea+"'"+",courseLocation:"+"'"+imod.courseLocation+"'"+",courseSemester:"+"'"+imod.courseSemester+"'"+",creditHours:"+"'"+imod.creditHours+"'"+",imodNumber:"+"'"+imod.imodNumber+"'"+",name:"+"'"+imod.name+"'"+",numberOfSeats:"+"'"+imod.numberOfSeats+"'"+",overview:"+"'"+imod.overview+"'"+",owner:"+"'"+imod.getOwner()+"'"+",saved:"+"'"+imod.saved+"'"+",subjectArea:"+"'"+imod.subjectArea+"'"+",url:"+"'"+imod.url+"'"+"})\n"+
                        "WITH p.__id__ as rid\n"+
                        "MATCH (imod:Imod {__id__:rid})\n" +
                        "MATCH (user:ImodUser {__id__: "+imod.getOwnerId()+"})\n" +
                        "MERGE (user)-[:OWNS]->(imod)\n"+
                        "MERGE (imod)-[:OWNER]->(user);"
        imod.cypher(query)



        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'imod.label', default: 'Imod'), imod.id])
                redirect action:'index',id:'imod.id'
            }
            '*' { respond imod, [status: CREATED] }
        }
    }

    def edit(Imod imod) {
        respond imod
    }

    @Transactional
    def update(Imod imod) {
        if (imod == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (imod.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond imod.errors, view:'edit'
            return
        }

        imod.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'imod.label', default: 'Imod'), imod.id])
                redirect imod
            }
            '*'{ respond imod, [status: OK] }
        }
    }

    @Transactional
    def delete(Imod imod) {

        if (imod == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        imod.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'imod.label', default: 'Imod'), imod.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'imod.label', default: 'Imod'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
