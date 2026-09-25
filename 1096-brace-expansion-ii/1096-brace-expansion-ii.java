class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> set = parseUnion(expression);

        List<String> result = new ArrayList<>(set);

        Collections.sort(result);

        return result;
    }

    // expression1 , expression2 , expression3
    private Set<String> parseUnion(String s) {

        Set<String> result = new HashSet<>();

        result.addAll(parseConcat(s));

        while (index < s.length() && s.charAt(index) == ',') {

            index++; // skip ','

            result.addAll(parseConcat(s));
        }

        return result;
    }

    // expression1expression2expression3
    private Set<String> parseConcat(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> part;

            if (s.charAt(index) == '{') {

                index++; // skip '{'

                part = parseUnion(s);

                index++; // skip '}'

            } else {

                part = new HashSet<>();

                part.add(String.valueOf(s.charAt(index)));

                index++;
            }

            // Cartesian product
            Set<String> temp = new HashSet<>();

            for (String a : result) {
                for (String b : part) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }
}