package rolling.pandas.server.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue
        val id: Long
) : UserDetails {
    lateinit var login: String
    lateinit var pass: String
    lateinit var firstName: String
    lateinit var lastName: String
    @ElementCollection(fetch = FetchType.EAGER)
    lateinit var roles: Set<String>

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles.map { GrantedAuthority { it } }.toMutableList()

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = login

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = pass

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun toString(): String {
        return "User(id=$id, login='$login', password='$password', firstName='$firstName', lastName='$lastName', roles=$roles)"
    }


}