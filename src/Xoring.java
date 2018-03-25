
public class Xoring {
    public String Xoring(String CtD, String C) {

        String AfterXor;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < C.length(); i++)
            sb.append((CtD.charAt(i) ^ C.charAt(i)));
        AfterXor = sb.toString();

        String AfterXorAscii = toAscii(AfterXor);
        return AfterXorAscii;
    }

    public String toAscii(String afterXor) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < afterXor.length(); i+=8)
            sb.append((char) Integer.parseInt(afterXor.substring(i, i + 8), 2));
        String s;
        s = sb.toString();
        return s;
    }
}
