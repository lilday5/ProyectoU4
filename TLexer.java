// $ANTLR 3.5.2 T.g 2025-03-31 09:07:45

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TLexer extends Lexer {
	public static final int EOF=-1;
	public static final int ALFABETICO=4;
	public static final int CERRAR=5;
	public static final int CREAR=6;
	public static final int FECHA=7;
	public static final int FIN=8;
	public static final int ID=9;
	public static final int INICIO=10;
	public static final int NUMERICO=11;
	public static final int TABLA=12;
	public static final int USAR=13;
	public static final int WS=14;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public TLexer() {} 
	public TLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public TLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "T.g"; }

	// $ANTLR start "CERRAR"
	public final void mCERRAR() throws RecognitionException {
		try {
			int _type = CERRAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:82:11: ( 'cerrar' )
			// T.g:82:13: 'cerrar'
			{
			match("cerrar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CERRAR"

	// $ANTLR start "NUMERICO"
	public final void mNUMERICO() throws RecognitionException {
		try {
			int _type = NUMERICO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:83:11: ( 'numeros' )
			// T.g:83:13: 'numeros'
			{
			match("numeros"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMERICO"

	// $ANTLR start "ALFABETICO"
	public final void mALFABETICO() throws RecognitionException {
		try {
			int _type = ALFABETICO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:84:11: ( 'letras' )
			// T.g:84:13: 'letras'
			{
			match("letras"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALFABETICO"

	// $ANTLR start "FECHA"
	public final void mFECHA() throws RecognitionException {
		try {
			int _type = FECHA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:85:11: ( 'fecha' )
			// T.g:85:13: 'fecha'
			{
			match("fecha"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FECHA"

	// $ANTLR start "TABLA"
	public final void mTABLA() throws RecognitionException {
		try {
			int _type = TABLA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:86:11: ( 'tabla' )
			// T.g:86:13: 'tabla'
			{
			match("tabla"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TABLA"

	// $ANTLR start "INICIO"
	public final void mINICIO() throws RecognitionException {
		try {
			int _type = INICIO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:87:11: ( 'inicio' )
			// T.g:87:13: 'inicio'
			{
			match("inicio"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INICIO"

	// $ANTLR start "FIN"
	public final void mFIN() throws RecognitionException {
		try {
			int _type = FIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:88:11: ( 'fin' )
			// T.g:88:13: 'fin'
			{
			match("fin"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FIN"

	// $ANTLR start "USAR"
	public final void mUSAR() throws RecognitionException {
		try {
			int _type = USAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:89:11: ( 'usar' )
			// T.g:89:13: 'usar'
			{
			match("usar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USAR"

	// $ANTLR start "CREAR"
	public final void mCREAR() throws RecognitionException {
		try {
			int _type = CREAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:90:11: ( 'crear' )
			// T.g:90:13: 'crear'
			{
			match("crear"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CREAR"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:91:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// T.g:91:13: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// T.g:91:37: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// T.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// T.g:92:11: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
			// T.g:92:13: ( ' ' | '\\n' | '\\t' | '\\r' )+
			{
			// T.g:92:13: ( ' ' | '\\n' | '\\t' | '\\r' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '\t' && LA2_0 <= '\n')||LA2_0=='\r'||LA2_0==' ') ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// T.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// T.g:1:8: ( CERRAR | NUMERICO | ALFABETICO | FECHA | TABLA | INICIO | FIN | USAR | CREAR | ID | WS )
		int alt3=11;
		switch ( input.LA(1) ) {
		case 'c':
			{
			switch ( input.LA(2) ) {
			case 'e':
				{
				int LA3_10 = input.LA(3);
				if ( (LA3_10=='r') ) {
					int LA3_19 = input.LA(4);
					if ( (LA3_19=='r') ) {
						int LA3_28 = input.LA(5);
						if ( (LA3_28=='a') ) {
							int LA3_37 = input.LA(6);
							if ( (LA3_37=='r') ) {
								int LA3_45 = input.LA(7);
								if ( ((LA3_45 >= '0' && LA3_45 <= '9')||(LA3_45 >= 'A' && LA3_45 <= 'Z')||LA3_45=='_'||(LA3_45 >= 'a' && LA3_45 <= 'z')) ) {
									alt3=10;
								}

								else {
									alt3=1;
								}

							}

							else {
								alt3=10;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

				}
				break;
			case 'r':
				{
				int LA3_11 = input.LA(3);
				if ( (LA3_11=='e') ) {
					int LA3_20 = input.LA(4);
					if ( (LA3_20=='a') ) {
						int LA3_29 = input.LA(5);
						if ( (LA3_29=='r') ) {
							int LA3_38 = input.LA(6);
							if ( ((LA3_38 >= '0' && LA3_38 <= '9')||(LA3_38 >= 'A' && LA3_38 <= 'Z')||LA3_38=='_'||(LA3_38 >= 'a' && LA3_38 <= 'z')) ) {
								alt3=10;
							}

							else {
								alt3=9;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

				}
				break;
			default:
				alt3=10;
			}
			}
			break;
		case 'n':
			{
			int LA3_2 = input.LA(2);
			if ( (LA3_2=='u') ) {
				int LA3_12 = input.LA(3);
				if ( (LA3_12=='m') ) {
					int LA3_21 = input.LA(4);
					if ( (LA3_21=='e') ) {
						int LA3_30 = input.LA(5);
						if ( (LA3_30=='r') ) {
							int LA3_39 = input.LA(6);
							if ( (LA3_39=='o') ) {
								int LA3_47 = input.LA(7);
								if ( (LA3_47=='s') ) {
									int LA3_53 = input.LA(8);
									if ( ((LA3_53 >= '0' && LA3_53 <= '9')||(LA3_53 >= 'A' && LA3_53 <= 'Z')||LA3_53=='_'||(LA3_53 >= 'a' && LA3_53 <= 'z')) ) {
										alt3=10;
									}

									else {
										alt3=2;
									}

								}

								else {
									alt3=10;
								}

							}

							else {
								alt3=10;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case 'l':
			{
			int LA3_3 = input.LA(2);
			if ( (LA3_3=='e') ) {
				int LA3_13 = input.LA(3);
				if ( (LA3_13=='t') ) {
					int LA3_22 = input.LA(4);
					if ( (LA3_22=='r') ) {
						int LA3_31 = input.LA(5);
						if ( (LA3_31=='a') ) {
							int LA3_40 = input.LA(6);
							if ( (LA3_40=='s') ) {
								int LA3_48 = input.LA(7);
								if ( ((LA3_48 >= '0' && LA3_48 <= '9')||(LA3_48 >= 'A' && LA3_48 <= 'Z')||LA3_48=='_'||(LA3_48 >= 'a' && LA3_48 <= 'z')) ) {
									alt3=10;
								}

								else {
									alt3=3;
								}

							}

							else {
								alt3=10;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case 'f':
			{
			switch ( input.LA(2) ) {
			case 'e':
				{
				int LA3_14 = input.LA(3);
				if ( (LA3_14=='c') ) {
					int LA3_23 = input.LA(4);
					if ( (LA3_23=='h') ) {
						int LA3_32 = input.LA(5);
						if ( (LA3_32=='a') ) {
							int LA3_41 = input.LA(6);
							if ( ((LA3_41 >= '0' && LA3_41 <= '9')||(LA3_41 >= 'A' && LA3_41 <= 'Z')||LA3_41=='_'||(LA3_41 >= 'a' && LA3_41 <= 'z')) ) {
								alt3=10;
							}

							else {
								alt3=4;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

				}
				break;
			case 'i':
				{
				int LA3_15 = input.LA(3);
				if ( (LA3_15=='n') ) {
					int LA3_24 = input.LA(4);
					if ( ((LA3_24 >= '0' && LA3_24 <= '9')||(LA3_24 >= 'A' && LA3_24 <= 'Z')||LA3_24=='_'||(LA3_24 >= 'a' && LA3_24 <= 'z')) ) {
						alt3=10;
					}

					else {
						alt3=7;
					}

				}

				else {
					alt3=10;
				}

				}
				break;
			default:
				alt3=10;
			}
			}
			break;
		case 't':
			{
			int LA3_5 = input.LA(2);
			if ( (LA3_5=='a') ) {
				int LA3_16 = input.LA(3);
				if ( (LA3_16=='b') ) {
					int LA3_25 = input.LA(4);
					if ( (LA3_25=='l') ) {
						int LA3_34 = input.LA(5);
						if ( (LA3_34=='a') ) {
							int LA3_42 = input.LA(6);
							if ( ((LA3_42 >= '0' && LA3_42 <= '9')||(LA3_42 >= 'A' && LA3_42 <= 'Z')||LA3_42=='_'||(LA3_42 >= 'a' && LA3_42 <= 'z')) ) {
								alt3=10;
							}

							else {
								alt3=5;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case 'i':
			{
			int LA3_6 = input.LA(2);
			if ( (LA3_6=='n') ) {
				int LA3_17 = input.LA(3);
				if ( (LA3_17=='i') ) {
					int LA3_26 = input.LA(4);
					if ( (LA3_26=='c') ) {
						int LA3_35 = input.LA(5);
						if ( (LA3_35=='i') ) {
							int LA3_43 = input.LA(6);
							if ( (LA3_43=='o') ) {
								int LA3_51 = input.LA(7);
								if ( ((LA3_51 >= '0' && LA3_51 <= '9')||(LA3_51 >= 'A' && LA3_51 <= 'Z')||LA3_51=='_'||(LA3_51 >= 'a' && LA3_51 <= 'z')) ) {
									alt3=10;
								}

								else {
									alt3=6;
								}

							}

							else {
								alt3=10;
							}

						}

						else {
							alt3=10;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case 'u':
			{
			int LA3_7 = input.LA(2);
			if ( (LA3_7=='s') ) {
				int LA3_18 = input.LA(3);
				if ( (LA3_18=='a') ) {
					int LA3_27 = input.LA(4);
					if ( (LA3_27=='r') ) {
						int LA3_36 = input.LA(5);
						if ( ((LA3_36 >= '0' && LA3_36 <= '9')||(LA3_36 >= 'A' && LA3_36 <= 'Z')||LA3_36=='_'||(LA3_36 >= 'a' && LA3_36 <= 'z')) ) {
							alt3=10;
						}

						else {
							alt3=8;
						}

					}

					else {
						alt3=10;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case '_':
		case 'a':
		case 'b':
		case 'd':
		case 'e':
		case 'g':
		case 'h':
		case 'j':
		case 'k':
		case 'm':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt3=10;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt3=11;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 3, 0, input);
			throw nvae;
		}
		switch (alt3) {
			case 1 :
				// T.g:1:10: CERRAR
				{
				mCERRAR(); 

				}
				break;
			case 2 :
				// T.g:1:17: NUMERICO
				{
				mNUMERICO(); 

				}
				break;
			case 3 :
				// T.g:1:26: ALFABETICO
				{
				mALFABETICO(); 

				}
				break;
			case 4 :
				// T.g:1:37: FECHA
				{
				mFECHA(); 

				}
				break;
			case 5 :
				// T.g:1:43: TABLA
				{
				mTABLA(); 

				}
				break;
			case 6 :
				// T.g:1:49: INICIO
				{
				mINICIO(); 

				}
				break;
			case 7 :
				// T.g:1:56: FIN
				{
				mFIN(); 

				}
				break;
			case 8 :
				// T.g:1:60: USAR
				{
				mUSAR(); 

				}
				break;
			case 9 :
				// T.g:1:65: CREAR
				{
				mCREAR(); 

				}
				break;
			case 10 :
				// T.g:1:71: ID
				{
				mID(); 

				}
				break;
			case 11 :
				// T.g:1:74: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
