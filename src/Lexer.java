

//VERSAO DO DIA 30/03/2016, AINDA APRIMORANDO
import java.util.List;
import java.util.ArrayList;
public class Lexer {
    public static enum Type {
        PARENTESES_ESQUERDA, PARENTESES_DIREITA, CONST, IGUAL, VIRGULA, PONTO_VIRGULA, INICIO, FIM;
    }
    public static class Token {
        public final Type t;
        public final String c;
        public Token(Type t, String c) {
            this.t = t;
            this.c = c;
        }
        public String toString() {
            if(t == Type.CONST) {
                return "CONST<" + c + ">";
            }
            return t.toString();
        }
    }
    public static String getConst(String s, int i) {
        int j = i;
        for( ; j < s.length(); ) {
            if(Character.isLetter(s.charAt(j))) {
                j++;
            } else {
                return s.substring(i, j);
            }
        }
        return s.substring(i, j);
    }
    public static List<Token> lex(String input) {
        List<Token> result = new ArrayList<Token>();
        for(int i = 0; i < input.length(); ) {
            switch(input.charAt(i)) {
            case '(':
                result.add(new Token(Type.PARENTESES_ESQUERDA, "("));
                i++;
                break;
            case ')':
                result.add(new Token(Type.PARENTESES_DIREITA, ")"));
                i++;
                break;
            case '=':
                result.add(new Token(Type.IGUAL, "="));
                i++;
                break;
            case ',':
                result.add(new Token(Type.VIRGULA, ","));
                i++;
                break;
            case ';':
                result.add(new Token(Type.PONTO_VIRGULA, ";"));
                i++;
                break;
            case '&':
                result.add(new Token(Type.INICIO, "&"));
                i++;
                break;
            case '!':
                result.add(new Token(Type.FIM, "!"));
                i++;
                break;
            default:
                if(Character.isWhitespace(input.charAt(i))) {
                    i++;
                } else {
                    String CONST = getConst(input, i);
                    i += CONST.length();
                    result.add(new Token(Type.CONST, CONST));
                }
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Entre com o código, em breve você poderá indicar um arquivo");
            return;
        }
        List<Token> tokens = lex(args[0]);
        for(Token t : tokens) {
            System.out.println(t);
        }
    }
}
