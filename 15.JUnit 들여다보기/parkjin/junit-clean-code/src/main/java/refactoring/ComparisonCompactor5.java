package refactoring;


import junit.framework.Assert;

public class ComparisonCompactor5 {

    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    private int contextLength;
    private String expected;
    private String actual;
    private int prefix;
    private int suffix;

    public ComparisonCompactor5(int contextLength, String expected, String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }


    /**
     * Step5. 함수 이름 변경
     * 실제로는 {@link #canBeCompacted()} 따라 압축을 하기 때문에 함수 이름에 compact라는 이름이 붙으면 오류 점검이라는 부가 단계가 숨겨짐
     * 게다가 단순히 압축된 문자열이 아닌 형식이 갖춰진 문자열을 반환함
     */
    /*
    public String compact(String message) {
    */
    public String formatCompactedComparison(String message) {
        if (canBeCompacted()) {
            findCommonPrefix();
            findCommonSuffix();
            String compactExpected = compactString(expected);
            String compactActual = compactString(actual);
            return Assert.format(message, compactExpected, compactActual);
        } else {
            return Assert.format(message, expected, actual);
        }
    }

    private boolean canBeCompacted() {
        return expected != null && actual != null && !areStringsEqual();
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
