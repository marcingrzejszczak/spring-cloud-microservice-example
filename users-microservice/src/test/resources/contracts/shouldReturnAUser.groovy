import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return a user"
	request {
		method GET()
		// WAT - it seems that this is not a query param
		url "/users/0?id=0"
	}
	response {
		status 200
		headers {
			contentType("application/hal+json")
		}
		body([
		        firstName: "foo",
		        lastName: "bar",
				phone: "123-123-123",
				email: "e@mail.com",
				birthDate: 123123L
		])
	}
}