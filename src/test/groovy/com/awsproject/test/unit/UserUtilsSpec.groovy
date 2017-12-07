class UserUtilsSpec extends Specification {

    def "Test register data initial validation with bad params"() {
        given:
        def authController = new AuthController()

        when:
        authController.login(Mock(HttpServletRequest), registerRequest)

        then:
        thrown(CustomLogicException)

        where:
        registerRequest << [
                new PlainLoginRequest(
                        email: 'test@gmail.com', password: 'password'
                )
        ]
    }
}