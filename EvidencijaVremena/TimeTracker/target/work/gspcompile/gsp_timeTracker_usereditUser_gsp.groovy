import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_timeTracker_usereditUser_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/editUser.gsp" }
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
expressionOut.print(resource(dir: 'js', file: 'User.js'))
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(7)
expressionOut.print(user.UserID)
printHtmlPart(8)
expressionOut.print(user.FirstName)
printHtmlPart(9)
expressionOut.print(user.LastName)
printHtmlPart(10)
invokeTag('select','g',31,['name':("UserStatus"),'from':(["Created", "Unactive", "Banned", "Active"]),'value':(user.UserStatus)],-1)
printHtmlPart(11)
expressionOut.print(user.Email)
printHtmlPart(12)
invokeTag('checkBox','g',45,['name':("AddUser"),'value':(true),'checked':(user.AddUser)],-1)
printHtmlPart(13)
invokeTag('checkBox','g',46,['name':("EditUser"),'value':(true),'checked':(user.EditUser)],-1)
printHtmlPart(14)
invokeTag('checkBox','g',48,['name':("AddProject"),'value':(true),'checked':(user.AddProject)],-1)
printHtmlPart(15)
invokeTag('checkBox','g',49,['name':("EditProject"),'value':(true),'checked':(user.EditProject)],-1)
printHtmlPart(16)
})
invokeTag('form','g',57,['name':("UserForm"),'url':([controller:'User',action:'editUserData'])],2)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(17)
expressionOut.print(user.UserID)
printHtmlPart(18)
})
invokeTag('form','g',78,['name':("UserForm"),'url':([controller:'User',action:'editUserPassword'])],2)
printHtmlPart(6)
})
invokeTag('captureBody','sitemesh',79,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1388743226290L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
