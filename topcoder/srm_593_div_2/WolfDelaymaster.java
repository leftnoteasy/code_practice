public class WolfDelaymaster {
    public String check(String in) {
        int pos = 0;
        while (pos < in.length()) {
            // check w
            if (in.charAt(pos) == 'w') {
                // get n
                int n = 1;
                pos++;
                while (pos < in.length()) {
                    if (in.charAt(pos) == 'w') {
                        n++;
                    }
                    pos++;
                }

                if (pos >= in.length()) {
                    return "INVALID";
                }

                // check o, l, f
                for (int i = 0; i < n; i++) {
                    if (in.charAt(pos) == 'o') {
                        pos++;
                    } else {
                        return "INVALID";
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (in.charAt(pos) == 'l') {
                        pos++;
                    } else {
                        return "INVALID";
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (in.charAt(pos) == 'f') {
                        pos++;
                    } else {
                        return "INVALID";
                    }
                }

                if (pos >= in.length()) {
                    return "INVALID";
                }

                pos++;
             } else {
                return "INVALID";
            }
        }
        return "VALID";
    }
}