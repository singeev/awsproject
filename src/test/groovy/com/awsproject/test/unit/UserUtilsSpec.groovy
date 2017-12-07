import com.awsproject.backend.persistence.domain.backend.User
import com.awsproject.utils.UserUtils
import com.awsproject.web.controllers.ForgotMyPasswordController
import com.awsproject.web.domain.frontend.BasicAccountPayload
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.*
import uk.co.jemos.podam.api.PodamFactory
import uk.co.jemos.podam.api.PodamFactoryImpl

class UserUtilsSpec extends Specification {

    @Shared
    private MockHttpServletRequest mockHttpServletRequest
    @Shared
    private PodamFactory podamFactory

    def setup() {
        mockHttpServletRequest = new MockHttpServletRequest()
        podamFactory = new PodamFactoryImpl()
    }

    def "Test convert web User object to domain user object"() {
        given:
        def webUser = podamFactory.manufacturePojoWithFullData(BasicAccountPayload)
        webUser.setEmail('me@example.com')

        when:
        def user = UserUtils.fromWebUserToDomainUser(webUser)

        then:
        webUser.getFirstName() == user.getFirstName()
        webUser.getLastName() == user.getLastName()
        webUser.getPassword() == user.getPassword()
        webUser.getUsername() == user.getUsername()
        webUser.getDescription() == user.getDescription()
        webUser.getCountry() == user.getCountry()
        webUser.getPhoneNumber() == user.getPhoneNumber()
        webUser.getEmail() == user.getEmail()
    }

    def "Test construct password reset URL"() {

        given:
        mockHttpServletRequest.setServerPort(8080)
        def token = UUID.randomUUID().toString()
        def userId = 1234

        when:
        def createdUrl = UserUtils.createPasswordResetUrl(mockHttpServletRequest, userId, token)

        then:
        createdUrl == "http://localhost:8080${ForgotMyPasswordController.CHANGE_PASSWORD_URL_PATH}?id=${userId}&token=${token}"
    }
}