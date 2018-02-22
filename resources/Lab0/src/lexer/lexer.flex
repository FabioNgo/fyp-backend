/* You do not need to change anything up here. */
package lexer;

import beaver.Symbol;
import beaver.Scanner;
import static parser.Parser.Terminals.*;
%%

%public
%final
%class Lexer
%function nextToken
%extends Scanner
%type Symbol
%unicode
%line
%column

%{
    private Symbol token(short type) {
        return new Symbol(type, this.yytext());
    }

    private Symbol token(short type, String text) {
        return new Symbol(type, this.yyline, this.yycolumn, this.yytext().length(), text);
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
