package refactoring;


import junit.framework.Assert;

public class ComparisonCompactor6 {

    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    private int contextLength;
    private String expected;
    private String actual;
    private int prefix;
    private int suffix;

    private String compactExpected;
    private String compactActual;

    public ComparisonCompactor6(int contextLength, String expected, String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }


    /**
     * Step6. 함수 분리
     * if 내에서 예상 문자열과 실제 문자열을 압축하는 작업을 {@link #compactExpectedAndActual()} 함수로 빼냄
     * 그러나 형식을 맞추는 작업은 {@link #formatCompactedComparison(String)} 함수에 전적으로 맡김
     */
    public String formatCompactedComparison(String message) {
        if (canBeCompacted()) {
            /*
            findCommonPrefix();
            findCommonSuffix();
            String compactExpected = compactString(expected);
            String compactActual = compactString(actual);
             */
            compactExpectedAndActual();
            return Assert.format(message, compactExpected, compactActual);
        } else {
            return Assert.format(message, expected, actual);
        }
    }

    private void compactExpectedAndActual() {
        findCommonPrefix();
        findCommonSuffix();
        compactExpected = compactString(expected);
        compactActual = compactString(actual);
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
