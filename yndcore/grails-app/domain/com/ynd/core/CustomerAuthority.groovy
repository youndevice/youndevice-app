package com.ynd.core

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class CustomerAuthority implements Serializable {

	private static final long serialVersionUID = 1

	Customer customer
	Authority authority

	CustomerAuthority(Customer u, Authority r) {
		this()
		customer = u
		authority = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof CustomerAuthority)) {
			return false
		}

		other.customer?.id == customer?.id && other.authority?.id == authority?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (customer) builder.append(customer.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static CustomerAuthority get(long customerId, long authorityId) {
		criteriaFor(customerId, authorityId).get()
	}

	static boolean exists(long customerId, long authorityId) {
		criteriaFor(customerId, authorityId).count()
	}

	private static DetachedCriteria criteriaFor(long customerId, long authorityId) {
		CustomerAuthority.where {
			customer == Customer.load(customerId) &&
			authority == Authority.load(authorityId)
		}
	}

	static CustomerAuthority create(Customer customer, Authority authority, boolean flush = false) {
		def instance = new CustomerAuthority(customer: customer, authority: authority)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Customer u, Authority r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = CustomerAuthority.where { customer == u && authority == r }.deleteAll()

		if (flush) { CustomerAuthority.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(Customer u, boolean flush = false) {
		if (u == null) return

		CustomerAuthority.where { customer == u }.deleteAll()

		if (flush) { CustomerAuthority.withSession { it.flush() } }
	}

	static void removeAll(Authority r, boolean flush = false) {
		if (r == null) return

		CustomerAuthority.where { authority == r }.deleteAll()

		if (flush) { CustomerAuthority.withSession { it.flush() } }
	}

	static constraints = {
		authority validator: { Authority r, CustomerAuthority ur ->
			if (ur.customer == null || ur.customer.id == null) return
			boolean existing = false
			CustomerAuthority.withNewSession {
				existing = CustomerAuthority.exists(ur.customer.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['customer', 'authority']
		version false
	}
}
