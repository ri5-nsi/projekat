import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_timeTracker_layoutsmain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge,chrome=1")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',10,['default':("Grails")],-1)
})
invokeTag('captureTitle','sitemesh',10,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',10,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(2)
expressionOut.print(resource(dir: 'css', file: 'bootstrap.min.css'))
printHtmlPart(3)
expressionOut.print(resource(dir: 'css', file: 'bootstrap-responsive.min.css'))
printHtmlPart(3)
expressionOut.print(resource(dir: 'css', file: 'jquery-ui-1.10.3.custom.min.css'))
printHtmlPart(3)
expressionOut.print(resource(dir: 'css', file: 'Site.css'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'js', file: 'jquery-1.9.1.min.js'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'js', file: 'jquery.validate1.min.js'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'js', file: 'jquery-ui-1.10.3.custom.min.js'))
printHtmlPart(6)
invokeTag('layoutHead','g',22,[:],-1)
printHtmlPart(1)
invokeTag('layoutResources','r',23,[:],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',24,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
invokeTag('layoutBody','g',54,[:],-1)
printHtmlPart(9)
expressionOut.print(resource(dir: 'js', file: 'bootstrap.min.js'))
printHtmlPart(10)
invokeTag('javascript','g',58,['library':("application")],-1)
printHtmlPart(7)
})
invokeTag('captureBody','sitemesh',59,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1383775311921L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
