package com.ynd.core

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class AdminUserAdminRole implements Serializable {

	private static final long serialVersionUID = 1

	AdminUser adminUser
	AdminRole adminRole

	@Override
	boolean equals(other) {
		if (other instanceof AdminUserAdminRole) {
			other.adminUserId == adminUser?.id && other.adminRoleId == adminRole?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (adminUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, adminUser.id)
		}
		if (adminRole) {
		    hashCode = HashCodeHelper.updateHash(hashCode, adminRole.id)
		}
		hashCode
	}

	static AdminUserAdminRole get(long adminUserId, long adminRoleId) {
		criteriaFor(adminUserId, adminRoleId).get()
	}

	static boolean exists(long adminUserId, long adminRoleId) {
		criteriaFor(adminUserId, adminRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long adminUserId, long adminRoleId) {
		AdminUserAdminRole.where {
			adminUser == AdminUser.load(adminUserId) &&
			adminRole == AdminRole.load(adminRoleId)
		}
	}

	static AdminUserAdminRole create(AdminUser adminUser, AdminRole adminRole, boolean flush = false) {
		def instance = new AdminUserAdminRole(adminUser: adminUser, adminRole: adminRole)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(AdminUser u, AdminRole r) {
		if (u != null && r != null) {
			AdminUserAdminRole.where { adminUser == u && adminRole == r }.deleteAll()
		}
	}

	static int removeAll(AdminUser u) {
		u == null ? 0 : AdminUserAdminRole.where { adminUser == u }.deleteAll() as int
	}

	static int removeAll(AdminRole r) {
		r == null ? 0 : AdminUserAdminRole.where { adminRole == r }.deleteAll() as int
	}

	static constraints = {
		adminRole validator: { AdminRole r, AdminUserAdminRole ur ->
			if (ur.adminUser?.id) {
				AdminUserAdminRole.withNewSession {
					if (AdminUserAdminRole.exists(ur.adminUser.id, r.id)) {
						return ['userRole.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['adminUser', 'adminRole']
		version false
	}
}
