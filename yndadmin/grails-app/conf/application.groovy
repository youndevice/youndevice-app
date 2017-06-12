

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'youndevice.admin.AdminUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'youndevice.admin.AdminUserAdminRole'
grails.plugin.springsecurity.authority.className = 'youndevice.admin.AdminRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['ROLE_ADMIN']],
	[pattern: '/device/**',          access: ['ROLE_ADMIN']],
	[pattern: '/appliance/**',          access: ['ROLE_ADMIN']],
	[pattern: '/index.gsp',      access: ['ROLE_ADMIN']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/**/console/**', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**/console*/**', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

