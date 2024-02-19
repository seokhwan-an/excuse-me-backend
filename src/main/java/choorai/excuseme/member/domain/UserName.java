package choorai.excuseme.member.domain;

import choorai.excuseme.member.exception.MemberErrorCode;
import choorai.excuseme.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class UserName {

    private static final Pattern USERNAME_FORMAT = Pattern.compile(
        "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");

    @Column(name = "username", nullable = false)
    private String value;

    public UserName(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        final Matcher matcher = USERNAME_FORMAT.matcher(value);
        if (!matcher.matches()) {
            throw new MemberException(MemberErrorCode.WRONG_USERNAME);
        }
    }
}
