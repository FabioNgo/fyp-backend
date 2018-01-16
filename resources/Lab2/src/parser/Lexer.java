/* The following code was generated by JFlex 1.4 on 9/17/13 10:07 AM */

package parser;

import beaver.Symbol;
import beaver.Scanner;
import static parser.Parser.Terminals.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4
 * on 9/17/13 10:07 AM from the specification file
 * <tt>/home/mschaefer/teaching/CZ3007/mobila/src/frontend/mobila.flex</tt>
 */
public final class Lexer extends Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\4\1\1\1\2\1\0\1\1\1\2\16\4\4\0\1\1\1\45"+
    "\1\6\1\0\1\3\1\54\2\0\1\34\1\35\1\52\1\50\1\43"+
    "\1\51\1\0\1\53\12\5\1\0\1\42\1\46\1\44\1\47\2\0"+
    "\32\3\1\36\1\0\1\37\1\0\1\3\1\0\1\25\1\24\1\33"+
    "\1\11\1\14\1\26\1\3\1\31\1\15\1\3\1\32\1\13\1\7"+
    "\1\22\1\10\1\16\1\3\1\17\1\27\1\20\1\12\1\23\1\30"+
    "\1\3\1\21\1\3\1\40\1\0\1\41\1\0\41\4\2\0\4\3"+
    "\4\0\1\3\2\0\1\4\7\0\1\3\4\0\1\3\5\0\27\3"+
    "\1\0\37\3\1\0\u01ca\3\4\0\14\3\16\0\5\3\7\0\1\3"+
    "\1\0\1\3\21\0\160\4\5\3\1\0\2\3\2\0\4\3\10\0"+
    "\1\3\1\0\3\3\1\0\1\3\1\0\24\3\1\0\123\3\1\0"+
    "\213\3\1\0\5\4\2\0\236\3\11\0\46\3\2\0\1\3\7\0"+
    "\47\3\11\0\55\4\1\0\1\4\1\0\2\4\1\0\2\4\1\0"+
    "\1\4\10\0\33\3\5\0\3\3\15\0\4\4\7\0\1\3\4\0"+
    "\13\4\5\0\53\3\37\4\4\0\2\3\1\4\143\3\1\0\1\3"+
    "\10\4\1\0\6\4\2\3\2\4\1\0\4\4\2\3\12\4\3\3"+
    "\2\0\1\3\17\0\1\4\1\3\1\4\36\3\33\4\2\0\131\3"+
    "\13\4\1\3\16\0\12\4\41\3\11\4\2\3\4\0\1\3\5\0"+
    "\26\3\4\4\1\3\11\4\1\3\3\4\1\3\5\4\22\0\31\3"+
    "\3\4\244\0\4\4\66\3\3\4\1\3\22\4\1\3\7\4\12\3"+
    "\2\4\2\0\12\4\1\0\7\3\1\0\7\3\1\0\3\4\1\0"+
    "\10\3\2\0\2\3\2\0\26\3\1\0\7\3\1\0\1\3\3\0"+
    "\4\3\2\0\1\4\1\3\7\4\2\0\2\4\2\0\3\4\1\3"+
    "\10\0\1\4\4\0\2\3\1\0\3\3\2\4\2\0\12\4\4\3"+
    "\7\0\1\3\5\0\3\4\1\0\6\3\4\0\2\3\2\0\26\3"+
    "\1\0\7\3\1\0\2\3\1\0\2\3\1\0\2\3\2\0\1\4"+
    "\1\0\5\4\4\0\2\4\2\0\3\4\3\0\1\4\7\0\4\3"+
    "\1\0\1\3\7\0\14\4\3\3\1\4\13\0\3\4\1\0\11\3"+
    "\1\0\3\3\1\0\26\3\1\0\7\3\1\0\2\3\1\0\5\3"+
    "\2\0\1\4\1\3\10\4\1\0\3\4\1\0\3\4\2\0\1\3"+
    "\17\0\2\3\2\4\2\0\12\4\1\0\1\3\17\0\3\4\1\0"+
    "\10\3\2\0\2\3\2\0\26\3\1\0\7\3\1\0\2\3\1\0"+
    "\5\3\2\0\1\4\1\3\7\4\2\0\2\4\2\0\3\4\10\0"+
    "\2\4\4\0\2\3\1\0\3\3\2\4\2\0\12\4\1\0\1\3"+
    "\20\0\1\4\1\3\1\0\6\3\3\0\3\3\1\0\4\3\3\0"+
    "\2\3\1\0\1\3\1\0\2\3\3\0\2\3\3\0\3\3\3\0"+
    "\14\3\4\0\5\4\3\0\3\4\1\0\4\4\2\0\1\3\6\0"+
    "\1\4\16\0\12\4\11\0\1\3\7\0\3\4\1\0\10\3\1\0"+
    "\3\3\1\0\27\3\1\0\12\3\1\0\5\3\3\0\1\3\7\4"+
    "\1\0\3\4\1\0\4\4\7\0\2\4\1\0\2\3\6\0\2\3"+
    "\2\4\2\0\12\4\22\0\2\4\1\0\10\3\1\0\3\3\1\0"+
    "\27\3\1\0\12\3\1\0\5\3\2\0\1\4\1\3\7\4\1\0"+
    "\3\4\1\0\4\4\7\0\2\4\7\0\1\3\1\0\2\3\2\4"+
    "\2\0\12\4\1\0\2\3\17\0\2\4\1\0\10\3\1\0\3\3"+
    "\1\0\51\3\2\0\1\3\7\4\1\0\3\4\1\0\4\4\1\3"+
    "\10\0\1\4\10\0\2\3\2\4\2\0\12\4\12\0\6\3\2\0"+
    "\2\4\1\0\22\3\3\0\30\3\1\0\11\3\1\0\1\3\2\0"+
    "\7\3\3\0\1\4\4\0\6\4\1\0\1\4\1\0\10\4\22\0"+
    "\2\4\15\0\60\3\1\4\2\3\7\4\4\0\10\3\10\4\1\0"+
    "\12\4\47\0\2\3\1\0\1\3\2\0\2\3\1\0\1\3\2\0"+
    "\1\3\6\0\4\3\1\0\7\3\1\0\3\3\1\0\1\3\1\0"+
    "\1\3\2\0\2\3\1\0\4\3\1\4\2\3\6\4\1\0\2\4"+
    "\1\3\2\0\5\3\1\0\1\3\1\0\6\4\2\0\12\4\2\0"+
    "\2\3\42\0\1\3\27\0\2\4\6\0\12\4\13\0\1\4\1\0"+
    "\1\4\1\0\1\4\4\0\2\4\10\3\1\0\44\3\4\0\24\4"+
    "\1\0\2\4\5\3\13\4\1\0\44\4\11\0\1\4\71\0\53\3"+
    "\24\4\1\3\12\4\6\0\6\3\4\4\4\3\3\4\1\3\3\4"+
    "\2\3\7\4\3\3\4\4\15\3\14\4\1\3\17\4\2\0\46\3"+
    "\12\0\53\3\1\0\1\3\3\0\u0149\3\1\0\4\3\2\0\7\3"+
    "\1\0\1\3\1\0\4\3\2\0\51\3\1\0\4\3\2\0\41\3"+
    "\1\0\4\3\2\0\7\3\1\0\1\3\1\0\4\3\2\0\17\3"+
    "\1\0\71\3\1\0\4\3\2\0\103\3\2\0\3\4\40\0\20\3"+
    "\20\0\125\3\14\0\u026c\3\2\0\21\3\1\0\32\3\5\0\113\3"+
    "\3\0\3\3\17\0\15\3\1\0\4\3\3\4\13\0\22\3\3\4"+
    "\13\0\22\3\2\4\14\0\15\3\1\0\3\3\1\0\2\4\14\0"+
    "\64\3\40\4\3\0\1\3\3\0\2\3\1\4\2\0\12\4\41\0"+
    "\3\4\2\0\12\4\6\0\130\3\10\0\51\3\1\4\1\3\5\0"+
    "\106\3\12\0\35\3\3\0\14\4\4\0\14\4\12\0\12\4\36\3"+
    "\2\0\5\3\13\0\54\3\4\0\21\4\7\3\2\4\6\0\12\4"+
    "\46\0\27\3\5\4\4\0\65\3\12\4\1\0\35\4\2\0\13\4"+
    "\6\0\12\4\15\0\1\3\130\0\5\4\57\3\21\4\7\3\4\0"+
    "\12\4\21\0\11\4\14\0\3\4\36\3\12\4\3\0\2\3\12\4"+
    "\6\0\46\3\16\4\14\0\44\3\24\4\10\0\12\4\3\0\3\3"+
    "\12\4\44\3\122\0\3\4\1\0\25\4\4\3\1\4\4\3\1\4"+
    "\15\0\300\3\47\4\25\0\4\4\u0116\3\2\0\6\3\2\0\46\3"+
    "\2\0\6\3\2\0\10\3\1\0\1\3\1\0\1\3\1\0\1\3"+
    "\1\0\37\3\2\0\65\3\1\0\7\3\1\0\1\3\3\0\3\3"+
    "\1\0\7\3\3\0\4\3\2\0\6\3\4\0\15\3\5\0\3\3"+
    "\1\0\7\3\16\0\5\4\32\0\5\4\20\0\2\3\23\0\1\3"+
    "\13\0\5\4\5\0\6\4\1\0\1\3\15\0\1\3\20\0\15\3"+
    "\3\0\32\3\26\0\15\4\4\0\1\4\3\0\14\4\21\0\1\3"+
    "\4\0\1\3\2\0\12\3\1\0\1\3\3\0\5\3\6\0\1\3"+
    "\1\0\1\3\1\0\1\3\1\0\4\3\1\0\13\3\2\0\4\3"+
    "\5\0\5\3\4\0\1\3\21\0\51\3\u0a77\0\57\3\1\0\57\3"+
    "\1\0\205\3\6\0\4\3\3\4\16\0\46\3\12\0\66\3\11\0"+
    "\1\3\17\0\1\4\27\3\11\0\7\3\1\0\7\3\1\0\7\3"+
    "\1\0\7\3\1\0\7\3\1\0\7\3\1\0\7\3\1\0\7\3"+
    "\1\0\40\4\57\0\1\3\u01d5\0\3\3\31\0\11\3\6\4\1\0"+
    "\5\3\2\0\5\3\4\0\126\3\2\0\2\4\2\0\3\3\1\0"+
    "\132\3\1\0\4\3\5\0\51\3\3\0\136\3\21\0\33\3\65\0"+
    "\20\3\u0200\0\u19b6\3\112\0\u51cc\3\64\0\u048d\3\103\0\56\3\2\0"+
    "\u010d\3\3\0\20\3\12\4\2\3\24\0\57\3\1\4\14\0\2\4"+
    "\1\0\31\3\10\0\120\3\2\4\45\0\11\3\2\0\147\3\2\0"+
    "\4\3\1\0\2\3\16\0\12\3\120\0\10\3\1\4\3\3\1\4"+
    "\4\3\1\4\27\3\5\4\20\0\1\3\7\0\64\3\14\0\2\4"+
    "\62\3\21\4\13\0\12\4\6\0\22\4\6\3\3\0\1\3\4\0"+
    "\12\4\34\3\10\4\2\0\27\3\15\4\14\0\35\3\3\0\4\4"+
    "\57\3\16\4\16\0\1\3\12\4\46\0\51\3\16\4\11\0\3\3"+
    "\1\4\10\3\2\4\2\0\12\4\6\0\27\3\3\0\1\3\1\4"+
    "\4\0\60\3\1\4\1\3\3\4\2\3\2\4\5\3\2\4\1\3"+
    "\1\4\1\3\30\0\3\3\43\0\6\3\2\0\6\3\2\0\6\3"+
    "\11\0\7\3\1\0\7\3\221\0\43\3\10\4\1\0\2\4\2\0"+
    "\12\4\6\0\u2ba4\3\14\0\27\3\4\0\61\3\u2104\0\u012e\3\2\0"+
    "\76\3\2\0\152\3\46\0\7\3\14\0\5\3\5\0\1\3\1\4"+
    "\12\3\1\0\15\3\1\0\5\3\1\0\1\3\1\0\2\3\1\0"+
    "\2\3\1\0\154\3\41\0\u016b\3\22\0\100\3\2\0\66\3\50\0"+
    "\15\3\3\0\20\4\20\0\7\4\14\0\2\3\30\0\3\3\31\0"+
    "\1\3\6\0\5\3\1\0\207\3\2\0\1\4\4\0\1\3\13\0"+
    "\12\4\7\0\32\3\4\0\1\3\1\0\32\3\13\0\131\3\3\0"+
    "\6\3\2\0\6\3\2\0\6\3\2\0\3\3\3\0\2\3\3\0"+
    "\2\3\22\0\3\4\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\1\12\3\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\1\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\0\1\25\4\3\1\26\11\3\1\27\1\30\1\31"+
    "\1\32\3\3\1\33\12\3\1\34\3\3\1\35\1\36"+
    "\1\37\11\3\1\40\1\41\1\42\1\43\1\44\1\45"+
    "\1\46\1\3\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[92];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\55\0\55\0\132\0\207\0\264\0\341\0\u010e"+
    "\0\u013b\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249\0\u0276"+
    "\0\55\0\55\0\55\0\55\0\55\0\55\0\55\0\55"+
    "\0\u02a3\0\u02d0\0\u02fd\0\u032a\0\55\0\55\0\55\0\55"+
    "\0\55\0\264\0\55\0\u0357\0\u0384\0\u03b1\0\u03de\0\132"+
    "\0\u040b\0\u0438\0\u0465\0\u0492\0\u04bf\0\u04ec\0\u0519\0\u0546"+
    "\0\u0573\0\55\0\55\0\55\0\55\0\u05a0\0\u05cd\0\u05fa"+
    "\0\132\0\u0627\0\u0654\0\u0681\0\u06ae\0\u06db\0\u0708\0\u0735"+
    "\0\u0762\0\u078f\0\u07bc\0\132\0\u07e9\0\u0816\0\u0843\0\132"+
    "\0\132\0\132\0\u0870\0\u089d\0\u08ca\0\u08f7\0\u0924\0\u0951"+
    "\0\u097e\0\u09ab\0\u09d8\0\132\0\132\0\132\0\132\0\132"+
    "\0\132\0\132\0\u0a05\0\132";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[92];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\4\1\2\1\5\1\6\1\7\4\4"+
    "\1\10\1\11\1\12\1\13\1\14\2\4\1\15\1\16"+
    "\1\4\1\17\1\4\1\20\3\4\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\60\0\3\4"+
    "\1\0\25\4\26\0\1\5\47\0\2\42\1\0\3\42"+
    "\1\43\46\42\3\0\3\4\1\0\1\4\1\44\23\4"+
    "\24\0\3\4\1\0\4\4\1\45\20\4\24\0\3\4"+
    "\1\0\1\46\12\4\1\47\3\4\1\50\5\4\24\0"+
    "\3\4\1\0\3\4\1\51\21\4\24\0\3\4\1\0"+
    "\5\4\1\52\17\4\24\0\3\4\1\0\10\4\1\53"+
    "\1\4\1\54\12\4\24\0\3\4\1\0\1\4\1\55"+
    "\23\4\24\0\3\4\1\0\1\4\1\56\6\4\1\57"+
    "\14\4\24\0\3\4\1\0\16\4\1\60\6\4\24\0"+
    "\3\4\1\0\22\4\1\61\2\4\65\0\1\62\54\0"+
    "\1\63\54\0\1\64\54\0\1\65\13\0\3\4\1\0"+
    "\2\4\1\66\22\4\24\0\3\4\1\0\20\4\1\67"+
    "\4\4\24\0\3\4\1\0\7\4\1\70\15\4\24\0"+
    "\3\4\1\0\11\4\1\71\13\4\24\0\3\4\1\0"+
    "\15\4\1\72\7\4\24\0\3\4\1\0\11\4\1\73"+
    "\13\4\24\0\3\4\1\0\3\4\1\74\21\4\24\0"+
    "\3\4\1\0\7\4\1\75\15\4\24\0\3\4\1\0"+
    "\6\4\1\76\16\4\24\0\3\4\1\0\1\4\1\77"+
    "\23\4\24\0\3\4\1\0\5\4\1\100\17\4\24\0"+
    "\3\4\1\0\4\4\1\101\20\4\24\0\3\4\1\0"+
    "\6\4\1\102\16\4\24\0\3\4\1\0\3\4\1\103"+
    "\21\4\24\0\3\4\1\0\5\4\1\104\17\4\24\0"+
    "\3\4\1\0\1\4\1\105\23\4\24\0\3\4\1\0"+
    "\4\4\1\106\20\4\24\0\3\4\1\0\3\4\1\107"+
    "\21\4\24\0\3\4\1\0\5\4\1\110\17\4\24\0"+
    "\3\4\1\0\5\4\1\111\17\4\24\0\3\4\1\0"+
    "\2\4\1\112\22\4\24\0\3\4\1\0\4\4\1\113"+
    "\20\4\24\0\3\4\1\0\16\4\1\114\6\4\24\0"+
    "\3\4\1\0\20\4\1\115\4\4\24\0\3\4\1\0"+
    "\4\4\1\116\20\4\24\0\3\4\1\0\4\4\1\117"+
    "\20\4\24\0\3\4\1\0\10\4\1\120\14\4\24\0"+
    "\3\4\1\0\6\4\1\121\16\4\24\0\3\4\1\0"+
    "\10\4\1\122\14\4\24\0\3\4\1\0\5\4\1\123"+
    "\17\4\24\0\3\4\1\0\23\4\1\124\1\4\24\0"+
    "\3\4\1\0\5\4\1\125\17\4\24\0\3\4\1\0"+
    "\5\4\1\126\17\4\24\0\3\4\1\0\5\4\1\127"+
    "\17\4\24\0\3\4\1\0\11\4\1\130\13\4\24\0"+
    "\3\4\1\0\24\4\1\131\24\0\3\4\1\0\13\4"+
    "\1\132\11\4\24\0\3\4\1\0\16\4\1\133\6\4"+
    "\24\0\3\4\1\0\13\4\1\134\11\4\21\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2610];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\15\1\10\11\4\1\5\11\1\0\1\11"+
    "\16\1\4\11\47\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[92];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
	private Symbol token(short type) {
		return new Symbol(type, yytext());
	}
	
	private Symbol token(short type, String text) {
		return new Symbol(type, yyline, yycolumn, yytext().length(), text);
	}


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2208) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Symbol nextToken() throws java.io.IOException, Exception {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 2: 
          { /* do nothing */
          }
        case 40: break;
        case 29: 
          { return token(TRUE);
          }
        case 41: break;
        case 30: 
          { return token(TYPE);
          }
        case 42: break;
        case 22: 
          { return token(IF);
          }
        case 43: break;
        case 31: 
          { return token(VOID);
          }
        case 44: break;
        case 28: 
          { return token(ELSE);
          }
        case 45: break;
        case 18: 
          { return token(TIMES);
          }
        case 46: break;
        case 39: 
          { return token(BOOLEAN);
          }
        case 47: break;
        case 35: 
          { return token(MODULE);
          }
        case 48: break;
        case 34: 
          { return token(WHILE);
          }
        case 49: break;
        case 38: 
          { return token(RETURN);
          }
        case 50: break;
        case 32: 
          { return token(BREAK);
          }
        case 51: break;
        case 4: 
          { return token(INT_LITERAL);
          }
        case 52: break;
        case 3: 
          { return token(ID);
          }
        case 53: break;
        case 9: 
          { return token(LCURLY);
          }
        case 54: break;
        case 25: 
          { return token(LEQ);
          }
        case 55: break;
        case 17: 
          { return token(MINUS);
          }
        case 56: break;
        case 36: 
          { return token(IMPORT);
          }
        case 57: break;
        case 5: 
          { return token(LPAREN);
          }
        case 58: break;
        case 7: 
          { return token(LBRACKET);
          }
        case 59: break;
        case 11: 
          { return token(SEMICOLON);
          }
        case 60: break;
        case 27: 
          { return token(INT);
          }
        case 61: break;
        case 12: 
          { return token(COMMA);
          }
        case 62: break;
        case 23: 
          { return token(EQEQ);
          }
        case 63: break;
        case 19: 
          { return token(DIV);
          }
        case 64: break;
        case 33: 
          { return token(FALSE);
          }
        case 65: break;
        case 15: 
          { return token(GT);
          }
        case 66: break;
        case 14: 
          { return token(LT);
          }
        case 67: break;
        case 20: 
          { return token(MOD);
          }
        case 68: break;
        case 26: 
          { return token(GEQ);
          }
        case 69: break;
        case 8: 
          { return token(RBRACKET);
          }
        case 70: break;
        case 13: 
          { return token(EQL);
          }
        case 71: break;
        case 10: 
          { return token(RCURLY);
          }
        case 72: break;
        case 24: 
          { return token(NEQ);
          }
        case 73: break;
        case 37: 
          { return token(PUBLIC);
          }
        case 74: break;
        case 1: 
          { throw new Error("unexpected character " + yytext());
          }
        case 75: break;
        case 6: 
          { return token(RPAREN);
          }
        case 76: break;
        case 16: 
          { return token(PLUS);
          }
        case 77: break;
        case 21: 
          { return token(STRING_LITERAL, yytext().substring(1, yylength()-1));
          }
        case 78: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              { return token(EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
