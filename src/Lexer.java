//VERSAO DO DIA 30/03/2016, AINDA APRIMORANDO
//Contém: Análise de Tokens, Analise de Palavras reservadas
import java.util.List;
import java.util.ArrayList;
public class Lexer {
    public static enum Type {
        PARENTESES_ESQUERDA, PARENTESES_DIREITA, CONST, IGUAL, VIRGULA, PONTO_VIRGULA, DOIS_PONTOS, INICIO, FIM, SE, ENTAO;
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
                return c;
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
        boolean reservado = false;
        for(int i = 0; i < input.length(); ) {
            switch(input.charAt(i)) {
            case '(':
                result.add(new Token(Type.PARENTESES_ESQUERDA, "("));
                i++;
                break;
            case ')'result.add(new Token(Type.PARENTESES_DIREITA, ")"));
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
            case ':':
                result.add(new Token(Type.DOIS_PONTOS, ":"));
                i++;
                break;
            default:
                if(Character.isWhitespace(input.charAt(i))) {
                  i++;
                } else {
                    String CONST = getConst(input, i);
                    i += CONST.length();
                    if(CONST.equals("BEGIN")){
                    result.add(new Token(Type.INICIO, "BEGIN"));
                    reservado = true;
                    }
                    if(CONST.equals("END")){
                    result.add(new Token(Type.FIM, "END;"));
                    reservado = true;
                    }
                    if(CONST.equals("IF")){
                    result.add(new Token(Type.SE, "IF"));
                    reservado = true;
                    }
                    if(CONST.equals("THEN")){
                    result.add(new Token(Type.ENTAO, "THEN"));
                    reservado = true;
                    }

                    if(reservado==false){
                    CONST = "var<"+CONST+">";
                    result.add(new Token(Type.CONST, CONST));
                    }
                }
                reservado = false;
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Escreva o código logo apos o nome da classe java, em breve você poderá indicar um arquivo");
            return;
        }
        List<Token> tokens = lex(args[0]);
        for(Token t : tokens) {
            System.out.println(t);
        }
    }
}
