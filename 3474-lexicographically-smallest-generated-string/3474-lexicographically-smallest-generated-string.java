class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] res = new char[n + m - 1];

        // Step 1: initialize with '?'
        for (int i = 0; i < res.length; i++) {
            res[i] = '?';
        }

        // Step 2: enforce 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] == '?' || res[i + j] == str2.charAt(j)) {
                        res[i + j] = str2.charAt(j);
                    } else {
                        return ""; // conflict
                    }
                }
            }
        }

        // Step 3: fill remaining with 'a'
        for (int i = 0; i < res.length; i++) {
            if (res[i] == '?') {
                res[i] = 'a';
            }
        }

        // Step 4: handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {

                boolean match = true;

                // check if substring == str2
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                // if equal → we must break it safely
                if (match) {
                    boolean changed = false;

                    // try changing from rightmost (better lexicographically)
                    for (int j = m - 1; j >= 0; j--) {
                        char original = res[i + j];

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == original) continue;

                            res[i + j] = c;

                            // validate all 'T' constraints again
                            boolean valid = true;

                            for (int k = 0; k < n; k++) {
                                if (str1.charAt(k) == 'T') {
                                    for (int x = 0; x < m; x++) {
                                        if (res[k + x] != str2.charAt(x)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                    if (!valid) break;
                                }
                            }

                            if (valid) {
                                changed = true;
                                break;
                            }
                        }

                        if (changed) break;

                        // revert if not valid
                        res[i + j] = original;
                    }

                    if (!changed) return "";
                }
            }
        }

        return new String(res);
    }
}