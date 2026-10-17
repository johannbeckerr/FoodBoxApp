package org.cct;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

// Browser-only entry point used by the CheerpJ demo (docs/index.html).
// It is NOT part of the coursework code in src/ and is only compiled by web/build.sh.
//
// In the browser there is no keyboard stdin, so this class replaces System.in
// with a stream that asks the web page for one line at a time (native readLine,
// implemented in JavaScript). Then it starts the real app: Main.main().
public class BrowserMain {

    // Implemented in JavaScript (docs/index.html). Waits until the user presses Enter.
    static native String readLine();

    public static void main(String[] args) {
        System.setIn(new InputStream() {
            private byte[] buffer = new byte[0];
            private int position = 0;

            // Asks the page for a new line only when everything typed before was consumed.
            private boolean fill() {
                if (position < buffer.length) {
                    return true;
                }
                String line = readLine();
                if (line == null) {
                    return false;
                }
                // Echo what the user typed, like a real terminal.
                System.out.println("> " + line);
                buffer = (line + "\n").getBytes(StandardCharsets.UTF_8);
                position = 0;
                return true;
            }

            @Override
            public int read() throws IOException {
                if (!fill()) {
                    return -1;
                }
                return buffer[position++] & 0xff;
            }

            // Returns only what is already buffered, so Scanner never waits for an extra line.
            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                if (len == 0) {
                    return 0;
                }
                if (!fill()) {
                    return -1;
                }
                int n = Math.min(len, buffer.length - position);
                System.arraycopy(buffer, position, b, off, n);
                position += n;
                return n;
            }

            @Override
            public int available() {
                return buffer.length - position;
            }
        });

        Main.main(args);
    }
}
