

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.ynd.core.Customer'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.ynd.core.CustomerAuthority'
grails.plugin.springsecurity.authority.className = 'com.ynd.core.Authority'
grails.plugin.springsecurity.auth.loginFormUrl = '/login'
grails.plugin.springsecurity.logout.postOnly = false
//grails.plugin.springsecurity.auth.loginFormUrl = '/login/auth'
//grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/admin/dashboard'
//grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/home'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern: '/', access: ['ROLE_USER']],
		[pattern: '/comingsoon', access: ['permitAll']],
		[pattern: '/verifyUser', access: ['permitAll']],
		[pattern: '/userDevice/**', access: ['ROLE_USER']],
		[pattern: '/util/**', access: ['ROLE_USER']],
		[pattern: '/userAppliance/**', access: ['ROLE_USER']],
		[pattern: '/error', access: ['permitAll']],
		[pattern: '/index', access: ['ROLE_USER']],
		[pattern: '/signin', access: ['permitAll']],
		[pattern: '/signup', access: ['permitAll']],
		[pattern: '/user/registerUser', access: ['permitAll']],
		[pattern: '/user/confirmUser', access: ['permitAll']],
		[pattern: '/user/**', access: ['ROLE_USER']],
		[pattern: '/shutdown', access: ['permitAll']],
		[pattern: '/assets/**', access: ['permitAll']],
		[pattern: '/**/js/**', access: ['permitAll']],
		[pattern: '/**/css/**', access: ['permitAll']],
		[pattern: '/**/images/**', access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/**/console/**', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
		[pattern: '/assets/**', filters: 'none'],
		[pattern: '/**/js/**', filters: 'none'],
		[pattern: '/**/css/**', filters: 'none'],
		[pattern: '/**/images/**', filters: 'none'],
		[pattern: '/**/favicon.ico', filters: 'none'],
		[pattern: '/**/console*/**', filters: 'none'],
		[pattern: '/**', filters: 'JOINED_FILTERS']
]

grails {
	serverURL = System.getenv('SERVER_URL') ?:  "http://localhost:9090"
	mail {
		host = System.getenv('MAIL_HOST') ?: "smtp.gmail.com"
		port = System.getenv('MAIL_PORT') ?: 465
		username = System.getenv('YND_MAIL_USER_NAME') ?: "youndevice.2017@gmail.com"
		password = System.getenv('YND_MAIL_PASSWORD') ?: ""

		if (System.getenv('CLIENT_EMAIL_SMTP')) {
			props = ["mail.smtp.starttls.enable": System.getenv('MAIL_SMTP_STARTTLS_ENABLE') ?: "true",
					 "mail.smtp.port"           : System.getenv('MAIL_PORT') ?: 587]
		} else {
			props = ["mail.smtp.auth"                  : System.getenv('MAIL_SMTP_AUTH') ?: "true",
					 "mail.smtp.socketFactory.port"    : System.getenv('MAIL_SMTP_SOCKET_FACTORY_PORT') ?: "465",
					 "mail.smtp.socketFactory.class"   : System.getenv('MAIL_SMTP_SOCKET_FACTORY_CLASS') ?: "javax.net.ssl.SSLSocketFactory",
					 "mail.smtp.socketFactory.fallback": System.getenv('MAIL_SMTP_SOCKET_FACTORY_FALLBACK') ?: "false",
					 "mail.smtp.starttls.enable"       : System.getenv('MAIL_SMTP_STARTTLS_ENABLE') ?: "true"
			]
		}

	}
}
