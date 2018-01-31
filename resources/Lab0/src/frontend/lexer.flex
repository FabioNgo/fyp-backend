/* You do not need to change anything up here. */
package frontend;

import lexer.Token;
import static lexer.Token.Type.*;
%%

%public
%final
%class Lexer
%function nextToken
%type Token
%unicode
%line
%column

%{
    /* These two methods are for the convenience of rules to create toke objects.
    * If you do not want to use them, delete them
    * otherwise add the code in
    */

    private Token token(Token.Type type) {
        return new Token(type, yyline, yycolumn, yytext());
    }

    /* Use this method for rules where you need to process yytext() to get the lexeme of the token.
     *
     * Useful for string literals; e.g., the quotes around the literal are part of yytext(),
     *       but they should not be part of the lexeme.
    */
    private Token token(Token.Type type, String text) {
        if (type.equals(Token.Type.STRING_LITERAL)) {
            text = text.substring(1, text.length()-1);
        }
        return new Token(type,yyline,yycolumn,text);
    }
%}

/* This definition may come in handy. If you wish, you can add more definitions here. */
#DEFINITIONS HOLDER#


%%
/* put in your rules here.    */

// keywords
#KEYWORDS HOLDER#


/* You don't need to change anything below this line. */
.							{ throw new Error("unexpected character '" + yytext() + "'"); }
 <<EOF>>						{ return token(EOF); }
