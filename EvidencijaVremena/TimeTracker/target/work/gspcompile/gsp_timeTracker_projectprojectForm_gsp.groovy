import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_timeTracker_projectprojectForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/project/projectForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'js', file: 'jquery.validate.min.js'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'js', file: 'Project.js'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'js', file: 'ui.multiselect.js'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'js', file: 'Project.js'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'css', file: 'ui.multiselect.css'))
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(1)
createClosureForHtmlPart(9, 2)
invokeTag('form','g',50,['name':("UserForm"),'url':([controller:'User',action:'addUser'])],2)
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',54,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1383778935504L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
