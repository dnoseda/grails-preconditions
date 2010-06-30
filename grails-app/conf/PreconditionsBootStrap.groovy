import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor as Events

class BootStrap {
 def grailsApplication

 def excludedProps = [Events.ONLOAD_EVENT,
    Events.BEFORE_DELETE_EVENT, Events.AFTER_DELETE_EVENT,
    Events.BEFORE_INSERT_EVENT, Events.AFTER_INSERT_EVENT,
    Events.BEFORE_UPDATE_EVENT, Events.AFTER_UPDATE_EVENT]

  def init = { servletContext ->
     grailsApplication.controllerClasses.each{ domainClass ->
         domainClass.metaClass.part= { m ->
             def map= [:]
             if(m.'include'){
                 m.'include'.each{
                     map[it]= delegate."${it}"
                 }
             }else if(m.'except'){
                 m.'except'.addAll excludedProps
                 def props= domainClass.persistentProperties.findAll {
                     !(it.name in m.'except')
                 }
                 props.each{
                     map[it.name]= delegate."${it.name}"
                 }
             }
             return map
         }
     }
  }
  def destroy = {
  }
}