package refactoring;


import junit.framework.Assert;

public class ComparisonCompactor {

    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    /**
     * Step1. 접두어 f 제거
     * 오늘날 사용하는 개발 환경에서는 이처럼 변수 이름에 범위를 표시할 필요가 없음
     * 중복되는 정보이므로 제거
     */
    /*-
    private int fContextLength;
    private String fExpected;
    private String fActual;
    private int fPrefix;
    private int fSuffix;
     */
    private int contextLength;
    private String expected;
    private String actual;
    private int prefix;
    private int suffix;

    public ComparisonCompactor(int contextLength, String expected, String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }


    /**
     * Step2. 조건문 캡슐화
     * 의도를 명확히 하기 위해 {@link #compact(String)} 함수 시작부의 캡슐화 되지 않은 조건문 캡슐화
     * {@link #shouldNotCompact()}
     *
     *
     * Step3. 이름은 명확하게
     * {@link #compact(String)} 함수 내의 this.expected, this.actual 명확하게 변경
     * 접두어를 제거하는 바람에 생긴 결과로 지역 변수와 멤버 변수의 이름이 동일함
     */
    public String compact(String message) {
        /*-
        if (expected == null || actual == null || areStringsEqual()) {
            return Assert.format(message, expected, actual);
        }
         */
        if (shouldNotCompact())
            return Assert.format(message, expected, actual);


        findCommonPrefix();
        findCommonSuffix();

        /*-
        String expected = compactString(this.expected);
        String actual = compactString(this.actual);
         */
        String compactExpected = compactString(expected);
        String compactActual = compactString(actual);
        return Assert.format(message, compactExpected, compactActual);
    }

    private boolean shouldNotCompact() {
        return expected == null || actual == null || areStringsEqual();
    }

    private String compactString(String source) {
        String result = DELTA_START + source.substring(prefix, source.length() - suffix + 1) + DELTA_END;
        if (prefix > 0) {
            result = computeCommonPrefix() + result;
        }
        if (suffix > 0) {
            result = result + computeCommonSuffix();
        }
        return result;
    }

    private void findCommonPrefix() {
        prefix = 0;
        int end = Math.min(expected.length(), actual.length());
        for (; prefix < end; prefix++) {
            if (expected.charAt(prefix) != actual.charAt(prefix)) {
                break;
            }
        }
    }

    private void findCommonSuffix() {
        int expectedSuffix = expected.length() - 1;
        int actualSuffix = actual.length() - 1;
        for (; actualSuffix >= prefix && expectedSuffix >= prefix; actualSuffix--, expectedSuffix--) {
            if (expected.charAt(expectedSuffix) != actual.charAt(actualSuffix)) {
                break;
            }
        }
        suffix = expected.length() - expectedSuffix;
    }

    private String computeCommonPrefix() {
        return (prefix > contextLength ? ELLIPSIS : "") + expected.substring(Math.max(0, prefix - contextLength), prefix);
    }

    private String computeCommonSuffix() {
        int end = Math.min(expected.length() - suffix + 1 + contextLength, expected.length());
        return expected.substring(expected.length() - suffix + 1, end) + (expected.length() - suffix + 1 < expected.length() - contextLength ? ELLIPSIS : "");
    }

    private boolean areStringsEqual() {
        return expected.equals(actual);
    }
}
